package com.example.server.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.time.LocalDateTime;

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
@ApiModel(value="Caselist对象", description="")
public class Caselist implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用例标题")
    private String caseTitle;
//    @NotBlank(message = "请求体不能为空")
    @Length(message = "长度不能超过{500}",max = 500)
    @ApiModelProperty(value = "请求体")
    private String caserequest;

    @ApiModelProperty(value = "入参变量名称，中间“,”连接")
    private String inputParameter;

    @ApiModelProperty(value = "出参变量名称，中间“,”连接")
    private String outputParameter;

    @ApiModelProperty(value = "header信息头")
    private String header;

    @ApiModelProperty(value = "断言变量")
    private String asserts;

    @ApiModelProperty(value = "断言结果")
    private String assertresult;

    private LocalDateTime createtime;

    private LocalDateTime updateTime;

    private Boolean enabled;
    private int interfaceid;
    private String asserttype;

    private Interface face;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCaseTitle() {
        return caseTitle;
    }

    public void setCaseTitle(String caseTitle) {
        this.caseTitle = caseTitle;
    }

    public String getCaserequest() {
        return caserequest;
    }

    public void setCaserequest(String caserequest) {
        this.caserequest = caserequest;
    }

    public String getInputParameter() {
        return inputParameter;
    }

    public void setInputParameter(String inputParameter) {
        this.inputParameter = inputParameter;
    }

    public String getOutputParameter() {
        return outputParameter;
    }

    public void setOutputParameter(String outputParameter) {
        this.outputParameter = outputParameter;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getAsserts() {
        return asserts;
    }

    public void setAsserts(String asserts) {
        this.asserts = asserts;
    }

    public String getAssertresult() {
        return assertresult;
    }

    public void setAssertresult(String assertresult) {
        this.assertresult = assertresult;
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

    public int getInterfaceid() {
        return interfaceid;
    }

    public void setInterfaceid(int interfaceid) {
        this.interfaceid = interfaceid;
    }

    public String getAsserttype() {
        return asserttype;
    }

    public void setAsserttype(String asserttype) {
        this.asserttype = asserttype;
    }

    public Interface getFace() {
        return face;
    }

    public void setFace(Interface face) {
        this.face = face;
    }

    @Override
    public String toString() {
        return "Caselist{" +
                "id=" + id +
                ", caseTitle='" + caseTitle + '\'' +
                ", caserequest='" + caserequest + '\'' +
                ", inputParameter='" + inputParameter + '\'' +
                ", outputParameter='" + outputParameter + '\'' +
                ", header='" + header + '\'' +
                ", asserts='" + asserts + '\'' +
                ", assertresult='" + assertresult + '\'' +
                ", createtime=" + createtime +
                ", updateTime=" + updateTime +
                ", enabled=" + enabled +
                ", interfaceid=" + interfaceid +
                ", asserttype='" + asserttype + '\'' +
                ", face=" + face +
                '}';
    }
}
