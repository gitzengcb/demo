package co;

import com.example.server.utils.RestAssuredUtil;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class aaa {
    public static void main(String[] args) throws Exception {

//        Long timestamp = System.currentTimeMillis();
//        String secret = "this is secret";
//
//        String stringToSign = timestamp + "\n" + secret;
//        Mac mac = Mac.getInstance("HmacSHA256");
//        mac.init(new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256"));
//        byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
//        String sign = URLEncoder.encode(new String(Base64.encodeBase64(signData)), "UTF-8");
//        System.out.println(sign);
//        JSONObject json = new JSONObject();
//        JSONObject jsontext = new JSONObject();
//        jsontext.put("content", "23534我就是我, 是不一样的烟火");
//        json.put("isAtAll", true);
//        json.put("text", jsontext);
//        json.put("msgtype", "text");
//        String url = "https://oapi.dingtalk.com/robot/send?access_token=acf4d6b63b23d333c431ce403bceddc3020942800d8b8b527417320cb2e003ef"+"&timestamp="+timestamp+"&sign="+sign;
//
//
//        Response response =
//                given()
//                        .header("Content-Type", "application/json;charset=utf-8")
//                        .body(json.toString())
//                        .when()
//                        .post(url)
//                        .then()
//                        .extract()
//                        .response();
//        String print = response.body().print();
//        System.out.println(print);

        Map<String, Object> requestmap = new HashMap<>();
        String url="http://hbos-test.cfuture.shop/nurse/doctor-order-execute";
        requestmap.put("identity","rescue");
        requestmap.put("menuId",10532);
        Response response=
                given()
                        .params(requestmap)
                        .when()
                        .get(url)
                        .then()
//                .body(caselist.getAsserts(),equalTo(caselist.getAssertresult()))
                        .spec(RestAssuredUtil.getDefaultResponseSpecification())
                        .extract().response();
        System.out.println(response.body().print());

    }
}
