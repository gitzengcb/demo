package com.example.server.controller;


import com.example.server.pojo.Interface;
import com.example.server.publics.RespBean;
import com.example.server.service.IInterfaceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zengchengbing
 * @since 2022-01-10
 */
@RestController
//@RequestMapping("/interface")
public class InterfaceController {
    private static Logger logger = LoggerFactory.getLogger(ClassificationController.class);
    @Autowired
    IInterfaceService interfaceService;

    @PostMapping("insert/interface")
    public RespBean insertinterface(@Valid @RequestBody Interface inputlist, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return RespBean.error(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        inputlist.setCreatetime(LocalDateTime.now());
        interfaceService.insertinter(inputlist);
        return RespBean.sucess("接口新增成功");
    }
    @PostMapping("update/interface")
    public RespBean updateinterface(@Valid @RequestBody Interface inputlist,BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return RespBean.error(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        inputlist.setUpdateTime(LocalDateTime.now());
        interfaceService.updateinset(inputlist);
        return RespBean.sucess("编辑接口成功");
    }
    @PostMapping("delect/interface")
    public RespBean delectinterface(@RequestBody Interface inputlist){
        interfaceService.delectinter(inputlist);
        return RespBean.sucess("删除接口成功");
    }
    @PostMapping("select/interface")
    public RespBean selectinterface(@RequestBody Interface inputlist){
        List<Interface> selectinter = interfaceService.selectinter(inputlist);
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("interfacelist",selectinter);
        return RespBean.sucess("查询成功",objectObjectHashMap);
    }




}
