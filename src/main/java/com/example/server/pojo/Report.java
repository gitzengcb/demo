package com.example.server.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @since 2023-06-08
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Report对象", description="")
public class Report implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer successsum;

    @ApiModelProperty(value = "失败case总量")
    private Integer failsum;

    @ApiModelProperty(value = "case总量")
    private Integer casesum;

    @ApiModelProperty(value = "成功用例名称集合")
    private String successnamelist;

    @ApiModelProperty(value = "失败用例名称集合")
    private String failurenamelist;

    private LocalDateTime createtime;

    private Integer isdelete;

    @ApiModelProperty(value = "任务名称")
    private String tasksname;

    @ApiModelProperty(value = "执行状态：0创建任务，1任务执行中，2任务结束")
    private Integer status;

    private LocalDateTime updatetime;

    private LocalDateTime starttime;

    private LocalDateTime endtime;

    private Integer performtasksid;

    public Integer getPerformtasksid() {
        return performtasksid;
    }

    public void setPerformtasksid(Integer performtasksid) {
        this.performtasksid = performtasksid;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSuccesssum() {
        return successsum;
    }

    public void setSuccesssum(Integer successsum) {
        this.successsum = successsum;
    }

    public Integer getFailsum() {
        return failsum;
    }

    public void setFailsum(Integer failsum) {
        this.failsum = failsum;
    }

    public Integer getCasesum() {
        return casesum;
    }

    public void setCasesum(Integer casesum) {
        this.casesum = casesum;
    }

    public String getSuccessnamelist() {
        return successnamelist;
    }

    public void setSuccessnamelist(String successnamelist) {
        this.successnamelist = successnamelist;
    }

    public String getFailurenamelist() {
        return failurenamelist;
    }

    public void setFailurenamelist(String failurenamelist) {
        this.failurenamelist = failurenamelist;
    }

    public LocalDateTime getCreatetime() {
        return createtime;
    }

    public void setCreatetime(LocalDateTime createtime) {
        this.createtime = createtime;
    }

    public Integer getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }

    public String getTasksname() {
        return tasksname;
    }

    public void setTasksname(String tasksname) {
        this.tasksname = tasksname;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(LocalDateTime updatetime) {
        this.updatetime = updatetime;
    }

    public LocalDateTime getStarttime() {
        return starttime;
    }

    public void setStarttime(LocalDateTime starttime) {
        this.starttime = starttime;
    }

    public LocalDateTime getEndtime() {
        return endtime;
    }

    public void setEndtime(LocalDateTime endtime) {
        this.endtime = endtime;
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", successsum=" + successsum +
                ", failsum=" + failsum +
                ", casesum=" + casesum +
                ", successnamelist='" + successnamelist + '\'' +
                ", failurenamelist='" + failurenamelist + '\'' +
                ", createtime=" + createtime +
                ", isdelete=" + isdelete +
                ", tasksname='" + tasksname + '\'' +
                ", status=" + status +
                ", updateTime=" + updatetime +
                ", startTime=" + starttime +
                ", endTime=" + endtime +
                ",performtasksid"+performtasksid+
                '}';
    }
}
