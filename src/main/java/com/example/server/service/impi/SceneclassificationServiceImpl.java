package com.example.server.service.impi;

import com.example.server.pojo.Sceneclassification;
import com.example.server.mapper.SceneclassificationMapper;
import com.example.server.service.ISceneclassificationService;
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
 * @since 2022-01-11
 */
@Service
public class SceneclassificationServiceImpl extends ServiceImpl<SceneclassificationMapper, Sceneclassification> implements ISceneclassificationService {
    @Resource
    SceneclassificationMapper sceneclassificationMapper;
    @Description("新增场景")
    @Override
    public int insertscene(Sceneclassification sceneclassification){
        return sceneclassificationMapper.insert(sceneclassification);
    }
    @Description("修改场景")
    @Override
    public int updatescene(Sceneclassification sceneclassification){
        return sceneclassificationMapper.update(sceneclassification);
    }
    @Description("删除场景")
    @Override
    public int delectscene(Sceneclassification sceneclassification){
        return sceneclassificationMapper.delete(sceneclassification);
    }
    @Description("查询场景")
    @Override
    public List<Sceneclassification> selectscene(Sceneclassification sceneclassification){
        return sceneclassificationMapper.select(sceneclassification);
    }
    @Description("添加case")
    @Override
    public int addcase(Sceneclassification sceneclassification){
        return sceneclassificationMapper.addcase(sceneclassification);
    }
    @Description("根据场景id查询")
    @Override
    public Sceneclassification scenestart(int id){
        return sceneclassificationMapper.scenestart(id);
    }
}
