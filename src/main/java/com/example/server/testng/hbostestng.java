package com.example.server.testng;

import com.alibaba.fastjson.JSONObject;
import com.example.server.Applocation;
import com.example.server.service.CustomTestNGListenerService;
import com.example.server.utils.RestAssuredUtil;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.testng.annotations.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
//import io.restassured.module.jsv.JsonSchemaValidator.*;


@Listeners({CustomTestNGListenerService.class})
@SpringBootTest(classes = {Applocation.class, hbostestng.class})
@Component
public class hbostestng {
//    private static Logger logger = LoggerFactory.getLogger(demo.class);

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
    }
    @AfterClass
    public void t6(){
        System.out.println("AfterClass.......");
    }
    @BeforeMethod
    public void t7(){
        System.out.println("BeforeMethod........");
    }
    @AfterMethod
    public void t8(){
        System.out.println("AfterMethod");
    }




    @Test(testName = "jiekouming")
    public void test11(){
        String url="kapi/hbos-nurse-station/carePlan/queryRecommendTemplates";
//        String s={"healthcareRecordId":"218474"};
        JSONObject rep = new JSONObject();
        rep.put("healthcareRecordId","218474");
        Response response=
                given()
                        .body(rep.toJSONString())
                        .when()
                        .post(url)
                        .then()
                        .body("code", equalTo(200))
                        .spec(RestAssuredUtil.getDefaultResponseSpecification())
                        .extract()
                        .response();

        boolean success = response.body().jsonPath().getBoolean("success");
        if (success){
//            logger.info("接口成功");
            System.out.println(success);
        }else {
//            logger.error("接口失败");
        }


    }

    @Test
    public void test12(){
        String url="kapi/apex-sso/getLoginUserInfo";
        JSONObject rep = new JSONObject();
        Response response=
                given().
                        body(rep.toJSONString()).
                        when().post(url).
                        then().body("code", equalTo(200)).
                        spec(RestAssuredUtil.getDefaultResponseSpecification()).extract().response();
    }



}
