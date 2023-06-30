package com.example.server.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.server.pojo.Cases;
import com.example.server.pojo.Sceneclassification;
import com.example.server.publics.RespBean;
import com.example.server.service.ICasesService;
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
public class CasesController {
    private static Logger logger = LoggerFactory.getLogger(ClassificationController.class);
    @Autowired
    ICasesService casesService;

    @PostMapping("insert/caselist")
    public RespBean insertcaselist(@Valid @RequestBody Cases cases, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return RespBean.error(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        System.out.println("入参body"+cases.getCaserequest());

        JSONObject jsonObject = JSONObject.parseObject(cases.getCaserequest());

//        jsonObject.put("code",300);
//        System.out.println("变参数body："+jsonObject);
//        caselist.setCaserequest(jsonObject.toString());

        cases.setCreatetime(LocalDateTime.now().plusHours(13));
        casesService.insertcase(cases);
        return RespBean.sucess("新增成功");
    }
    @PostMapping("update/caselist")
    public RespBean updatecaselist(@Valid @RequestBody Cases cases,BindingResult bindingResult ){
        if (bindingResult.hasErrors()){
            return RespBean.error(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        cases.setUpdateTime(LocalDateTime.now().plusHours(13));
        casesService.updatecase(cases);
        return RespBean.sucess("修改用例成功");
    }
    @PostMapping("select/caselist")
    public RespBean selectcaselist(@RequestBody Cases cases){
        List<Cases> selectlist = casesService.selectcase(cases);
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("cases",selectlist);
        return RespBean.sucess("查询成功",objectObjectHashMap);
    }
    @PostMapping("delete/caselist")
    public RespBean deletecaselist(@RequestBody Cases cases){
        casesService.deletecase(cases);
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
        List<Cases> groupcaselist = casesService.selectgroupcase(list);
        HashMap<String, Object> caselistmap = new HashMap<>();
        caselistmap.put("groupcaselist",groupcaselist);
        return RespBean.sucess("查询成功",caselistmap);
    }
    @PostMapping("select/vaguecase")
    public RespBean selectvaguecase(@RequestBody Cases cases){
        String caseTitle = cases.getCaseTitle();
        if (caseTitle.isEmpty()){
            return RespBean.error("不能空查询case名称");
        }
        List<Cases> listbase = casesService.selectvaguecase(cases);
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("listbase",listbase);
        return RespBean.sucess("查询成功",objectObjectHashMap);
    }
}
