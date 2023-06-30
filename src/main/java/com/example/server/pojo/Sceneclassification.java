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
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author zengchengbing
 * @since 2022-01-11
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Sceneclassification对象", description="")
public class Sceneclassification implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "场景名称")
    @NotBlank(message = "场景名称不能为空")
    @Length(message = "长度不能超过{50}",max = 50)
    private String scenename;

    private LocalDateTime createtime;

    private LocalDateTime updateTime;

    private Integer isdelete;
    private String casegroup;
    private List<Sceneclassification> scene;

    public void setScene(List<Sceneclassification> scene) {
        this.scene = scene;
    }

    public List<Sceneclassification> getScene() {
        return scene;
    }

    public void setCasegroup(String casegroup) {
        this.casegroup = casegroup;
    }

    public String getCasegroup() {
        return casegroup;
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

    public String getScenename() {
        return scenename;
    }

    public void setScenename(String scenename) {
        this.scenename = scenename;
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
        return "Sceneclassification{" +
                "id=" + id +
                ", scenename='" + scenename + '\'' +
                ", createtime=" + createtime +
                ", updateTime=" + updateTime +
                ", isdetete=" + isdelete +
                ", casegroup='" + casegroup + '\'' +
                ", scene=" + scene +
                '}';
    }
}
