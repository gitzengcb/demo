package com.example.server.service.impi;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.server.mapper.TestrecordsMapper;
import com.example.server.pojo.Testrecords;
import com.example.server.service.ITestrecordsService;
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
 * @since 2023-06-02
 */
@Service
public class TestrecordsServiceImpl extends ServiceImpl<TestrecordsMapper, Testrecords> implements ITestrecordsService {
    @Resource
    TestrecordsMapper testrecordsMapper;
    @Description("插入case测试结果")
    @Override
    public void insert(Testrecords testrecords){
        testrecordsMapper.insert(testrecords);
    }

    @Description("查询")
    @Override
    public List<Testrecords> selectreport(Integer performtasksid){
        return testrecordsMapper.selectList(performtasksid);
    }


}
