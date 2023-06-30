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
public class Cases implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用例标题")
    private String caseTitle;
//    @NotBlank(message = "请求体不能为空")
    @Length(message = "长度不能超过{max}",max = 500)
    @ApiModelProperty(value = "请求体")
    private String caserequest;

    @ApiModelProperty(value = "取response中数据的key，中间“,”连接，如{'code':'data.code','massage':'data.massage'")
    private String outputParameter;

    @ApiModelProperty(value = "header信息头，如{'code':'code','massage':'massage'}")
    private String headers;

    @ApiModelProperty(value = "断言变量，取json路由，如code就取'data.code'")
    private String asserts;

    @ApiModelProperty(value = "断言结果")
    private String assertresult;

    @ApiModelProperty(value = "接口的变量key，如'code,massage'")
    private String inputlist;

    private LocalDateTime createtime;

    private LocalDateTime updateTime;

    private Integer isdelete;
    @ApiModelProperty(value = "接口id")
    private int interfaceid;
    @ApiModelProperty(value = "断言类型")
    private String asserttype;

    @ApiModelProperty(value = "接口")
    private Interface face;
    private Integer sceneid;
    private Integer performtasksid;
    private Boolean assertionresults;

    public Boolean getAssertionresults() {
        return assertionresults;
    }

    public void setAssertionresults(Boolean assertionresults) {
        this.assertionresults = assertionresults;
    }

    public Integer getSceneid() {
        return sceneid;
    }

    public void setSceneid(Integer sceneid) {
        this.sceneid = sceneid;
    }

    public Integer getPerformtasksid() {
        return performtasksid;
    }

    public void setPerformtasksid(Integer performtasksid) {
        this.performtasksid = performtasksid;
    }

    @Override
    public String toString() {
        return "Caselist{" +
                "id=" + id +
                ", caseTitle='" + caseTitle + '\'' +
                ", caserequest='" + caserequest + '\'' +
                ", outputParameter='" + outputParameter + '\'' +
                ", headers='" + headers + '\'' +
                ", asserts='" + asserts + '\'' +
                ", assertresult='" + assertresult + '\'' +
                ", inputlist='" + inputlist + '\'' +
                ", createtime=" + createtime +
                ", updateTime=" + updateTime +
                ", isdelete=" + isdelete +
                ", interfaceid=" + interfaceid +
                ", asserttype='" + asserttype + '\'' +
                ", face=" + face +
                '}';
    }

    public Integer getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
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


    public String getOutputParameter() {
        return outputParameter;
    }

    public void setOutputParameter(String outputParameter) {
        this.outputParameter = outputParameter;
    }

    public String getHeaders() {
        return headers;
    }

    public void setHeaders(String headers) {
        this.headers = headers;
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

    public String getInputlist() {
        return inputlist;
    }

    public void setInputlist(String inputlist) {
        this.inputlist = inputlist;
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
}
