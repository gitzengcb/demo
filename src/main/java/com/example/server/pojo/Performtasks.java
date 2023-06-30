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
//    @NotBlank(message = "任务名称不能为空")
    @Length(message = "任务名称长度不能超过50字符",max = 50)
    @ApiModelProperty(value = "执行任务名称")
    private String tasksname;

    @ApiModelProperty(value = "执行任务的场景id")
    private String scenegroupid;

    private LocalDateTime createtime;

    private LocalDateTime updateTime;

    private Integer isdelete;

    private String username;
    private String password;
    private String hosturl;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHosturl() {
        return hosturl;
    }

    public void setHosturl(String hosturl) {
        this.hosturl = hosturl;
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

    public Integer getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }

    @Override
    public String toString() {
        return "Performtasks{" +
                "id=" + id +
                ", tasksname='" + tasksname + '\'' +
                ", scenegroupid='" + scenegroupid + '\'' +
                ", createtime=" + createtime +
                ", updateTime=" + updateTime +
                ", isdelete=" + isdelete +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", hosturl='" + hosturl + '\'' +
                '}';
    }
}
