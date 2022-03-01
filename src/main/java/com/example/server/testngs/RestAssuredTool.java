package com.example.server.testngs;
//import com.iworkh.test.restassured.domain.vo.JsonDataResult;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.path.json.config.JsonPathConfig;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Value;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

/**
 * RestAssuredTool工具类
 *
 * @author:
 * @date:
 */
public class RestAssuredTool {
    @Value("${testng.port}")
    private static int port;

    @Value("${testng.host}")
    private static String siteBaseURI;

    static {
        // 这可以修改为从配置文件读取
//        String siteBaseURI = "http://47.101.191.102";
//        int port = 8082;
        RestAssured.baseURI = siteBaseURI;
        RestAssured.port = port;
        JsonPath.config = new JsonPathConfig("UTF-8");
    }

    public static RequestSpecification restClient() {
        // 认证等操作，都可以在这统一处理
        return given();
    }

    public static RequestSpecification restClientWithHeader(Headers headers) {
        // 认证等操作，都可以在这统一处理
        return given().headers(headers);
    }

    // get
    public static Response get(String url) {
        return restClient().get(url);
    }

    public static Response getWithParams(String url, Map<String, ?> params) {
        return restClient().params(params).get(url);
    }

    public static Response getWithQueryParams(String url, Map<String, ?> params) {
        return restClient().queryParams(params).get(url);
    }

    public static Response getWithFormParams(String url, Map<String, ?> params) {
        return restClient().formParams(params).get(url);
    }

    public static <T> Response getWithJson(String url, T data) {
        return restClient().contentType(ContentType.JSON).body(data).get(url);
    }

    // post
    public static Response postWithParams(String url, Map<String, ?> params) {
        return restClient().params(params).post(url);
    }

    public static Response postWithFormParams(String url, Map<String, ?> params) {
        return restClient().formParams(params).post(url);
    }

    public static <T> Response postWithJson(String url, T data) {
        return restClient().contentType(ContentType.JSON).body(data).post(url);
    }

    // put
    public static Response putWithParams(String url, Map<String, ?> params) {
        return restClient().params(params).put(url);
    }

    public static Response putWithFormParams(String url, Map<String, ?> params) {
        return restClient().formParams(params).put(url);
    }

    public static <T> Response putWithJson(String url, T data) {
        return restClient().contentType(ContentType.JSON).body(data).put(url);
    }

    // delete
    public static Response delete(String url) {
        return restClient().delete(url);
    }

    public static <T> Response deleteWithJson(String url, T data) {
        return restClient().contentType(ContentType.JSON).body(data).delete(url);
    }

    // validate response
    public static void validateStatusCode(Response response, int expectedStatusCode) {
        response.then().statusCode(expectedStatusCode);
    }

    public static <T> void validateEqualTo(Response response, String path, T expectedValue) {
        response.then().body(path, equalTo(expectedValue));
    }

    public static <T> void validateHasItems(Response response, String path, T... expectedValue) {
        response.then().body(path, hasItems(expectedValue));
    }

    // convert
//    public static JsonDataResult<Map<String, ?>> asJsonDataResult(Response response) {
//        return response.as(new TypeRef<JsonDataResult<Map<String, ?>>>() {});
//    }

    public static <T> T asGeneric(Response response, TypeRef<T> typeRef) {
        return response.as(typeRef);
    }

    public static <T> T asCls(Response response, Class<T> cls) {
        return response.as(cls);
    }
}

