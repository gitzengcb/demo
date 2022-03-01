package com.example.server.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "Userid对象",description = "")
public class Userid implements Serializable {
    private static final long serialVersionUID = 5078363635489660870L;
    @ApiModelProperty(value = "用户id",required = true)
    private List<Integer> id;
}
