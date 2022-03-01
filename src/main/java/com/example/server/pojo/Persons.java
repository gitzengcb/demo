package com.example.server.pojo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "Persons对象",description = "")
public class Persons {

    @ApiModelProperty(value = "名字")
    @NotBlank(message = "请输入名字")//String用这个判空
    @Length(message = "长度不能超过{max},不能少于{min}",max = 10,min =1 )
    private String name;

    @NotNull(message = "年龄不能为空")//Integer用这个判空
    @Range(message = "年龄最大不能超过{max},最小不能低于{min}",max = 100,min = 10)
    @ApiModelProperty(value = "年龄",required = true)
    private Integer age;
}
