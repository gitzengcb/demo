package com.example.server.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2022-01-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Performtasks对象", description="")
public class Performtasks implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @NotBlank(message = "任务名称不能为空")
    @Length(message = "任务名称长度不能超过50字符",max = 50)
    @ApiModelProperty(value = "执行任务名称")
    private String tasksname;

    @ApiModelProperty(value = "执行任务的场景id")
    private String scenegroupid;

    private LocalDateTime createtime;

    private LocalDateTime updateTime;

    private Boolean enabled;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTasksname() {
        return tasksname;
    }

    public void setTasksname(String tasksname) {
        this.tasksname = tasksname;
    }

    public String getScenegroupid() {
        return scenegroupid;
    }

    public void setScenegroupid(String scenegroupid) {
        this.scenegroupid = scenegroupid;
    }

    public LocalDateTime getCreatetime() {
        return createtime;
    }

    public void setCreatetime(LocalDateTime createtime) {
        this.createtime = createtime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "Performtasks{" +
                "id=" + id +
                ", tasksname='" + tasksname + '\'' +
                ", scenegroupid='" + scenegroupid + '\'' +
                ", createtime=" + createtime +
                ", updateTime=" + updateTime +
                ", enabled=" + enabled +
                '}';
    }
}
