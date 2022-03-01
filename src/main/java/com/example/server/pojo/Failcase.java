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
 * @since 2022-01-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Failcase对象", description="")
public class Failcase implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用例id")
    private Integer caseid;

    @ApiModelProperty(value = "执行任务名id")
    private Integer performtasksid;

    @ApiModelProperty(value = "执行任务的场景id")
    private Integer sceneid;

    @ApiModelProperty(value = "断言失败返回数据")
    private String errorlog;

    private LocalDateTime createtime;

    private Boolean enabled;
    private Integer reportid;

    public Integer getReportid() {
        return reportid;
    }

    public void setReportid(Integer reportid) {
        this.reportid = reportid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCaseid() {
        return caseid;
    }

    public void setCaseid(Integer caseid) {
        this.caseid = caseid;
    }

    public Integer getPerformtasksid() {
        return performtasksid;
    }

    public void setPerformtasksid(Integer performtasksid) {
        this.performtasksid = performtasksid;
    }

    public Integer getSceneid() {
        return sceneid;
    }

    public void setSceneid(Integer sceneid) {
        this.sceneid = sceneid;
    }

    public String getErrorlog() {
        return errorlog;
    }

    public void setErrorlog(String errorlog) {
        this.errorlog = errorlog;
    }

    public LocalDateTime getCreatetime() {
        return createtime;
    }

    public void setCreatetime(LocalDateTime createtime) {
        this.createtime = createtime;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "Failcase{" +
                "id=" + id +
                ", caseid=" + caseid +
                ", performtasksid=" + performtasksid +
                ", sceneid=" + sceneid +
                ", errorlog='" + errorlog + '\'' +
                ", createtime=" + createtime +
                ", enabled=" + enabled +
                ", reportid=" + reportid +
                '}';
    }
}
