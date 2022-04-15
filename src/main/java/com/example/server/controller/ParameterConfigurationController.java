package com.example.server.controller;


import com.example.server.pojo.ParameterConfiguration;
import com.example.server.publics.RespBean;
import com.example.server.service.IParameterConfigurationService;
import lombok.experimental.Accessors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zengchengbing
 * @since 2022-04-14
 */
@RestController
//@RequestMapping("/parameter-configuration")
public class ParameterConfigurationController {
    private static Logger logger = LoggerFactory.getLogger(ClassificationController.class);
    @Autowired
    IParameterConfigurationService parameterConfigurationService;
    @PostMapping("insert/ParameterConfiguration")
    public RespBean insertParameterConfiguration(@Valid @RequestBody ParameterConfiguration parameterConfiguration, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return RespBean.error(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }

        parameterConfiguration.setCreatetime(LocalDateTime.now().plusHours(14));
        parameterConfigurationService.insert(parameterConfiguration);
        return RespBean.sucess("新增配置成功");
    }
    @PostMapping("select/ParameterConfiguration")
    public RespBean selectParameterConfiguration(@Valid @RequestBody ParameterConfiguration parameterConfiguration, BindingResult bindingResult){
        List<ParameterConfiguration> selectlist = parameterConfigurationService.select();
        HashMap<Object, Object> map = new HashMap<>();
        map.put("list",selectlist);

        return RespBean.sucess("新增配置成功",map);
    }

    @PostMapping("update/ParameterConfiguration")
    public RespBean updateParameterConfiguration(@Valid @RequestBody ParameterConfiguration parameterConfiguration, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return RespBean.error(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }

        parameterConfiguration.setCreatetime(LocalDateTime.now().plusHours(14));
        parameterConfigurationService.update(parameterConfiguration);
        return RespBean.sucess("新增配置成功");
    }
    @PostMapping("delete/ParameterConfiguration")
    public RespBean deleteParameterConfiguration(@Valid @RequestBody ParameterConfiguration parameterConfiguration, BindingResult bindingResult){
        if (parameterConfiguration.getId() !=null){
            parameterConfigurationService.delete(parameterConfiguration.getId());
        }
        return RespBean.sucess("新增配置成功");
    }


}
