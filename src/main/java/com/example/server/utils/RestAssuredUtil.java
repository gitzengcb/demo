package com.example.server.utils;

import com.example.server.constant.ServerHosts;
import com.example.server.filters.LoginAuthRequestFilter;
import com.example.server.pojo.Performtasks;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.HeaderConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author kentzhong
 */
@Component
@DependsOn(value = "serverHosts")
public class RestAssuredUtil {

    private static RequestSpecification requestSpec = null;
    private static ResponseSpecification responseSpec = null;
    private static ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
    private static RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();




    @PostConstruct
    public void init(){
//
//        requestSpecBuilder.setBaseUri(ServerHosts.SERVER_HOST);
//        requestSpec = requestSpecBuilder.build();
//
//        System.out.println("初始化域名："+ServerHosts.SERVER_HOST);
        //add logging filter when then servlet init
        RestAssured.filters(new LoginAuthRequestFilter(),new RequestLoggingFilter(), new ResponseLoggingFilter());

//        RestAssured.filters(new RequestLoggingFilter(), new LoginAuthRequestFilter());
    }

    public static ResponseSpecification getDefaultResponseSpecification(){
        responseSpecBuilder.expectStatusCode(200);
        responseSpec = responseSpecBuilder.build();
        return responseSpec;
    }

    public static void setAuthHeader(String authHeader){
        requestSpecBuilder.addHeader("authorization", authHeader);
        requestSpec = requestSpecBuilder.build();
    }

    public static RequestSpecification getRequestSpec(){return requestSpec;
    }

    public static RequestSpecification switchLocation(String newOrg, String newLocation){
        requestSpecBuilder.setConfig(RestAssured.config().headerConfig(HeaderConfig.headerConfig().overwriteHeadersWithName("k1", "k2")));
        requestSpecBuilder.addHeader("k1", newOrg);
        requestSpecBuilder.addHeader("k2", newLocation);
        requestSpec = requestSpecBuilder.build();
        return requestSpec;
    }

    public void reset(){
        RestAssured.reset();
    }
}
