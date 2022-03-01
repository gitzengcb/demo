package com.example.server.controller;

import com.example.server.mapper.CcUsernameInfosMapper;
import com.example.server.pojo.CcUsernameInfos;
import com.example.server.pojo.Userid;
import com.example.server.publics.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class test01 {
    @Autowired(required = false)
    private CcUsernameInfosMapper ccUsernameInfosMapper;



    @PostMapping("/userid")
    @ResponseBody
//    @Insert()
    public RespBean userid(@RequestBody Userid userid) {
//        CcUsernameInfos ccUsernameInfos = ccUsernameInfosMapper.selectById(userid.getId());
//        CcUsernameInfos ccUsernameInfos = ccUsernameInfosMapper.userdetails(userid.getId());
        List<CcUsernameInfos> list = ccUsernameInfosMapper.userdetails(userid.getId());



        Map map = new HashMap();
        System.out.println("查询的用户：" + list);

        if (null !=list){
            map.put("usernameall",list);
            return RespBean.sucess("成功",map);
        }
        return RespBean.error("查询失败");
    }



}
