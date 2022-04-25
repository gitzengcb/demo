package com.example.server.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
//    @NotBlank(message = "标题不能为空")
    private String bugtitle;

    @ApiModelProperty(value = "bug描述")
//    @NotBlank(message = "bug描述不能为空")
    private String bugdescribe;

    @ApiModelProperty(value = "所属目录id")
//    @NotNull(message = "节点不能为空")
    private Integer groupsid;

    @ApiModelProperty(value = "优先级id")
    private Integer priorityid;

    @ApiModelProperty(value = "状态id")
    private Integer stateid;

    @ApiModelProperty(value = "bug类型id")
    private Integer bugtypeid;

    @ApiModelProperty(value = "标签id")
    private Integer labelid;

    private List<Integer> priorityidlist;
    private List<Integer> stateidlist;
    private List<Integer> bugtypeidlist;
    private List<Integer> labelidlist;

    private LocalDateTime createtime;

    private LocalDateTime updatetime;

    private Boolean active;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public List<Integer> getStateidlist() {
        return stateidlist;
    }

    public void setStateidlist(List<Integer> stateidlist) {
        this.stateidlist = stateidlist;
    }

    public List<Integer> getBugtypeidlist() {
        return bugtypeidlist;
    }

    public void setBugtypeidlist(List<Integer> bugtypeidlist) {
        this.bugtypeidlist = bugtypeidlist;
    }

    public List<Integer> getLabelidlist() {
        return labelidlist;
    }

    public void setLabelidlist(List<Integer> labelidlist) {
        this.labelidlist = labelidlist;
    }

    public List<Integer> getPriorityidlist() {
        return priorityidlist;
    }

    public void setPriorityidlist(List<Integer> priorityidlist) {
        this.priorityidlist = priorityidlist;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBugtitle() {
        return bugtitle;
    }

    public void setBugtitle(String bugtitle) {
        this.bugtitle = bugtitle;
    }

    public String getBugdescribe() {
        return bugdescribe;
    }

    public void setBugdescribe(String bugdescribe) {
        this.bugdescribe = bugdescribe;
    }

    public Integer getGroupsid() {
        return groupsid;
    }

    public void setGroupsid(Integer groupsid) {
        this.groupsid = groupsid;
    }

    public Integer getPriorityid() {
        return priorityid;
    }

    public void setPriorityid(Integer priorityid) {
        this.priorityid = priorityid;
    }

    public Integer getStateid() {
        return stateid;
    }

    public void setStateid(Integer stateid) {
        this.stateid = stateid;
    }

    public Integer getBugtypeid() {
        return bugtypeid;
    }

    public void setBugtypeid(Integer bugtypeid) {
        this.bugtypeid = bugtypeid;
    }

    public Integer getLabelid() {
        return labelid;
    }

    public void setLabelid(Integer labelid) {
        this.labelid = labelid;
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

    @Override
    public String toString() {
        return "Buglist{" +
                "id=" + id +
                ", bugtitle='" + bugtitle + '\'' +
                ", bugdescribe='" + bugdescribe + '\'' +
                ", groupsid=" + groupsid +
                ", priorityid=" + priorityid +
                ", stateid=" + stateid +
                ", bugtypeid=" + bugtypeid +
                ", labelid=" + labelid +
                ", createtime=" + createtime +
                ", updatetime=" + updatetime +
                ", active=" + active +
                '}';
    }
}
