package co;

import com.example.server.testng.tongji;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.TestNG;

import java.util.ArrayList;
import java.util.List;

public class LogTest {

    private static Logger logger = LoggerFactory.getLogger(LogTest.class);
    private static tongji tong=new tongji();

    public static void main(String[] args) throws InterruptedException {
//        logger.trace("trace.......");
//        logger.debug("debug.......");
//        logger.info("info.......");
//        logger.warn("warn.......");
//        logger.error("error.......");
//        String exception = "exception";
//        String excepDesc = "exception desc";
//        String[] err = {"error", "error desc"};
//        //提供了占位符"{}"，使书写布局更加灵活。占位符减少了日志信息中字符串的拼接，减少了内存和cpu的性能消耗。
//        logger.info("problem: {}", exception);
//        logger.info("problem: {},{}", exception, excepDesc);
//        logger.info("problem: {},{}", err);
            String relativelyPath=System.getProperty("user.dir");
            System.out.println("工程根目录:"+relativelyPath);

            TestNG testNG = new TestNG();
            List<String> suites = new ArrayList<String>();
//        suites.add(ProcessTest.class.getClassLoader().getResource("testng.xml").getPath());
            suites.add("testng.xml");
//        suites.add(relativelyPath+"/testng.xml");
            testNG.setTestSuites(suites);
            testNG.run();
        System.out.println("总数"+ tong.getBasesum());
        System.out.println("错误"+ tong.getErrorsum());
        System.out.println("成功"+ tong.getSuccesssum());

//         等待执行结束，然后去执行失败用例
//            TestNG testNG1 = new TestNG();
//            List<String> suites1 = new ArrayList<String>();
//            Thread.sleep(5000);
//            suites1.add(relativelyPath+"/test-output/testng-failed.xml");
//            testNG1.setTestSuites(suites1);
//            testNG1.run();
//            logger.info("日志成功了");

        }



    }
