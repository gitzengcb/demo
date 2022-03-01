package com.example.server.testng;

import com.alibaba.fastjson.JSONObject;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class test1 {


    @Test
    public void test001(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("username","898989");
        map.put("password","123456");
        System.out.printf(String.valueOf(map));
        JSONObject json = new JSONObject(map);
        Response response=
                given().
                        body(json.toJSONString()).
                        when().
                        post("/login").
                        then().body("code",equalTo(200)).
                        extract().response();

        ResponseBody body = response.body();
        String data = body.jsonPath().getString("data");
        System.out.printf(data);

    }
}
