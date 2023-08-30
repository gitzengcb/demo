package com.example.server.service.impi;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.server.mapper.FailcaseMapper;
import com.example.server.pojo.Failcase;
import com.example.server.service.IFailcaseService;
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
 * @since 2022-01-18
 */
@Service
public class FailcaseServiceImpl extends ServiceImpl<FailcaseMapper, Failcase> implements IFailcaseService {
    @Resource
    FailcaseMapper failcaseMapper;
    @Description("插入断言失败日志")
    @Override
    public int insert(Failcase failcase){
        return failcaseMapper.insert(failcase);
    }
    @Description("查询错误日志")
    @Override
    public List<Failcase> select(Integer reportid){
        return failcaseMapper.selectid(reportid);
    }

}
