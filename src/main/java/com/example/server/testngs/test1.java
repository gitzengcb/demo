package com.example.server.testngs;

//import org.junit.Test;


import java.util.ArrayList;

public class test1 {
    public static void main(String[] args) {
        ArrayList<Object> list = new ArrayList<>();
        list.add("235");
        ArrayList<String> list1 = (ArrayList<String>)list.clone();
        list1.add("3465476");
        list.size();
        System.out.println(list.size());


    }


//    @Test
//    public void test001(){
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("username","898989");
//        map.put("password","123456");
//        System.out.printf(String.valueOf(map));
//        JSONObject json = new JSONObject(map);
//        Response response=
//                given().
//                        body(json.toJSONString()).
//                        when().
//                        post("/login").
//                        then().body("code",equalTo(200)).
//                        extract().response();
//
//        ResponseBody body = response.body();
//        String data = body.jsonPath().getString("data");
//        System.out.printf(data);

    }



