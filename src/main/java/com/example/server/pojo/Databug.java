package com.example.server.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author zengchengbing
 * @since 2022-04-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Databug对象", description="")
public class Databug implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "字典类型：FH001:优先级字典;FH002：状态字典;FH003:bug类型；FH004:标签")
    private String datatype;

    @ApiModelProperty(value = "字典唯一标识")
    private String datakey;

    @ApiModelProperty(value = "字典名称")
    private String datavalue;

    private LocalDateTime createtime;

    private LocalDateTime updatetime;

    private Boolean active;


}
