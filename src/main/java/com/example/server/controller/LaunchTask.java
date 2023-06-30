package com.example.server.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.server.constant.ServerHosts;
import com.example.server.generator.DDURLPOSTCase;
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
public class LaunchTask {
    private static Logger logger = LoggerFactory.getLogger(LaunchTask.class);
    private static Map<String, Object> date = new HashMap<>();

    public static Map<String,Object> headersmap=new HashMap<>();
    public static String url;//域名

    private static TestrecordsController testrecordsController=new TestrecordsController();
    private static ReportController reportController=new ReportController();
    private static Report report=new Report();
    @Autowired
    ISceneclassificationService sceneclassificationService;
    @Autowired
    ICasesService casesService;
    @Autowired
    IPerformtasksService performtasksService;

    //执行任务
    @PostMapping("startcase")
    public RespBean startcase(@RequestBody Performtasks performtasks) {
        //初始化参数
        variable variablelist = new variable();
        Parameter(performtasks);

        //用例执行
        if (!casestart(performtasks, variablelist)) {
            return RespBean.error("执行场景不能为空");
        }

        //发送钉钉报告
        Map map=dindinreport(report.getId());
        return RespBean.sucess("执行任务完成", map);
    }

    private Map dindinreport(Integer id) {
        Report selectreport = reportController.selectreport(id);
        Map<Object, Object> reportmap = new HashMap<>();
        reportmap.put("successsum",selectreport.getSuccesssum());
        reportmap.put("failsum",selectreport.getFailsum());
        reportmap.put("casesum",selectreport.getCasesum());
        reportmap.put("Successnamelist",selectreport.getSuccessnamelist());
        reportmap.put("failurenamelist",selectreport.getFailurenamelist());


        String str = "." + "执行用例case:" + reportmap.get("casesum") + "\n" + "执行通过case:" + reportmap.get("successsum") + "\n"
                + "执行失败case:" + reportmap.get("failsum") + "\n" + "执行成功用例名称:" + reportmap.get("successnamelist") + "\n"
                + "执行失败用例名称:" + reportmap.get("failurenamelist");
        JSONObject content = new JSONObject();
        JSONObject body = new JSONObject();
        content.put("content", str);
        body.put("msgtype", "text");
        body.put("text", content);
        DDURLPOSTCase.httpURLPOSTCase(body.toJSONString());
        return reportmap;
    }

    //通过请求方式(get&post)走向不同的请求方法里
    private void starttest(Cases cases,variable variablelist) {

        //加载域名与信息头数据
        loadingurlheaders(cases,variablelist);

      //通过请求方式(get&post)走向不同的请求方法里
        if (cases.getFace().getMethod().equals("post")) {
            Posttest(cases,variablelist);

        } else if (cases.getFace().getMethod().equals("get")){
            Gettest(cases,variablelist);

        } else {
            logger.error(cases.getCaseTitle() + "请求方式仅支持post与get");

        }
    }
    private void loadingurlheaders(Cases cases, variable variablelist) {
        url=cases.getFace().getDomainname();
        for (Map.Entry<String,Object> entry: variablelist.getJsonHeader().entrySet()){
            headersmap.put(entry.getKey(),entry.getValue());
        }
        System.out.println("取值成功URL="+url+"/"+"headersmap="+headersmap);
    }
    private void Gettest(Cases cases, variable variablelist) {
        String inputlist = cases.getInputlist();
        JSONObject jsonrequest = variablelist.getJsonrequest();
        //加载变量参数
        if (StringUtils.isNotEmpty(inputlist)){
            String[] split = inputlist.split(",");
            for (String str:split){
                if (variablelist.getResponsebody().containsKey(str)){
                    jsonrequest.put(str,variablelist.getResponsebody().get(str));
                }
            }

        }
        //转换成map
        Map<String, Object> requestmap=jsonrequest.getInnerMap();
        Response response =
                given()
                        .params(requestmap)
                        .when()
                        .get(cases.getFace().getInterfaceUrl())
                        .then()
//                .body(caselist.getAsserts(),equalTo(caselist.getAssertresult()))
                        .spec(RestAssuredUtil.getDefaultResponseSpecification())
                        .extract().response();

        responseAsserts(response, cases, variablelist);
    }
    private void Posttest(Cases cases, variable variablelist) {
        String inputlist = cases.getInputlist();
        JSONObject jsonrequest = variablelist.getJsonrequest();

        if (StringUtils.isNotEmpty(inputlist)){
            String[] split = inputlist.split(",");
            for (String str:split){
                if (variablelist.getResponsebody().containsKey(str)){
                    jsonrequest.put(str,variablelist.getResponsebody().get(str));//加载变量参数
                }
            }

        }
        Response response =
                given()
//                        .headers(jsonHeader)
                        .body(jsonrequest.toJSONString())
                        .when()
                        .post(cases.getFace().getInterfaceUrl())
                        .then()
//                        .body(caselist.getAsserts(),equalTo(caselist.getAssertresult()))
                        .spec(RestAssuredUtil.getDefaultResponseSpecification())
                        .extract()
                        .response();
        responseAsserts(response, cases, variablelist);//断言
    }
    //断言
    private void responseAsserts(Response response,Cases cases,variable variablelist) {
        JsonPath path = response.body().jsonPath();
        JSONObject jsonOutputParameter = variablelist.getJsonOutputParameter();
        JSONObject responsebody = variablelist.getResponsebody();
        try {
            if (path.getInt("code") == 200) {
                StringBuilder sb = new StringBuilder("");
                Object o = path.get(cases.getAsserts());
                sb.append(o);
                //断言开始，通过不同的断言类型区分断言
                if (cases.getAsserttype().equals("=")) {
                    if (sb.toString().equals(cases.getAssertresult())) {
                        cases.setAssertionresults(true);

                        //输出参数提取
                        if (!jsonOutputParameter.isEmpty()) {
                            Set<String> set = jsonOutputParameter.keySet();
                            for (String s : set) {
                                responsebody.put(s, path.get(jsonOutputParameter.getString(s)));
                            }
                        }
                    } else {
                        cases.setAssertionresults(false);
                    }
                }
                else if (cases.getAsserttype().equals(">")) {
                    if (Integer.valueOf(sb.toString()) > Integer.valueOf(cases.getAssertresult())) {
                        cases.setAssertionresults(true);
                        //输出参数组装
                        if (!jsonOutputParameter.isEmpty()) {
                            Set<String> set = jsonOutputParameter.keySet();
                            for (String s : set) {
                                responsebody.put(s, path.get(jsonOutputParameter.getString(s)));
                            }
                        }
                    } else {
                        cases.setAssertionresults(false);
                    }
                }
                else if (cases.getAsserttype().equals("<")) {
                    if (Integer.valueOf(sb.toString()) < Integer.valueOf(cases.getAssertresult())) {
                        cases.setAssertionresults(true);
                        //输出参数组装
                        if (!jsonOutputParameter.isEmpty()) {
                            Set<String> set = jsonOutputParameter.keySet();
                            for (String s : set) {
                                responsebody.put(s, path.get(jsonOutputParameter.getString(s)));
                            }
                        }
                    } else {
                        cases.setAssertionresults(false);
                    }
                }
                else if (cases.getAsserttype().equals(">=")) {
                    if (Integer.valueOf(sb.toString()) >= Integer.valueOf(cases.getAssertresult())) {
                        cases.setAssertionresults(true);
                        //输出参数组装
                        if (!jsonOutputParameter.isEmpty()) {
                            Set<String> set = jsonOutputParameter.keySet();
                            for (String s : set) {
                                responsebody.put(s, path.get(jsonOutputParameter.getString(s)));
                            }
                        }
                    } else {
                        cases.setAssertionresults(false);
                    }
                }
                else if (cases.getAsserttype().equals("<=")) {
                    if (Integer.valueOf(sb.toString()) <= Integer.valueOf(cases.getAssertresult())) {
                        cases.setAssertionresults(true);
                        //输出参数组装
                        if (!jsonOutputParameter.isEmpty()) {
                            Set<String> set = jsonOutputParameter.keySet();
                            for (String s : set) {
                                responsebody.put(s, path.get(jsonOutputParameter.getString(s)));
                            }
                        }
                    } else {
                        cases.setAssertionresults(false);
                    }
                }
                else {
                    cases.setAssertionresults(false);
                    logger.error("断言类型不支持：" + cases.getAsserttype());
                    System.out.println("断言类型不支持：" + cases.getAsserttype());
                }

            } else {
                cases.setAssertionresults(false);
            }
            testrecordsController.assertresultinsert(response.body().print(), cases, variablelist,report);
            reportController.addreport(cases,report);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            logger.error("异常:" + e.getMessage());
            cases.setAssertionresults(false);
            testrecordsController.assertresultinsert("异常捕获:" + e.getMessage(), cases, variablelist,report);
            reportController.addreport(cases,report);
        }

    }
    //取出入参的数据并转化为json
    private void dataloading(Cases cases, variable variablelist) {
        try {
            if (StringUtils.isNotEmpty(cases.getCaserequest())) {
                variablelist.setJsonrequest(JSONObject.parseObject(cases.getCaserequest()));
            }
            if (StringUtils.isNotEmpty(cases.getHeaders())) {
                variablelist.setJsonHeader(JSONObject.parseObject(cases.getHeaders()));
            }

            if (StringUtils.isNotEmpty(cases.getOutputParameter())) {
                variablelist.setJsonOutputParameter(JSONObject.parseObject(cases.getOutputParameter()));
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("解析参数异常" + e);
        }
    }
    //根据任务中场景id查询并组装用例的case
    private boolean casestart(Performtasks performtasks, variable variablelist) {
        //执行任务id查场景组id
        String scenegroupid = performtasksService.selectscenegroupid(performtasks.getId());
        if (StringUtils.isEmpty(scenegroupid)) {
            logger.info("没有场景id");
            return false;
        }
        //计算用例总数
        report.setCasesum(casesums(scenegroupid));

        //创建执行报告任务
        Integer reportid = reportController.reportinsert(performtasks,report);
        report.setId(reportid);

        String[] split = scenegroupid.split(",");
        //循环场景id
        for (String sceneid : split) {
            //组装场景下的caseid
            List caseidlist = caselists(sceneid);
            if (caseidlist.isEmpty()){
                continue;
            }

            //组装场景下用例集
            List<Cases> caselist = casesService.casestart(caseidlist);

            //循环执行case
            for (Cases cases : caselist) {
                cases.setPerformtasksid(performtasks.getId());
                cases.setSceneid(Integer.parseInt(sceneid));
                dataloading(cases, variablelist);//把各种数据加装到各参数中
                starttest(cases, variablelist);//执行用例
                //初始化
                variablelist.setJsonrequest(new JSONObject());
                variablelist.setJsonHeader(new JSONObject());
                variablelist.setJsonOutputParameter(new JSONObject());
            }

            variablelist.setResponsebody(new JSONObject());

        }
        return true;
    }

    private Integer casesums(String scenegroupid) {
        String[] split = scenegroupid.split(",");
        Integer casesum=0;
        for (String sceneid : split) {
            //组装场景下的caseid
            List caseidlist = caselists(sceneid);
            casesum=casesum+caseidlist.size();
        }
        return casesum;
    }

    //组装场景下的case
    private List caselists( String sceneid) {
        //查询场景下的用例id组
        Sceneclassification scenecase = sceneclassificationService.selectscenelist(Integer.parseInt(sceneid));
        String casegroup = scenecase.getCasegroup();
        List<Integer> caseidlist = new ArrayList<>();
        if (StringUtils.isEmpty(casegroup)) {
            logger.info(scenecase.getScenename() + "场景case为空");
            System.out.println(scenecase.getScenename() + "场景case为空");
        }else {
            String[] split1 = casegroup.split(",");

            for (String caseid : split1) {
                caseidlist.add(Integer.parseInt(caseid));
            }
        }
        return caseidlist;


    }

    //查询报告
//    @GetMapping("/selectreport")
//    public RespBean selectreport() {
//        Map<String, LocalDateTime> map = new HashMap<>();
//        map.put("starttime", LocalDateTime.now().plusDays(-7).plusHours(13));
//        map.put("endtime", LocalDateTime.now().plusHours(13));
//
//        List<Report> reportlist = reportService.selectreportlist(map);
//        List<Map> list = new ArrayList<>();
//        for (Report report : reportlist) {
//            List<Object> lis1 = new ArrayList<>();
//            List<Object> lis2 = new ArrayList<>();
//            Map<String, Object> maps = new HashMap<>();
//            //添加解析正确&错误的用例名称
//            String successfullist = report.getSuccessfullist();
//            if (successfullist != null) {
//                String[] sp1 = successfullist.split(",");
//                for (String str1 : sp1) {
//                    lis1.add(str1);
//                }
//            }
//            String failurelist = report.getFailurelist();
//            if (failurelist != null) {
//                String[] sp2 = failurelist.split(",");
//                for (String str2 : sp2) {
//                    lis2.add(str2);
//                }
//            }
//
//            //查询当前报告下错误日志信息
//            List<Failcase> faillist = failcaseService.select(report.getId());
//            report.setFailcaselist(faillist);
//
//            maps.put("report", report);
//            maps.put("Successfullist", lis1);
//            maps.put("Failurelist", lis2);
//            System.out.println(report);
//            logger.info(String.valueOf(report));
//            list.add(maps);
//        }
//
//        date.put("reportlist", list);
//
//        return RespBean.sucess("查询成功", date);
//    }
        //读取配置信息
    public ServerHosts Parameter(Performtasks performtasks){
        ServerHosts serverHosts = new ServerHosts();
        serverHosts.setUsername(performtasks.getUsername());
        serverHosts.setPassword(performtasks.getPassword());
        serverHosts.setHosturl(performtasks.getHosturl());
        return serverHosts;
    }

}
