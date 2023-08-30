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
 * @since 2022-01-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Interface对象", description="")
public class Interface implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer classificationid;
    @ApiModelProperty(value = "接口名称")
    @NotBlank(message = "接口名称不能为空")
    @Length(message = "接口名称长度不能大于{max}",max=500)
    private String interfaceName;
    @ApiModelProperty(value = "接口url")
    @NotBlank(message = "接口url不能为空")
    @Length(message = "接口url长度不能大于{max}",max=500)
    private String interfaceUrl;
    @ApiModelProperty(value = "请求方式")
    @NotBlank(message = "请求方式不能为空")
    @Length(message = "请求方式长度不能大于{max}",max=10)
    private String method;

    private LocalDateTime createtime;

    private LocalDateTime updateTime;

    private Integer isdelete;
    @ApiModelProperty(value = "域名")
    @NotBlank(message = "域名不能为空")
    @Length(message = "域名长度不能大于{max}",max=50)
    private String domainname;

    public Integer getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }

    public String getDomainname() {
        return domainname;
    }

    public void setDomainname(String domainname) {
        this.domainname = domainname;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public Integer getClassificationid() {
        return classificationid;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public String getInterfaceUrl() {
        return interfaceUrl;
    }

    public String getMethod() {
        return method;
    }

    public LocalDateTime getCreatetime() {
        return createtime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public void setClassificationid(Integer classificationid) {
        this.classificationid = classificationid;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public void setInterfaceUrl(String interfaceUrl) {
        this.interfaceUrl = interfaceUrl;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setCreatetime(LocalDateTime createtime) {
        this.createtime = createtime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }


    @Override
    public String toString() {
        return "Interface{" +
                "id=" + id +
                ", classificationid=" + classificationid +
                ", interfaceName='" + interfaceName + '\'' +
                ", interfaceUrl='" + interfaceUrl + '\'' +
                ", method='" + method + '\'' +
                ", createtime=" + createtime +
                ", updateTime=" + updateTime +
                ", isdelete=" + isdelete +
                ", domainname='" + domainname + '\'' +
                '}';
    }
}
