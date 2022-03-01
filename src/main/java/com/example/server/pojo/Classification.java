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
 * @since 2022-01-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Classification对象", description="")
public class Classification implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "父id")
//    @NotNull(message = "父id不能为空")
    private Integer superiorid;

    @ApiModelProperty(value = "分类名称")
    @NotBlank(message = "分类名称不能为空")//String用这个判空
    @Length(message = "长度不能超过{max},不能少于{min}",max = 50,min =1 )
    private String classification;

    private LocalDateTime createtime;

    private LocalDateTime updatetime;

    private Boolean enabled;

    private List<Classification> Classlist;

    public List<Classification> getClasslist() {
        return Classlist;
    }

    public void setClasslist(List<Classification> classlist) {
        Classlist = classlist;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public Integer getSuperiorid() {
        return superiorid;
    }

    public String getClassification() {
        return classification;
    }

    public LocalDateTime getCreatetime() {
        return createtime;
    }

    public LocalDateTime getUpdatetime() {
        return updatetime;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setSuperiorid(Integer superiorid) {
        this.superiorid = superiorid;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public void setCreatetime(LocalDateTime createtime) {
        this.createtime = createtime;
    }

    public void setUpdatetime(LocalDateTime updatetime) {
        this.updatetime = updatetime;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "Classification{" +
                "id=" + id +
                ", superiorid=" + superiorid +
                ", classification='" + classification + '\'' +
                ", createtime=" + createtime +
                ", updatetime=" + updatetime +
                ", enabled=" + enabled +
                '}';
    }
}
