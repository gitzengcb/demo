package com.example.server.service.impi;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.server.mapper.ClassificationMapper;
import com.example.server.pojo.Classification;
import com.example.server.service.IClassificationService;
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
 * @since 2022-01-07
 */
@Service
public class ClassificationServiceImpl extends ServiceImpl<ClassificationMapper, Classification> implements IClassificationService {
    @Resource
    private ClassificationMapper classificationMapper;

    @Description("新增分类")
    @Override
    public int insertClass(Classification classification) {
        return classificationMapper.insert(classification);
    }
    @Description("修改分类")
    @Override
    public int updateClass(Classification classification){
        return classificationMapper.update(classification);
    }
    @Description("删除分类")
    @Override
    public int deleteClass(Classification classification){
        return classificationMapper.delete(classification);
    }
    @Description("查询分类")
    @Override
    public List<Classification> selectClass(){
        return classificationMapper.select();
    }
}
