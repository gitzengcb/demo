package com.example.server.service.impi;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.server.mapper.CaselistMapper;
import com.example.server.pojo.Caselist;
import com.example.server.service.ICaselistService;
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
public class CaselistServiceImpl extends ServiceImpl<CaselistMapper, Caselist> implements ICaselistService {
    @Resource
    private CaselistMapper caselistMapper;

    @Description("新增用例")
    @Override
    public int insertcase(Caselist caselist){
        return caselistMapper.insert(caselist);
    }
    @Description("编辑用例")
    @Override
    public int updatecase(Caselist caselist){
        return caselistMapper.update(caselist);
    }
    @Description("根据接口查询用例")
    @Override
    public List<Caselist> selectcase(Caselist caselist){
        return caselistMapper.select(caselist);
    }
    @Description("删除用例")
    @Override
    public int deletecase(Caselist caselist){
        return caselistMapper.delete(caselist);
    }
    @Description("根据场景组查询case")
    @Override
    public List<Caselist> selectgroupcase(List<Integer> list){
        return caselistMapper.groupcase(list);
    }
    @Description("通过case名称模糊查询")
    @Override
    public List<Caselist> selectvaguecase(Caselist caselist ){
        return caselistMapper.vaguecase(caselist);
    }
    @Description("通过id查询执行的case数据")
    @Override
    public Caselist casestart(Integer id){
        return caselistMapper.casestart(id);
    }
}
