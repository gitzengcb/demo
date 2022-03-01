package com.example.server.service.impi;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.server.mapper.InterfaceMapper;
import com.example.server.pojo.Interface;
import com.example.server.service.IInterfaceService;
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
 * @since 2022-01-10
 */
@Service
public class InterfaceServiceImpl extends ServiceImpl<InterfaceMapper, Interface> implements IInterfaceService {
    @Resource
    private InterfaceMapper interfaceMapper;

    @Description("新增接口")
    @Override
    public int insertinter(Interface inputlist){
        return interfaceMapper.insert(inputlist);
    }
    @Description("编辑接口")
    @Override
    public int updateinset(Interface inputlist){
        return interfaceMapper.update(inputlist);
    }
    @Description("删除接口")
    @Override
    public int delectinter(Interface inputlist){
        return interfaceMapper.delete(inputlist);
    }
    @Description("通过分类查询接口数据")
    @Override
    public List<Interface> selectinter(Interface inputlist){
        return interfaceMapper.select(inputlist);
    }

}
