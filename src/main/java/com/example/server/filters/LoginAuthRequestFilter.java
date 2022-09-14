package com.example.server.filters;

import com.alibaba.fastjson.JSONObject;
import com.example.server.constant.AuthorizationConstant;
import com.example.server.constant.ServerHosts;
import com.example.server.constant.UrlPath;
import com.example.server.pojo.Performtasks;
import com.example.server.utils.EncryptUtil;
import io.restassured.filter.FilterContext;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import io.restassured.spi.AuthFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import static io.restassured.RestAssured.given;

/**
 *
 * @author kentzhong
 * @date 2020/4/26
 */
@Component
@DependsOn("serverHosts")
public class LoginAuthRequestFilter implements AuthFilter {

    private static final Logger logger = LoggerFactory.getLogger(LoginAuthRequestFilter.class);
    private static String logintoken = null;





    @Override
    public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec, FilterContext ctx) {

        requestSpec.baseUri(ServerHosts.SERVER_HOST);
        replaceHeader(requestSpec);

        if (requestSpec.getURI().contains(UrlPath.OAUTH_login)//判断是否是登陆接口
//                || requestSpec.getURI().contains(UrlPath.DEFAULT_LOCATION)
//        ||requestSpec.getHeaders().hasHeaderWithName("Authorization")
//        ||requestSpec.getCookies().
        ||requestSpec.getCookies().hasCookieWithName("test_hbos_token")){
            logger.info(requestSpec.getURI() + " API IS AUTHOR");//打印接口名
        } else {
            requestSpec.contentType(ContentType.JSON);//加contentType信息头
            requestSpec = restAssuredLogin(requestSpec);//调登陆接口
        }

        return ctx.next(requestSpec, responseSpec);
    }

    public  FilterableRequestSpecification restAssuredLogin(FilterableRequestSpecification requestSpec) {

        logintoken = doRestAssuredAuth(requestSpec);//调登陆接口并获取登陆的logintoken
        if (logintoken != null){
            requestSpec = replaceHeader(requestSpec);

        }
        return requestSpec;
    }

    private FilterableRequestSpecification replaceHeader(FilterableRequestSpecification requestSpec){

        if (logintoken!=null){
            requestSpec.cookie("test_hbos_token",logintoken);
            requestSpec.cookie("test_hbos_his_token",logintoken);
        }
        return requestSpec;
    }

    private String doRestAssuredAuth(FilterableRequestSpecification requestSpec) {
//        String encryptPassword = EncryptUtil.encrypt(AuthorizationConstant.PASSWORD, AuthorizationConstant.PUBLIC_KEY);
        JSONObject requestParams = new JSONObject();
        requestParams.put("accountNo", AuthorizationConstant.USER_NAME);
//        requestParams.put("accountNo", username);
        requestParams.put("password", AuthorizationConstant.PASSWORD);
//        requestParams.put("password", password);

//        System.out.println("登陆账户："+username+"/"+password);
        String token =
                given().
                        body(requestParams.toJSONString()).
                        when().
                        post(UrlPath.OAUTH_login).
                        then().
                        extract().
                        path("data.token");
        return token;
    }


//    private void getDefaultLocation() {
//
//        Response response =
//                given().
//                        when().
//                        post(UrlPath.DEFAULT_LOCATION).then().extract().response();
//        k1Header = response.body().jsonPath().getString("body.defaultIns");
//        k2Header = response.body().jsonPath().getString("body.defaultLoc");

//    }

//    public static void switchLocation(String newOrg, String newLocation){
////        k1Header = newOrg;
////        k2Header = newLocation;
//    }


//    private FilterableRequestSpecification replaceHeader(FilterableRequestSpecification requestSpec){
//
//        if (logintoken!=null){
////            requestSpec.replaceHeader("Authorization", authorizationHeader);
//            requestSpec.cookie("test_hbos_token",logintoken,ServerHosts.HOST_HOST);
//        }
////        if (k1Header!=null){
////            requestSpec.replaceHeader("k1", k1Header);
////        }
////        if (k2Header!=null){
////            requestSpec.replaceHeader("k2", k2Header);
////        }
//        return requestSpec;
//    }


}
