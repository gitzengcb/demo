package com.example.server.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import groovy.transform.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author zengchengbing
 * @since 2021-12-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Report对象", description="")
public class Report{

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer basesum=0;
    private Integer successsum=0;
    private Integer errorsum=0;
    private Integer sumcount=0;
    private String successfullist;
    private String failurelist;
    @TableField("create_time")
    private LocalDateTime create_time;
    private Boolean active=true;
    private List<Failcase> failcaselist;

    public void setFailcaselist(List<Failcase> failcaselist) {
        this.failcaselist = failcaselist;
    }

    public List<Failcase> getFailcaselist() {
        return failcaselist;
    }

    public Integer getId() {
        return id;
    }

    public Integer getBasesum() {
        return basesum;
    }

    public Integer getSuccesssum() {
        return successsum;
    }

    public Integer getErrorsum() {
        return errorsum;
    }

    public Integer getSumcount() {
        return sumcount;
    }

    public String getSuccessfullist() {
        return successfullist;
    }

    public String getFailurelist() {
        return failurelist;
    }

    public LocalDateTime getCreate_time() {
        return create_time;
    }

    public Boolean getActive() {
        return active;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setBasesum(Integer basesum) {
        this.basesum = basesum;
    }

    public void setSuccesssum(Integer successsum) {
        this.successsum = successsum;
    }

    public void setErrorsum(Integer errorsum) {
        this.errorsum = errorsum;
    }

    public void setSumcount(Integer sumcount) {
        this.sumcount = sumcount;
    }

    public void setSuccessfullist(String successfullist) {
        this.successfullist = successfullist;
    }

    public void setFailurelist(String failurelist) {
        this.failurelist = failurelist;
    }

    public void setCreate_time(LocalDateTime create_time) {
        this.create_time = create_time;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", basesum=" + basesum +
                ", successsum=" + successsum +
                ", errorsum=" + errorsum +
                ", sumcount=" + sumcount +
                ", successfullist='" + successfullist + '\'' +
                ", failurelist='" + failurelist + '\'' +
                ", create_time=" + create_time +
                ", active=" + active +
                ", failcaselist=" + failcaselist +
                '}';
    }
}
