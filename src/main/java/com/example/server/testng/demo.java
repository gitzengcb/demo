package com.example.server.testng;

import com.example.server.pojo.Report;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

//@Test(groups = "testclass")
public class demo{
    private static Logger logger = LoggerFactory.getLogger(demo.class);
    public List<String> Successful;
    public List<String> Failure;
    public static Report report=new Report();


    @DataProvider(name = "canshu")//参数传递用的别名
    public static Object[][] canshu(Method m) {//Method:获取拿这参数的test方法
        System.out.println(m.getName());
        return new Object[][] {
                { "Cedric", 36},
                { "Anne", 37},
        };
    }
    @DataProvider(name = "canshu1")//参数传递用的别名
    public static Object[][] canshu1(Method m) {//Method:获取拿这参数的test方法
        System.out.println(m.getName());
        return new Object[][] {
                new Object[]{36}
        };
    }
    @DataProvider(name = "canshu2")//参数传递用的别名
    public static Object[][] canshu2(Method m) {//Method:获取拿这参数的test方法
        System.out.println(m.getName());
        return new Object[][] {
                { "参数1",235},
                { "参数2",37},
        };
    }








//    @DataProvider(name = "canshu2")//参数传递用的别名
//    @Factory(dataProvider = "canshu2")//单个传值
//    public static Iterator<Object[]> canshu1(Method ms) {//Method:获取拿这参数的test方法
//        System.out.println(ms.getName());
//        Object[] s={};
//        s[0]=11111;
//        s[1]=22222;
//        s[2]=22222;
//        return s;
//    }

    @BeforeSuite
    public void t1(){
        System.out.println("BeforeSuite......");
    }
    @AfterSuite
    public void t2(){
        System.out.println("AfterSuite......");
    }
    @BeforeTest
    public void t3(){
        System.out.println("BeforeTest....");
    }
    @AfterTest
    public void t4(){
        System.out.println("AfterTest.........");

    }
    @BeforeClass
    public void t5(){
        System.out.println("BeforeClass.......");
        Successful = new ArrayList<>();
        Failure = new ArrayList<>();

    }
    @AfterClass
    public void t6(){
//        Iterator iterator = Failure.iterator();
//        String str1="";
//        while (iterator.hasNext()){
//            str1=str1+iterator.next();
//        }
//        Iterator iterators = Successful.iterator();
//        String str2="";
//        while (iterators.hasNext()){
//            str2=str2+iterators.next();
//        }
//        StringBuilder stringBuilder1 = new StringBuilder();
//        StringBuilder stringBuilder2 = new StringBuilder();
//        stringBuilder1.append(Successful);
//        stringBuilder2.append(Failure);

        String Successfuls ="";
        for (int a=0;a<Successful.size();a++){
            if (a+1<Successful.size()){
                Successfuls=Successfuls+ Successful.get(a) +",";
            } else{
                Successfuls=Successfuls+ Successful.get(a);
            }
        }
        String Failures ="";
        for (int a=0;a<Failure.size();a++){
            if (a+1<Failure.size()){
                Failures=Failures+ Failure.get(a) +",";
            } else{
                Failures=Failures+ Failure.get(a);
            }
        }
        report.setSuccessfullist(Successfuls);
        report.setFailurelist(Failures);
        System.out.println(Successfuls);
        System.out.println(Failures);
        logger.info(Successfuls);
        logger.info(Failures);
        System.out.println("AfterClass.........");
    }
    @BeforeMethod
    public void t7(){
        System.out.println("BeforeMethod........");
    }
    @AfterMethod
    public void t8(){
        System.out.println("AfterMethod");
    }





    @Test(groups = "ss",description = "方法名称1",dependsOnMethods = "test04")
    public void test01(){
        report.setBasesum(report.getBasesum()+1);
        System.out.println(1);
        logger.info("test01");
        if (true){
            report.setSuccesssum(report.getSuccesssum()+1);
            Successful.add("test01");

        }else {
            report.setErrorsum(report.getErrorsum()+1);
            Failure.add("test01");

        }
    }

    @Test(testName = "login",groups = "ss",description = "方法名称2")
    //dependsOnMethods = "test03"
    public void test02(){
        report.setBasesum(report.getBasesum()+1);
        System.out.println(2);
        logger.info("test02");
        if (true){
            report.setSuccesssum(report.getSuccesssum()+1);
        }else {
            report.setErrorsum(report.getErrorsum()+1);
            Failure.add("test02");

        }

    }


    
    @Test(groups = "broken",dependsOnGroups= "ss",dataProvider = "canshu1")//dependsOnMethods= {"login"},
    public void test03(Integer integer){
        report.setBasesum(report.getBasesum()+1);
        System.out.println(integer);
        logger.info("test03");
        if (true){
            report.setSuccesssum(report.getSuccesssum()+1);
            Successful.add("test03");
        }else {
            report.setErrorsum(report.getErrorsum()+1);
            Failure.add("test03");

        }
    }


    //参数传递方法dataProvider：指定参数方法的别名,循环执行test获取参数
    @Test(description = "方法名称4",dataProvider = "class2",dataProviderClass = canshu.class)
    public void test04(String s,Long i){
        report.setBasesum(report.getBasesum()+1);
        System.out.println("test04...."+"||");
        logger.info("test03");
        if (false){
            report.setSuccesssum(report.getSuccesssum()+1);
            Successful.add("test04");
        }else {
            report.setErrorsum(report.getErrorsum()+1);
            Failure.add("test04");

        }

    }

    @Test(groups = "ss")
    @Parameters({"testname1","testname2"})//@Optional指定选用参数
    public void test05(@Optional("testname1") String str2,@Optional("testname2")String str1){
        report.setBasesum(report.getBasesum()+1);
        System.out.println(str1+"......"+str2);
        logger.info("test05");
        if (false){
            report.setSuccesssum(report.getSuccesssum()+1);
            Successful.add("test05");
        }else {
            report.setErrorsum(report.getErrorsum()+1);
            Failure.add("test05");

        }
    }
    @Test(dataProvider = "class1",dataProviderClass = canshu.class)//dataProviderClass:跨类调参数
    public void test06(Integer integer){
        report.setBasesum(report.getBasesum()+1);
        System.out.println("test06...."+integer);
        logger.info("test06");

        if (false){
            report.setSuccesssum(report.getSuccesssum()+1);
            Successful.add("test06");

        }else {
            report.setErrorsum(report.getErrorsum()+1);
            Failure.add("test06");

        }
    }
}

