package com.example.server.controller;


import com.example.server.pojo.Performtasks;
import com.example.server.publics.RespBean;
import com.example.server.service.IPerformtasksService;
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
 * @since 2022-01-12
 */
@RestController
//@RequestMapping("/performtasks")
public class PerformtasksController {

    @Autowired
    IPerformtasksService performtasksService;

    @PostMapping("insert/tasks")
    public RespBean inserttasks(@Valid @RequestBody Performtasks performtasks, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return RespBean.error(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        performtasks.setCreatetime(LocalDateTime.now().plusHours(13));
        int inserttasks = performtasksService.inserttasks(performtasks);
        if (inserttasks!=1) {
            return RespBean.error("新增任务失败");
        }
        return RespBean.sucess("新增任务成功");
    }
    @PostMapping("update/tasks")
    public RespBean updatetasks(@Valid @RequestBody Performtasks performtasks,BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return RespBean.error(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        performtasks.setUpdateTime(LocalDateTime.now().plusHours(13));
        int updatetasks = performtasksService.updatetasks(performtasks);
        if (updatetasks!=1) {
            return RespBean.error("编辑任务失败");
        }
        return RespBean.sucess("编辑成功");
    }
    @PostMapping("select/tasks")
    public RespBean selecttasks(@RequestBody Performtasks performtasks){
        List<Performtasks> taskslist = performtasksService.selecttasks();
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("taskslist",taskslist);
        return RespBean.sucess("查询成功",objectObjectHashMap);
    }
    @PostMapping("delete/tasks")
    public RespBean deletetasks(@RequestBody Performtasks performtasks){
        performtasksService.deletetasks(performtasks);
        return RespBean.sucess("删除成功");
    }




}
