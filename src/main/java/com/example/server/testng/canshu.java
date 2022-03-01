package com.example.server.testng;

import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;

public class canshu {
    @DataProvider(name = "class1")
    public static Object[][] createData1(Method m) {
        System.out.println("谁拿了我的参数。。。"+m.getName());
        return new Object[][] {
                new Object[]{9999},
                new Object[]{8888},
//                new Object[7777],
//                new Object[6666],
        };
    }
    @DataProvider(name = "class2")//参数传递用的别名
    public static Object[][] canshu2(Method m) {//Method:获取拿这参数的test方法
        System.out.println(m.getName());
        return new Object[][] {
                {"[23434,36536,45775678]",35346L},
                { "Anne", 37L},
                {"saric",984564654678957975L}
        };
    }
}
