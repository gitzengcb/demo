package com.example.server.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

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
@ApiModel(value="Groups对象", description="")
public class Groups implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "节点名称")
    @NotBlank(message = "节点名称不能为空")
    private String groupname;

    @ApiModelProperty(value = "上级节点id")
    private Integer superiorid;

    private LocalDateTime createtime;

    private LocalDateTime updatetime;

    private Boolean active;
    private List<Groups> classlist;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    public Integer getSuperiorid() {
        return superiorid;
    }

    public void setSuperiorid(Integer superiorid) {
        this.superiorid = superiorid;
    }

    public LocalDateTime getCreatetime() {
        return createtime;
    }

    public void setCreatetime(LocalDateTime createtime) {
        this.createtime = createtime;
    }

    public LocalDateTime getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(LocalDateTime updatetime) {
        this.updatetime = updatetime;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<Groups> getClasslist() {
        return classlist;
    }

    public void setClasslist(List<Groups> classlist) {
        this.classlist = classlist;
    }

    @Override
    public String toString() {
        return "Groups{" +
                "id=" + id +
                ", groupname='" + groupname + '\'' +
                ", superiorid=" + superiorid +
                ", createtime=" + createtime +
                ", updatetime=" + updatetime +
                ", active=" + active +
                ", Classlist=" + classlist +
                '}';
    }
}
