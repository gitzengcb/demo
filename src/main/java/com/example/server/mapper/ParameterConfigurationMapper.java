package com.example.server.mapper;

import com.example.server.pojo.ParameterConfiguration;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zengchengbing
 * @since 2022-04-14
 */
public interface ParameterConfigurationMapper extends BaseMapper<ParameterConfiguration> {


    List<ParameterConfiguration> select();

    void update(ParameterConfiguration parameterConfiguration);

    void delete(Integer id);
}
