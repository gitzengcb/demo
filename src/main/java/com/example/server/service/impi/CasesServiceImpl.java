package com.example.server.service.impi;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.server.mapper.CasesMapper;
import com.example.server.pojo.Cases;
import com.example.server.service.ICasesService;
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
public class CasesServiceImpl extends ServiceImpl<CasesMapper, Cases> implements ICasesService {
    @Resource
    private CasesMapper casesMapper;

    @Description("新增用例")
    @Override
    public int insertcase(Cases cases){
        return casesMapper.insert(cases);
    }
    @Description("编辑用例")
    @Override
    public int updatecase(Cases cases){
        return casesMapper.update(cases);
    }
    @Description("根据接口查询用例")
    @Override
    public List<Cases> selectcase(Cases cases){
        return casesMapper.select(cases);
    }
    @Description("删除用例")
    @Override
    public int deletecase(Cases cases){
        return casesMapper.delete(cases);
    }
    @Description("根据场景组查询case")
    @Override
    public List<Cases> selectgroupcase(List<Integer> list){
        return casesMapper.groupcase(list);
    }
    @Description("通过case名称模糊查询")
    @Override
    public List<Cases> selectvaguecase(Cases cases ){
        return casesMapper.vaguecase(cases);
    }
    @Description("通过id查询执行的case数据")
    @Override
    public Cases casestart(Integer id){
        return casesMapper.casestart(id);
    }
}
