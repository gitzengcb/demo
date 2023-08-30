package com.example.server.service.impi;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.server.mapper.ParameterConfigurationMapper;
import com.example.server.pojo.ParameterConfiguration;
import com.example.server.service.IParameterConfigurationService;
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
 * @since 2022-04-14
 */
@Service
public class ParameterConfigurationServiceImpl extends ServiceImpl<ParameterConfigurationMapper, ParameterConfiguration> implements IParameterConfigurationService {
    @Resource
    ParameterConfigurationMapper parameterConfigurationMapper;

    @Description("新增配置")
    @Override
    public int insert(ParameterConfiguration parameterConfiguration){
        return parameterConfigurationMapper.insert(parameterConfiguration);
    }
    @Description("查询配置")
    @Override
    public List<ParameterConfiguration> select(){
        return parameterConfigurationMapper.select();
    }
    @Description("修改配置")
    @Override
    public void update(ParameterConfiguration parameterConfiguration){
        parameterConfigurationMapper.update(parameterConfiguration);

    }

    @Description("删除配置")
    @Override
    public void delete(Integer id){
        parameterConfigurationMapper.delete(id);
    }

}
