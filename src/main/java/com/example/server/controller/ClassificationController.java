package com.example.server.controller;


import com.example.server.generator.TreeStructure;
import com.example.server.pojo.Classification;
import com.example.server.publics.RespBean;
import com.example.server.service.IClassificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
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
 * @since 2022-01-07
 */
@RestController
//@RequestMapping("/classification")
public class ClassificationController {
    private static Logger logger = LoggerFactory.getLogger(ClassificationController.class);
    @Autowired
    private IClassificationService classificationService;

    @PostMapping("insert/Classification")
    public RespBean insertClassification(@Valid @RequestBody Classification inputlist, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return RespBean.error(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }

        inputlist.setCreatetime(LocalDateTime.now().plusHours(13));
        classificationService.insertClass(inputlist);
        return RespBean.sucess("新增分类成功");

    }
    @PostMapping("update/Classification")
    public RespBean updateClassification(@Valid @RequestBody Classification inputlist,BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return RespBean.error(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        if (inputlist.getId()==null){
            return RespBean.error("id不能为空");
        }
        if (inputlist.getSuperiorid()==null){
            return RespBean.error("父id不能为空");
        }

        inputlist.setUpdatetime(LocalDateTime.now().plusHours(13));
        classificationService.updateClass(inputlist);
        return RespBean.sucess("修改分类成功");
    }
    @PostMapping("delete/Classification")
    public RespBean deleteClassification(@RequestBody Classification inputlist){
        if (inputlist.getId()==null){
            return RespBean.error("删除的id不能为空");
        }
        classificationService.deleteClass(inputlist);
        return RespBean.sucess("删除成功");
    }
    @GetMapping("select/Classification")
    public RespBean selectClassification(){
        List<Classification> alllist = classificationService.selectClass();

        if (alllist.isEmpty()){
            return RespBean.sucess("查询成功",null);
        }

        List<Classification> trees =new TreeStructure().tree(alllist);
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();

        objectObjectHashMap.put("ClassList",trees);

        return RespBean.sucess("查询成功",objectObjectHashMap);
    }




}
