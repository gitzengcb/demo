package com.example.server.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.server.pojo.Caselist;
import com.example.server.pojo.Sceneclassification;
import com.example.server.publics.RespBean;
import com.example.server.service.ICaselistService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zengchengbing
 * @since 2022-01-11
 */
@RestController
//@RequestMapping("/caselist")
public class CaselistController {
    private static Logger logger = LoggerFactory.getLogger(ClassificationController.class);
    @Autowired
    ICaselistService caselistService;

    @PostMapping("insert/caselist")
    public RespBean insertcaselist(@Valid @RequestBody Caselist caselist, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return RespBean.error(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        System.out.println("入参body"+caselist.getCaserequest());

        JSONObject jsonObject = JSONObject.parseObject(caselist.getCaserequest());

//        jsonObject.put("code",300);
//        System.out.println("变参数body："+jsonObject);
//        caselist.setCaserequest(jsonObject.toString());

        caselist.setCreatetime(LocalDateTime.now().plusHours(13));
        caselistService.insertcase(caselist);
        return RespBean.sucess("新增成功");
    }
    @PostMapping("update/caselist")
    public RespBean updatecaselist(@Valid @RequestBody Caselist caselist,BindingResult bindingResult ){
        if (bindingResult.hasErrors()){
            return RespBean.error(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        caselist.setUpdateTime(LocalDateTime.now().plusHours(13));
        caselistService.updatecase(caselist);
        return RespBean.sucess("修改用例成功");
    }
    @PostMapping("select/caselist")
    public RespBean selectcaselist(@RequestBody Caselist caselist){
        List<Caselist> selectlist = caselistService.selectcase(caselist);
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("caselist",selectlist);
        return RespBean.sucess("查询成功",objectObjectHashMap);
    }
    @PostMapping("delete/caselist")
    public RespBean deletecaselist(@RequestBody Caselist caselist){
        caselistService.deletecase(caselist);
        return RespBean.sucess("删除用例成功");
    }

    @PostMapping("select/groupcase")
    public RespBean selectgroupcase(@RequestBody Sceneclassification sceneclassification){
        String casegroup = sceneclassification.getCasegroup();
        String[] str = casegroup.split(",");
        List<Integer> list = new ArrayList<>();
        for (String i:str){
            list.add(Integer.parseInt(i));
        }
        List<Caselist> groupcaselist = caselistService.selectgroupcase(list);
        HashMap<String, Object> caselistmap = new HashMap<>();
        caselistmap.put("groupcaselist",groupcaselist);
        return RespBean.sucess("查询成功",caselistmap);
    }
    @PostMapping("select/vaguecase")
    public RespBean selectvaguecase(@RequestBody Caselist caselist){
        String caseTitle = caselist.getCaseTitle();
        if (caseTitle.isEmpty()){
            return RespBean.error("不能空查询case名称");
        }
        List<Caselist> listbase = caselistService.selectvaguecase(caselist);
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("listbase",listbase);
        return RespBean.sucess("查询成功",objectObjectHashMap);
    }
}
