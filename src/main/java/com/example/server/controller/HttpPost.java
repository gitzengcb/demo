package com.example.server.controller;

import com.example.server.mapper.CcUsernameInfosMapper;
import com.example.server.pojo.Person;
import com.example.server.pojo.Persons;
import com.example.server.pojo.Selectbug;
import com.example.server.publics.RespBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;

@RestController
public class HttpPost {
    public static Logger logger=LoggerFactory.getLogger(HttpPost.class);
    @Autowired(required = false)
    private CcUsernameInfosMapper ccUsernameInfosMapper;

    @PostMapping("httppost")
//    @ResponseBody
    public RespBean httppost(@Valid @RequestBody Persons persons, BindingResult bindingResult) {
        //BindingResult 获取Valid返回的Message数据
        if (bindingResult.hasErrors()){
            return RespBean.error(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        Person person = new Person();
        person.setAge(persons.getAge());
        person.setName(persons.getName());
        HashMap hashMap = new HashMap();
        hashMap.put("yes", person);
        hashMap.clone();
        logger.info(String.valueOf(hashMap));
//        System.out.printf(String.valueOf(hashMap.get("yes")));
        return RespBean.sucess("成功", hashMap);
    }
    @GetMapping("select/buglist")
//    @ResponseBody
    public RespBean httpget(@Valid Selectbug uuser, BindingResult bindingResult){
        //BindingResult 获取Valid返回的Message数据
        if (bindingResult.hasErrors()){
            return RespBean.error(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("name",uuser.getName());
//        System.out.printf(uuser.getName());
        logger.info(uuser.getName());

        return RespBean.sucess("成功",map);

    }
}
