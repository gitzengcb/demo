package com.example.server.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@ApiModel(value="Buglist对象", description="")
public class Buglist implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "bug标题")
    private String bugtitle;

    @ApiModelProperty(value = "bug描述")
    private String bugdescribe;

    @ApiModelProperty(value = "所属目录id")
    private Integer groupsid;

    @ApiModelProperty(value = "优先级id")
    private Integer priorityid;

    @ApiModelProperty(value = "状态id")
    private Integer stateid;

    @ApiModelProperty(value = "bug类型id")
    private Integer bugtypeid;

    @ApiModelProperty(value = "标签id")
    private Integer labelid;

    private LocalDateTime createtime;

    private LocalDateTime updatetime;

    private Boolean active;


}
