package com.example.server.service;

import com.example.server.pojo.ParameterConfiguration;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.context.annotation.Description;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zengchengbing
 * @since 2022-04-14
 */
public interface IParameterConfigurationService extends IService<ParameterConfiguration> {
    @Description("新增配置")
    int insert(ParameterConfiguration parameterConfiguration);
    @Description("查询配置")
    List<ParameterConfiguration> select();

    void update(ParameterConfiguration parameterConfiguration);

    void delete(Integer id);
}
