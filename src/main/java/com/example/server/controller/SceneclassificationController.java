package com.example.server.controller;


import com.example.server.pojo.Sceneclassification;
import com.example.server.publics.RespBean;
import com.example.server.service.ISceneclassificationService;
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
//@RequestMapping("/sceneclassification")
public class SceneclassificationController {

    @Autowired
    ISceneclassificationService sceneclassificationService;

    @PostMapping("insert/scene")
    public RespBean insertscene(@Valid @RequestBody Sceneclassification sceneclassification, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return RespBean.error(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        sceneclassification.setCreatetime(LocalDateTime.now().plusHours(14));
        sceneclassificationService.insertscene(sceneclassification);
        return RespBean.sucess("新增场景成功");
    }

    @PostMapping("update/scene")
    public RespBean updatescene(@Valid @RequestBody Sceneclassification sceneclassification, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return RespBean.error(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        sceneclassification.setUpdateTime(LocalDateTime.now().plusHours(14));
        sceneclassificationService.updatescene(sceneclassification);
        return RespBean.sucess("修改成功");
    }

    @PostMapping("delete/scene")
    public RespBean delectscene(@RequestBody Sceneclassification sceneclassification) {
        sceneclassificationService.delectscene(sceneclassification);
        return RespBean.sucess("删除成功");
    }

    @PostMapping("select/scene")
    public RespBean selectscene(@RequestBody Sceneclassification sceneclassification) {
        List<Sceneclassification> alllist = sceneclassificationService.selectscene(sceneclassification);
        List<Sceneclassification> treeList=new ArrayList<Sceneclassification>();

        //组装数list
        for (Sceneclassification vo :alllist) {
            if (vo.getSuperiorid() == 0) {
                vo.setScene(getChildrenNode(vo.getId(), alllist));
                treeList.add(vo);
            }
        }

        HashMap<String, Object> objectObjectHashMap = new HashMap<>();

        objectObjectHashMap.put("treeList", treeList);
        return RespBean.sucess("查询成功", objectObjectHashMap);
    }

    @PostMapping("scene/addcase")
    public RespBean addcase(@RequestBody Sceneclassification sceneclassification) {
        sceneclassificationService.addcase(sceneclassification);
        return RespBean.sucess("添加case成功");
    }



    /**
     * 组装数结构
     * @param integer
     * @param treesList
     * @return
     */

    private List<Sceneclassification> getChildrenNode(Integer integer, List<Sceneclassification> treesList) {
        List<Sceneclassification> newTrees = new ArrayList<Sceneclassification>();
        for (Sceneclassification department : treesList) {
            if (department.getSuperiorid() == 0) {
                continue;
            }
            if (department.getSuperiorid() == integer) {
                // 递归获取子节点下的子节点，即设置树控件中的children
                department.setScene(getChildrenNode(department.getId(), treesList));
                newTrees.add(department);
            }
        }
        return newTrees;
    }
}
