package com.example.server.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.server.pojo.*;
import com.example.server.publics.RespBean;
import com.example.server.service.ICaselistService;
import com.example.server.service.IFailcaseService;
import com.example.server.service.IReportService;
import com.example.server.service.ISceneclassificationService;
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

    //执行任务
    @PostMapping("startcase")
    public RespBean startcase(@RequestBody Performtasks performtasks) {
        logger.error("");
        variable variablelist = new variable();
        //用例方法执行
        if (!scenestart(performtasks, variablelist)) {
            return RespBean.error("执行场景不能为空");
        }
        Report report = variablelist.getReport();
//        List<Failcase> failcaselist = variablelist.getFailcaselist();
        System.out.println("执行用例数量总数" + report.getBasesum());
        System.out.println("用例成功数量总数" + report.getSuccesssum());
        System.out.println("用例失败数量总数" + report.getErrorsum());
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

    //发送钉钉消息
    private void dindinreport() {
        //Map<String, Object> map
//        String str = "." + "Basesum:" + map.get("Basesum") + "\n" + "Successsum:" + map.get("Successsum") + "\n"
//                + "Errorsum:" + map.get("Errorsum")+"\n"+"Successfullist:"+map.get("Successfullist")+"\n"
//                +"Failurelist"+map.get("Failurelist");
        JSONObject body = new JSONObject();
        body.put("msgtype", "text");
        body.put("text", ".");

        Response res =
                given()
//                        .header("Content-Type","application/json")
                        .body(body.toJSONString())
                        .when()
                        .post("robot/send?access_token=e864a30490b605fed8455e45fb05aee0ed74684555c65e824253a758e82a6d2e")
                        .then()
//                        .body(caselist.getAsserts(),equalTo(caselist.getAssertresult()))
                        .spec(RestAssuredUtil.getDefaultResponseSpecification())
                        .extract()
                        .response();
        System.out.println(res.body().print());
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
        JSONObject jsonInputParameter = variablelist.getJsonInputParameter();
        JSONObject jsonrequest = variablelist.getJsonrequest();

        if (!jsonInputParameter.isEmpty()) {
            Set<String> set = jsonInputParameter.keySet();
            for (String s : set) {
                jsonrequest.put(s, jsonInputParameter.get(s));
                System.out.println("输入参数修改" + s + "...." + jsonInputParameter.get(s));
            }
        }
        Map<String, Object> requestmap = new HashMap<>();
        if (!jsonrequest.isEmpty()) {
            Set<String> requestset = jsonrequest.keySet();
            for (String r : requestset) {
                requestmap.put(r, jsonrequest.get(r));
            }
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

        responsetest(response, caselist, variablelist);
    }


    private void Posttest(Caselist caselist, variable variablelist) {
        JSONObject jsonInputParameter = variablelist.getJsonInputParameter();
        JSONObject jsonrequest = variablelist.getJsonrequest();
        if (!jsonInputParameter.isEmpty()) {
            Set<String> set = jsonInputParameter.keySet();
            for (String s : set) {
                jsonrequest.put(s, jsonInputParameter.get(s));
                System.out.println("输入参数修改" + s + "...." + jsonInputParameter.get(s));
            }
        }

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
        responsetest(response, caselist, variablelist);
    }


    //断言
    private void responsetest(Response response, Caselist caselist, variable variablelist) {
        JsonPath path = response.body().jsonPath();
        Report report = variablelist.getReport();
        JSONObject jsonOutputParameter = variablelist.getJsonOutputParameter();
        JSONObject responsebody = variablelist.getResponsebody();
        try {
            if (path.getInt("code") == 200) {
                StringBuilder sb = new StringBuilder("");
                Object o = path.get(caselist.getAsserts());
                sb.append(o);
                //断言
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
    private void jsonOrNull(Caselist caselist, variable variablelist) {
        try {
            if (StringUtils.isNotEmpty(caselist.getCaserequest())) {
                variablelist.setJsonrequest(JSONObject.parseObject(caselist.getCaserequest()));
//                jsonrequest = JSONObject.parseObject(caselist.getCaserequest());
            }
            if (StringUtils.isNotEmpty(caselist.getHeader())) {
                variablelist.setJsonHeader(JSONObject.parseObject(caselist.getHeader()));
//                jsonHeader = JSONObject.parseObject(caselist.getHeader());
            }
            if (StringUtils.isNotEmpty(caselist.getInputParameter())) {
                variablelist.setJsonInputParameter(JSONObject.parseObject(caselist.getInputParameter()));
//                jsonInputParameter = JSONObject.parseObject(caselist.getInputParameter());
            }
            if (StringUtils.isNotEmpty(caselist.getOutputParameter())) {
                variablelist.setJsonOutputParameter(JSONObject.parseObject(caselist.getOutputParameter()));
//                jsonOutputParameter = JSONObject.parseObject(caselist.getOutputParameter());
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("解析参数异常" + e);
        }
    }

    //根据任务中场景id查询并组装用例的case
    private boolean scenestart(Performtasks performtasks, variable variablelist) {

        variablelist.getFailcase().setPerformtasksid(performtasks.getId());//添加任务id
        String scenegroupid = performtasks.getScenegroupid();
        if (StringUtils.isEmpty(scenegroupid)) {
            System.out.println("场景为空返return");
            return false;
        }
        String[] split = scenegroupid.split(",");
        for (String str : split) {
            variablelist.getFailcase().setSceneid(Integer.parseInt(str));//添加场景id
            Sceneclassification scene = sceneclassificationService.scenestart(Integer.parseInt(str));
            String s = scene.getCasegroup();
            if (StringUtils.isEmpty(s)) {
                logger.info(scene.getScenename() + "场景case为空");
                System.out.println(scene.getScenename() + "场景case为空");
                continue;
            }
            String[] split1 = s.split(",");
            List<Caselist> caselist = new ArrayList<>();
            for (String str1 : split1) {
                Caselist casestart = caselistService.casestart(Integer.parseInt(str1));
                caselist.add(casestart);
            }
            for (Caselist cas : caselist) {
                jsonOrNull(cas, variablelist);//把各种数据加装到各参数中
                casetest(cas, variablelist);//执行用例
                variablelist.setJsonrequest(new JSONObject());
                variablelist.setJsonHeader(new JSONObject());
                variablelist.setJsonInputParameter(new JSONObject());
                variablelist.setJsonOutputParameter(new JSONObject());
//                jsonrequest=new JSONObject();
//                jsonHeader =new JSONObject();
//                jsonInputParameter =new JSONObject();
//                jsonOutputParameter =new JSONObject();
            }
            variablelist.setResponsebody(new JSONObject());
//            responsebody=new JSONObject();//执行完场景后初始化变量参数
        }
        return true;
    }

    //    @PostMapping("/testngstart")
//    public RespBean testngstart() {
////        String relativelyPath=System.getProperty("user.dir");
////        System.out.println("工程根目录:"+relativelyPath);
//
//        TestNG testNG = new TestNG();
//        List<String> suites = new ArrayList<String>();
////        suites.add(ProcessTest.class.getClassLoader().getResource("testng.xml").getPath());
//        suites.add("testng.xml");
////        suites.add(relativelyPath+"/testng.xml");
//        testNG.setTestSuites(suites);
//        testNG.run();
//
////         等待执行结束，然后去执行失败用例
////        TestNG testNG1 = new TestNG();
////        List<String> suites1 = new ArrayList<String>();
////        Thread.sleep(5000);
////        suites1.add(relativelyPath+"/test-output/testng-failed.xml");
////        testNG1.setTestSuites(suites1);
////        testNG1.run();
//        //报告插库
//
//
//        Map<Object, Integer> map = new HashMap();
//        map.put("Basesum", demo.report.getBasesum());
//        map.put("Errorsum", demo.report.getErrorsum());
//        map.put("Successsum", demo.report.getSuccesssum());
//        Map<Object, Object> date = new HashMap();
//        date.put("tong", map);
//        date.put("Successfullist", demo.report.getSuccessfullist());
//        date.put("Failurelist", demo.report.getFailurelist());
//
//
//        demo.report.setCreate_time(LocalDateTime.now().plusHours(14));
//        System.out.println("新增参数：" + demo.report);
//        reportService.insertreport(demo.report);
//
//
//        logger.info("成功用例列表：" + demo.report.getSuccessfullist());
//        logger.info("失败用例列表：" + demo.report.getFailurelist());
//        logger.info("用例总数" + demo.report.getBasesum() + "," + "失败用例数" + demo.report.getErrorsum() + "," + "成功用例数" + demo.report.getSuccesssum());
//
//        //初始化测试报告数据
//        new Initialization().testngstart_s();
//        return RespBean.sucess("执行成功", date);
//
//    }
//查询任务
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


}
