package com.example.server.controller;


import com.example.server.pojo.Classification;
import com.example.server.pojo.Groups;
import com.example.server.publics.RespBean;
import com.example.server.service.IGroupsService;
import com.example.server.utils.Treelist;
import groovyjarjarantlr4.runtime.tree.Tree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zengchengbing
 * @since 2022-04-18
 */
@RestController
//@RequestMapping("/groups")
public class GroupsController {
    private static Logger logger = LoggerFactory.getLogger(ClassificationController.class);
    @Autowired
    IGroupsService groupsService;
    @PostMapping("insert/groups")
    public RespBean insertgroups(@Valid @RequestBody Groups groups, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return RespBean.error(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        groups.setCreatetime(LocalDateTime.now().plusHours(13));
        groupsService.insert(groups);

        return RespBean.sucess("新增成功");
    }


    @GetMapping("select/groupstree")
    public RespBean selectgroups(){
        List<Groups> select = groupsService.select();
        List<Groups> tree = tree(select);
        Map<Object, Object> objectObjectMap = new HashMap<>();

        objectObjectMap.put("list",tree);

        return RespBean.sucess("true",objectObjectMap);
    }
    private List<Groups> tree(List<Groups> alllist) {
        List<Groups> listtree = new ArrayList<>();
        for (Groups groups:alllist){
            if (groups.getSuperiorid()==0){
                groups.setClasslist(treelist(alllist,groups.getId()));
                listtree.add(groups);
            }
        }
        return listtree;
    }
    private List<Groups> treelist(List<Groups> alllist, Integer id) {
        List<Groups> zitree = new ArrayList<>();
        for (Groups ov:alllist){
            if (ov.getSuperiorid().equals(id)){
                ov.setClasslist(treelist(alllist,ov.getId()));
                zitree.add(ov);
            }
        }
        return zitree;
    }


}
