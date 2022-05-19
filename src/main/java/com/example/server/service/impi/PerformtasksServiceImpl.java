package com.example.server.service.impi;

import com.example.server.pojo.Performtasks;
import com.example.server.mapper.PerformtasksMapper;
import com.example.server.service.IPerformtasksService;
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
 * @since 2022-01-12
 */
@Service
public class PerformtasksServiceImpl extends ServiceImpl<PerformtasksMapper, Performtasks> implements IPerformtasksService {
    @Resource
    PerformtasksMapper performtasksMapper;

    @Description("新增执行任务")
    @Override
    public int inserttasks(Performtasks performtasks){
        return performtasksMapper.insert(performtasks);
    }
    @Description("编辑执行任务")
    @Override
    public int updatetasks(Performtasks performtasks){
        return performtasksMapper.update(performtasks);
    }
    @Description("查询执行任务")
    @Override
    public List<Performtasks> selecttasks(){
        return performtasksMapper.select();
    }
    @Description("删除执行任务")
    @Override
    public int deletetasks(Performtasks performtasks){
        return performtasksMapper.delete(performtasks);
    }
    @Description("查询任务下场景")
    @Override
    public String selectscenegroupid(Integer id){
        return performtasksMapper.selectscenegroup(id);
    }
}
