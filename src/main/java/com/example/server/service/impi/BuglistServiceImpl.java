package com.example.server.service.impi;

import com.example.server.pojo.Buglist;
import com.example.server.mapper.BuglistMapper;
import com.example.server.service.IBuglistService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zengchengbing
 * @since 2022-04-18
 */
@Service
public class BuglistServiceImpl extends ServiceImpl<BuglistMapper, Buglist> implements IBuglistService {
    @Resource
    BuglistMapper buglistMapper;
    @Description("新增bug")
    @Override
    public int insert(Buglist buglist){
        return buglistMapper.insert(buglist);
    }

    @Override
    @Description("编辑bug")
    public void buglistupdate(Buglist buglist){
        buglistMapper.update(buglist);
    }

    @Override
    @Description("查询bug")
    public List<Buglist> select(Map map){
        return buglistMapper.select(map);
    }

}
