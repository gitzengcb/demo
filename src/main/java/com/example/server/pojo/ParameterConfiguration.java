package com.example.server.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author zengchengbing
 * @since 2022-04-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("parameter_configuration")
@ApiModel(value="ParameterConfiguration对象", description="")
public class ParameterConfiguration implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @NotBlank(message = "id不能为空")
    private Integer id;

    @ApiModelProperty(value = "登陆名称")
    @Length(message="用户名长度不能大于{max},不能小于{min}",max = 10,min = 1)
    private String username;

    @ApiModelProperty(value = "登陆密码")
    @Length(message="密码长度不能大于{max},不能小于{min}",max = 10,min = 1)
    private String password;

    @ApiModelProperty(value = "域名")
    @Length(message="域名长度不能大于{max},不能小于{min}",max = 50,min = 1)
    private String hosturl;

    private LocalDateTime createtime;

    private Boolean active;


}
