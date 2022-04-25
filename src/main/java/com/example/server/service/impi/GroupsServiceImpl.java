package com.example.server.service.impi;

import com.baomidou.mybatisplus.extension.api.R;
import com.example.server.pojo.Groups;
import com.example.server.mapper.GroupsMapper;
import com.example.server.service.IGroupsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zengchengbing
 * @since 2022-04-18
 */
@Service
public class GroupsServiceImpl extends ServiceImpl<GroupsMapper, Groups> implements IGroupsService {
    @Resource
    private GroupsMapper groupsMapper;

    @Override
    @Description("新增类目")
    public void insert(Groups groups){
        groupsMapper.insert(groups);
    }

    @Description("查询类目")
    @Override
    public List<Groups> select(){
        return groupsMapper.select();
    }
//    @Override
//    @Description("查询节点下所有节点")
//    public List selectlistid(int id){
//        return groupsMapper.selectList(id);
//    }

}
