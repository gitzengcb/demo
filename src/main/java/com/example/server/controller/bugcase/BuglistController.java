package com.example.server.controller.bugcase;


import com.example.server.controller.ClassificationController;
import com.example.server.pojo.Buglist;
import com.example.server.pojo.Groups;
import com.example.server.publics.RespBean;
import com.example.server.service.IBuglistService;
import com.example.server.service.IGroupsService;
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
//@RequestMapping("/buglist")
public class BuglistController {
    private static Logger logger = LoggerFactory.getLogger(ClassificationController.class);
    private static List<Integer> listid=new ArrayList<>();



    @Autowired
    IBuglistService buglistService;

    @Autowired(required = false)
    IGroupsService groupsServices;

    @PostMapping("insert/buglist")
    public RespBean insertgroups(@Valid @RequestBody Buglist buglist, BindingResult bindingResult){
        if (buglist.getGroupsid()==null){
            return RespBean.error("类目节点不能为空");
        }else if (buglist.getBugtitle().isEmpty()){
            return RespBean.error("标题不能为空");
        }else if (buglist.getBugdescribe().isEmpty()){
            return RespBean.error("bug内容描述不能为空");
        }else {
            logger.info(buglist.getBugtitle());
        }
        buglist.setCreatetime(LocalDateTime.now().plusHours(13));
        int id = buglistService.insert(buglist);
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("id",id);
        return RespBean.sucess("新增成功",objectObjectHashMap);
    }
    @PostMapping("update/buglist")
    public RespBean updatebuglist(@Valid @RequestBody Buglist buglist,BindingResult bindingResult){
        if (buglist.getGroupsid()==null){
            return RespBean.error("类目节点不能为空");
        }else if (buglist.getBugtitle().isEmpty()){
            return RespBean.error("标题不能为空");
        }else if (buglist.getBugdescribe().isEmpty()){
            return RespBean.error("bug内容描述不能为空");
        }else if (buglist.getId()==null){
            return RespBean.error("bugid未传");
        } else {
            logger.info(buglist.getBugtitle());
        }
        buglist.setUpdatetime(LocalDateTime.now().plusHours(13));
        buglistService.buglistupdate(buglist);
        return RespBean.sucess("修改成功");
    }

    @PostMapping("select/bugall")
    public RespBean bugall(@Valid @RequestBody Buglist buglist ){
        listid.clear();
        List<Groups> list = groupsServices.select();
        System.out.println(buglist.getGroupsid());
        tree(list,buglist.getGroupsid());//查询节点下所有子节点
        Map map = new HashMap<>();
        map.put("listid",listid);
        map.put("bugtitle",buglist.getBugtitle());
        map.put("priorityidlist",buglist.getPriorityidlist());
        map.put("stateidlist",buglist.getStateidlist());
        map.put("bugtypeidlist",buglist.getBugtypeidlist());
        map.put("labelidlist",buglist.getLabelidlist());
        map.put("start",(buglist.getCurrent()-1)*buglist.getSize());
        map.put("size",buglist.getSize());

        List<Buglist> listall = buglistService.select(map);
        Map<Object, Object> objectObjectHashMap = new HashMap<>();
        objectObjectHashMap.put("buglist",listall);
        return RespBean.sucess("查询成功",objectObjectHashMap);
    }
    private void tree(List<Groups> alllist,Integer id) {
        if (id==null){
            for (Groups groupall:alllist){
                listid.add(groupall.getId());
            }
        }
        for (Groups groups:alllist){
            if (groups.getId().equals(id)){
                treelist(alllist,groups.getId());
                listid.add(groups.getId());
            }
        }

    }
    private void treelist(List<Groups> alllist, Integer id) {
        for (Groups ov:alllist){
            if (ov.getSuperiorid().equals(id)){
                treelist(alllist,ov.getId());
                listid.add(ov.getId());
            }
        }
    }


}
