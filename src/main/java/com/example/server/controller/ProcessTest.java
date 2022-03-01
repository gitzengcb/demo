package com.example.server.controller;//package com.example.server.controller;
//
//import com.example.server.pojo.Initialization;
//import com.example.server.publics.RespBean;
//import com.example.server.testng.tongji;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.testng.TestNG;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
////LogFactory.getLog(ProcessTest.class );
//@RestController
//public class ProcessTest {
//    private static Logger logger = LoggerFactory.getLogger(ProcessTest.class);
//    private static tongji tong=new tongji();
//
//    @PostMapping("/testngstartold")
//    public RespBean testngstartold() throws InterruptedException {
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
//
//        Map<Object, Integer> map = new HashMap();
//        map.put("Basesum",tong.getBasesum());
//        map.put("Errorsum",tong.getErrorsum());
//        map.put("Successsum",tong.getSuccesssum());
//        Map<Object, Object> date = new HashMap();
//        date.put("Report",map);
//        date.put("Successfullist",tong.getSuccessfullist());
//        date.put("Failurelist",tong.getFailurelist());
//
//
//
//
//        logger.info("成功用例列表："+tong.getSuccessfullist());
//        logger.info("失败用例列表："+tong.getFailurelist());
//        logger.info("用例总数"+ tong.getBasesum()+","+"失败用例数"+ tong.getErrorsum()+","+"成功用例数"+ tong.getSuccesssum());
//
//        //初始化测试报告数据
//        new Initialization().testngstartold_s();
//        return RespBean.sucess("执行成功",date);
//
//    }
//
//    private static void getErrorStreamlist(Process process) throws IOException {
//        //取得命令结果的输出流
//        InputStream inputStreampwd=process.getErrorStream();
//        //用一个读输出流类去读
//        InputStreamReader isr=new InputStreamReader(inputStreampwd);
//        //用缓冲器读行
//        BufferedReader br=new BufferedReader(isr);
//        String line=null;
//        //直到读完为止
//        while((line=br.readLine())!=null)
//        {
//            System.out.println(line);
//        }
//    }
//
//    private static void InputStreamlist(Process process) throws IOException {
//        //取得命令结果的输出流
//        InputStream inputStreampwd=process.getInputStream();
//        //用一个读输出流类去读
//        InputStreamReader isr=new InputStreamReader(inputStreampwd);
//        //用缓冲器读行
//        BufferedReader br=new BufferedReader(isr);
//        String line=null;
//        //直到读完为止
//        while((line=br.readLine())!=null)
//        {
//            System.out.println(line);
//        }
//    }
//}
