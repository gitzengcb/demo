package com.example.server.utils;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * @Classname jsonFileToBean
 * @Date 2021/5/11 4:17 下午
 * @Created by lxt
 * @Description jsonFileToBean
 */
public class jsonFileToJSONObject {
    public JSONObject jsonFileToJSONObject(String path) throws IOException{
        String paths = System.getProperty("user.dir")+path;//读取json文件路径
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> readDataMap = mapper.readValue(new File(paths), Map.class);//把json存储到map集合中
        JSONObject json = new JSONObject(readDataMap);//
        return json;
    }


//    public static void main(String[] args) throws IOException{
//        jsonFileToJSONObject jts =new jsonFileToJSONObject();
//        JSONObject s = jts.jsonFileToJSONObject(MisHospitalUrlPath.LIST_PAGE_REGISTER_INFO_BY_PARAM_JSON_PATH);//传json文件路径
//        s.get("orderKey");
//        System.out.println(s.get("orderKey"));
//    }
}
