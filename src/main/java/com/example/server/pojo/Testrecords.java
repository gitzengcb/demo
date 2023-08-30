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
 * @since 2023-06-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Testrecords对象", description="")
public class Testrecords implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用例id")
    private Integer caseid;

    @ApiModelProperty(value = "执行任务名id")
    private Integer performtasksid;

    @ApiModelProperty(value = "执行任务的场景id")
    private Integer sceneid;

    @ApiModelProperty(value = "response数据")
    private String returnresults;

    @ApiModelProperty(value = "true通过,false不通过")
    private Boolean assertionresults;

    private LocalDateTime createtime;

    private Integer isdelete;

    private Integer reportid;

    public Integer getReportid() {
        return reportid;
    }

    public void setReportid(Integer reportid) {
        this.reportid = reportid;
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

    public String getReturnresults() {
        return returnresults;
    }

    public void setReturnresults(String returnresults) {
        this.returnresults = returnresults;
    }

    public Boolean getAssertionresults() {
        return assertionresults;
    }

    public void setAssertionresults(Boolean assertionresults) {
        this.assertionresults = assertionresults;
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

    @Override
    public String toString() {
        return "Testrecords{" +
                "id=" + id +
                ", caseid=" + caseid +
                ", performtasksid=" + performtasksid +
                ", sceneid=" + sceneid +
                ", returnresults='" + returnresults + '\'' +
                ", assertionresults=" + assertionresults +
                ", createtime=" + createtime +
                ", isdelete=" + isdelete +
                ",reportid="+reportid+
                '}';
    }
}
