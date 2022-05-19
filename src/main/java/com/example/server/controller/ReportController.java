package com.example.server.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.server.constant.ServerHosts;
import com.example.server.filters.LoginAuthRequestFilter;
import com.example.server.pojo.*;
import com.example.server.publics.RespBean;
import com.example.server.service.*;
import com.example.server.utils.RestAssuredUtil;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.*;

import static io.restassured.RestAssured.given;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zengchengbing
 * @since 2021-12-28
 */
//@SpringBootTest(classes = Applocation.class)
//@ComponentScan("com.example.*")
@RestController
public class ReportController {
    private static Logger logger = LoggerFactory.getLogger(ReportController.class);
    private static Map<String, Object> date = new HashMap<>();
//    private static JSONObject jsonrequest =new JSONObject();
//    private static JSONObject jsonHeader =new JSONObject();
//    private static JSONObject jsonInputParameter =new JSONObject();
//    private static JSONObject jsonOutputParameter =new JSONObject();
//    private static JSONObject responsebody =new JSONObject();
//    private static Report report=new Report();
//    private static Failcase failcase=new Failcase();
//    private static List<Failcase> failcaselist=new ArrayList<>();

    @Autowired
    IReportService reportService;
    //    @Autowired(required = false)
//    Report report;
    @Autowired
    ISceneclassificationService sceneclassificationService;
    @Autowired
    ICaselistService caselistService;
    @Autowired
    IFailcaseService failcaseService;
    @Autowired
    IPerformtasksService performtasksService;

    //执行任务
    @PostMapping("startcase")
    public RespBean startcase(@RequestBody Performtasks performtasks) {
        //初始化参数
        variable variablelist = new variable();

        //用例执行
        if (!scenestart(performtasks, variablelist)) {
            return RespBean.error("执行场景不能为空");
        }


        Report report = variablelist.getReport();

        logger.info("执行用例数量总数" + report.getBasesum()+"/"+"用例成功数量总数" + report.getSuccesssum()+"/"+"用例失败数量总数" + report.getErrorsum());
        Map<String, Object> map = new HashMap<>();
        map.put("Basesum", report.getBasesum());
        map.put("Successsum", report.getSuccesssum());
        map.put("Errorsum", report.getErrorsum());
        map.put("Successfullist", report.getSuccessfullist());
        map.put("Failurelist", report.getFailurelist());
        report.setCreate_time(LocalDateTime.now().plusHours(14));//报告插入数据库
        reportService.insertreport(report);
        System.out.println(report.getId());
        for (Failcase fail : variablelist.getFailcaselist()) {
            fail.setReportid(report.getId());
            failcaseService.insert(fail);
        }
        //初始化数据
        initialization(variablelist);
        //发送钉钉报告
        String str = "." + "执行用例case:" + map.get("Basesum") + "\n" + "执行通过case:" + map.get("Successsum") + "\n"
                + "执行失败case:" + map.get("Errorsum") + "\n" + "执行成功用例名称:" + map.get("Successfullist") + "\n"
                + "执行失败用例名称:" + map.get("Failurelist");
        JSONObject content = new JSONObject();
        JSONObject body = new JSONObject();
        content.put("content", str);
        body.put("msgtype", "text");
        body.put("text", content);
        DDURLPOSTCase.httpURLPOSTCase(body.toJSONString());
        return RespBean.sucess("执行任务完成", map);
    }

    //初始化数据
    private void initialization(variable variablelist) {
        variablelist.getReport().setBasesum(0);
        variablelist.getReport().setErrorsum(0);
        variablelist.getReport().setSuccesssum(0);
        variablelist.getReport().setSuccessfullist(null);
        variablelist.getReport().setFailurelist(null);
        variablelist.setFailcaselist(new ArrayList<>());
//        failcaselist=new ArrayList<>();
    }

    //通过请求方式(get&post)走向不同的接口api
    private void casetest(Caselist caselist, variable variablelist) {
        Report report = variablelist.getReport();
        if (caselist.getFace().getMethod().equals("post")) {
            report.setBasesum(report.getBasesum() + 1);
            Posttest(caselist, variablelist);
            System.out.println("执行post");
        } else if (caselist.getFace().getMethod().equals("get")) {
            report.setBasesum(report.getBasesum() + 1);
            Gettest(caselist, variablelist);
            System.out.println("执行get");
        } else {
            logger.error(caselist.getCaseTitle() + "请求方式只能支持post与get");
            System.out.println("请求方式只能支持post与get");
        }
    }

    private void Gettest(Caselist caselist, variable variablelist) {
        String inputlist = caselist.getInputlist();
        JSONObject jsonrequest = variablelist.getJsonrequest();
        Map<String, Object> requestmap = new HashMap<>();
        //加载变量参数
        if (inputlist!=null){
            String[] split = inputlist.split(",");
            for (String str:split){
                jsonrequest.put(str,variablelist.getResponsebody().get(str));
            }

        }
        //转换成map

        Set<String> requestset = jsonrequest.keySet();
        for (String r : requestset) {
            requestmap.put(r, jsonrequest.get(r));
        }

        Response response =
                given()
                        .params(requestmap)
                        .when()
                        .get(caselist.getFace().getInterfaceUrl())
                        .then()
//                .body(caselist.getAsserts(),equalTo(caselist.getAssertresult()))
                        .spec(RestAssuredUtil.getDefaultResponseSpecification())
                        .extract().response();

        responseAsserts(response, caselist, variablelist);
    }


    private void Posttest(Caselist caselist, variable variablelist) {
        String inputlist = caselist.getInputlist();
        JSONObject jsonrequest = variablelist.getJsonrequest();

        if (!inputlist.isEmpty()){
            String[] split = inputlist.split(",");
            for (String str:split){
                jsonrequest.put(str,variablelist.getResponsebody().get(str));//加载变量参数
            }

        }


//        if (!jsonInputParameter.isEmpty()) {
//            Set<String> set = jsonInputParameter.keySet();
//            for (String s : set) {
//                jsonrequest.put(s, jsonInputParameter.get(s));
//                System.out.println("输入参数修改" + s + "...." + jsonInputParameter.get(s));
//                logger.info("输入参数修改" + s + "...." + jsonInputParameter.get(s));
//            }
//        }

        Response response =
                given()
//                        .headers(jsonHeader)
                        .body(jsonrequest.toJSONString())
                        .when()
                        .post(caselist.getFace().getInterfaceUrl())
                        .then()
//                        .body(caselist.getAsserts(),equalTo(caselist.getAssertresult()))
                        .spec(RestAssuredUtil.getDefaultResponseSpecification())
                        .extract()
                        .response();
        responseAsserts(response, caselist, variablelist);//断言
    }


    //断言
    private void responseAsserts(Response response, Caselist caselist, variable variablelist) {
        JsonPath path = response.body().jsonPath();
        Report report = variablelist.getReport();
        JSONObject jsonOutputParameter = variablelist.getJsonOutputParameter();
        JSONObject responsebody = variablelist.getResponsebody();

        try {
            if (path.getInt("code") == 200) {
                StringBuilder sb = new StringBuilder("");
                Object o = path.get(caselist.getAsserts());
                sb.append(o);
                //断言开始，通过不同的断言类型区分断言
                if (caselist.getAsserttype().equals("=")) {
                    if (sb.toString().equals(caselist.getAssertresult())) {
                        report.setSuccesssum(report.getSuccesssum() + 1);//记case成功总数
                        if (StringUtils.isNotEmpty(report.getSuccessfullist())) {
                            report.setSuccessfullist(report.getSuccessfullist() + "," + caselist.getCaseTitle());
                        } else {
                            report.setSuccessfullist(caselist.getCaseTitle());
                        }

                        //输出参数组装
                        if (!jsonOutputParameter.isEmpty()) {
                            Set<String> set = jsonOutputParameter.keySet();
                            for (String s : set) {
                                responsebody.put(s, path.get(jsonOutputParameter.getString(s)));
                            }
                        }
                    } else {
                        failcaseinsert(response.body().print(), caselist, variablelist);//把错误的返回插入库中
                    }
                }
                else if (caselist.getAsserttype().equals(">")) {
                    if (Integer.valueOf(sb.toString()) > Integer.valueOf(caselist.getAssertresult())) {
                        report.setSuccesssum(report.getSuccesssum() + 1);//记case成功总数
                        if (StringUtils.isNotEmpty(report.getSuccessfullist())) {
                            report.setSuccessfullist(report.getSuccessfullist() + "," + caselist.getCaseTitle());
                        } else {
                            report.setSuccessfullist(caselist.getCaseTitle());
                        }

                        //输出参数组装
                        if (!jsonOutputParameter.isEmpty()) {
                            Set<String> set = jsonOutputParameter.keySet();
                            for (String s : set) {
                                responsebody.put(s, path.get(jsonOutputParameter.getString(s)));
                            }
                        }
                    } else {
                        failcaseinsert(response.body().print(), caselist, variablelist);//把错误的返回插入库中
                    }
                }
                else if (caselist.getAsserttype().equals("<")) {
                    if (Integer.valueOf(sb.toString()) < Integer.valueOf(caselist.getAssertresult())) {
                        report.setSuccesssum(report.getSuccesssum() + 1);//记case成功总数
                        if (StringUtils.isNotEmpty(report.getSuccessfullist())) {
                            report.setSuccessfullist(report.getSuccessfullist() + "," + caselist.getCaseTitle());
                        } else {
                            report.setSuccessfullist(caselist.getCaseTitle());
                        }

                        //输出参数组装
                        if (!jsonOutputParameter.isEmpty()) {
                            Set<String> set = jsonOutputParameter.keySet();
                            for (String s : set) {
                                responsebody.put(s, path.get(jsonOutputParameter.getString(s)));
                            }
                        }
                    } else {
                        failcaseinsert(response.body().print(), caselist, variablelist);//把错误的返回插入库中
                    }
                }
                else if (caselist.getAsserttype().equals(">=")) {
                    if (Integer.valueOf(sb.toString()) >= Integer.valueOf(caselist.getAssertresult())) {
                        report.setSuccesssum(report.getSuccesssum() + 1);//记case成功总数
                        if (StringUtils.isNotEmpty(report.getSuccessfullist())) {
                            report.setSuccessfullist(report.getSuccessfullist() + "," + caselist.getCaseTitle());
                        } else {
                            report.setSuccessfullist(caselist.getCaseTitle());
                        }

                        //输出参数组装
                        if (!jsonOutputParameter.isEmpty()) {
                            Set<String> set = jsonOutputParameter.keySet();
                            for (String s : set) {
                                responsebody.put(s, path.get(jsonOutputParameter.getString(s)));
                            }
                        }
                    } else {
                        failcaseinsert(response.body().print(), caselist, variablelist);//把错误的返回插入库中
                    }
                }
                else if (caselist.getAsserttype().equals("<=")) {
                    if (Integer.valueOf(sb.toString()) <= Integer.valueOf(caselist.getAssertresult())) {
                        report.setSuccesssum(report.getSuccesssum() + 1);//记case成功总数
                        if (StringUtils.isNotEmpty(report.getSuccessfullist())) {
                            report.setSuccessfullist(report.getSuccessfullist() + "," + caselist.getCaseTitle());
                        } else {
                            report.setSuccessfullist(caselist.getCaseTitle());
                        }

                        //输出参数组装
                        if (!jsonOutputParameter.isEmpty()) {
                            Set<String> set = jsonOutputParameter.keySet();
                            for (String s : set) {
                                responsebody.put(s, path.get(jsonOutputParameter.getString(s)));
                            }
                        }
                    } else {
                        failcaseinsert(response.body().print(), caselist, variablelist);//把错误的返回插入库中
                    }
                }
                else {
                    logger.error("断言类型不支持：" + caselist.getAsserttype());
                    System.out.println("断言类型不支持：" + caselist.getAsserttype());
                }

            } else {

                failcaseinsert(response.body().print(), caselist, variablelist);//把错误的返回插入库中
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            logger.error("异常:" + e.getMessage());
            failcaseinsert("异常捕获:" + e.getMessage(), caselist, variablelist);//把错误的返回插入库中
        }
        System.out.println(responsebody);
    }

    //写入异常断言失败list
    private void failcaseinsert(String print, Caselist caselist, variable variablelist) {
        Report report = variablelist.getReport();
        Failcase failcase = variablelist.getFailcase();
        List<Failcase> failcaselist = variablelist.getFailcaselist();

        report.setErrorsum(report.getErrorsum() + 1);//记case失败总数
        if (StringUtils.isNotEmpty(report.getFailurelist())) {
            report.setFailurelist(report.getFailurelist() + "," + caselist.getCaseTitle());
        } else {
            report.setFailurelist(caselist.getCaseTitle());
        }

        failcase.setCaseid(caselist.getId());
        failcase.setCreatetime(LocalDateTime.now().plusHours(14));
        failcase.setErrorlog(print);
        failcaselist.add(failcase);
    }

    //取出入参的所有数据变量
    private void dataloading(Caselist caselist, variable variablelist) {
        try {
            if (StringUtils.isNotEmpty(caselist.getCaserequest())) {
                variablelist.setJsonrequest(JSONObject.parseObject(caselist.getCaserequest()));
            }
            if (StringUtils.isNotEmpty(caselist.getHeader())) {
                variablelist.setJsonHeader(JSONObject.parseObject(caselist.getHeader()));
            }

            if (StringUtils.isNotEmpty(caselist.getOutputParameter())) {
                variablelist.setJsonOutputParameter(JSONObject.parseObject(caselist.getOutputParameter()));
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("解析参数异常" + e);
        }
    }

    //根据任务中场景id查询并组装用例的case
    private boolean scenestart(Performtasks performtasks, variable variablelist) {

        variablelist.getFailcase().setPerformtasksid(performtasks.getId());//添加任务id

        //执行任务id查场景组id
        String scenegroupid = performtasksService.selectscenegroupid(performtasks.getId());

        if (StringUtils.isEmpty(scenegroupid)) {
            logger.info("没有场景id");
            return false;
        }

        String[] split = scenegroupid.split(",");
        for (String str : split) {
            variablelist.getFailcase().setSceneid(Integer.parseInt(str));//添加场景id
            //查询场景id的数据
            Sceneclassification scene = sceneclassificationService.selectscenelist(Integer.parseInt(str));
            String casegroup = scene.getCasegroup();
            if (StringUtils.isEmpty(casegroup)) {
                logger.info(scene.getScenename() + "场景case为空");
                System.out.println(scene.getScenename() + "场景case为空");
                continue;
            }

            String[] split1 = casegroup.split(",");
            List<Caselist> caselist = new ArrayList<>();
            for (String str1 : split1) {
                //查询用例并加载到用例组中
                Caselist cas = caselistService.casestart(Integer.parseInt(str1));

                dataloading(cas, variablelist);//把各种数据加装到各参数中
                casetest(cas, variablelist);//执行用例
                variablelist.setJsonrequest(new JSONObject());
                variablelist.setJsonHeader(new JSONObject());
                variablelist.setJsonOutputParameter(new JSONObject());
            }

            variablelist.setResponsebody(new JSONObject());

        }
        return true;
    }

//查询报告
    @GetMapping("/selectreport")
    public RespBean selectreport() {
        Map<String, LocalDateTime> map = new HashMap<>();
        map.put("starttime", LocalDateTime.now().plusDays(-7).plusHours(14));
        map.put("endtime", LocalDateTime.now().plusHours(14));

        List<Report> reportlist = reportService.selectreportlist(map);
        List<Map> list = new ArrayList<>();
        for (Report report : reportlist) {
            List<Object> lis1 = new ArrayList<>();
            List<Object> lis2 = new ArrayList<>();
            Map<String, Object> maps = new HashMap<>();
            //添加解析正确&错误的用例名称
            String successfullist = report.getSuccessfullist();
            if (successfullist != null) {
                String[] sp1 = successfullist.split(",");
                for (String str1 : sp1) {
                    lis1.add(str1);
                }
            }
            String failurelist = report.getFailurelist();
            if (failurelist != null) {
                String[] sp2 = failurelist.split(",");
                for (String str2 : sp2) {
                    lis2.add(str2);
                }
            }

            //查询当前报告下错误日志信息
            List<Failcase> faillist = failcaseService.select(report.getId());
            report.setFailcaselist(faillist);

            maps.put("report", report);
            maps.put("Successfullist", lis1);
            maps.put("Failurelist", lis2);
            System.out.println(report);
            logger.info(String.valueOf(report));
            list.add(maps);
        }

        date.put("reportlist", list);

        return RespBean.sucess("查询成功", date);
    }
        //读取配置信息
    public ServerHosts Parameter(Performtasks performtasks){
        ServerHosts serverHosts = new ServerHosts();
        serverHosts.setUsername(performtasks.getUsername());
        serverHosts.setPassword(performtasks.getPassword());
        serverHosts.setHosturl(performtasks.getHosturl());
        return serverHosts;
    }

}
