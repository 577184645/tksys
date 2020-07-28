package com.ruoyi.system.domain;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * bom列表对象 bom
 * 
 * @author ruoyi
 * @date 2020-07-24
 */
public class Bom extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
    private Long id;

    /** 编号 */
    @Excel(name = "编号")
    private String number;

    /** 版本 */
    @Excel(name = "版本")
    private String version;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 型号 */
    @Excel(name = "型号")
    private String partnumber;

    /** 描述 */
    @Excel(name = "描述")
    private String description;

    /** 发布状态 */
    @Excel(name = "发布状态")
    private String publishstatus;

    /** 所属项目 */
    @Excel(name = "所属项目")
    private String project;

    /** 产品大类 */
    @Excel(name = "产品大类")
    private String producttype;

    /** 价格 */
    @Excel(name = "价格")
    private Double price;

    /** 设计者 */
    @Excel(name = "设计者")
    private String designer;

    /** 检查者 */
    @Excel(name = "检查者")
    private String scrutineer;

    /** 批准者 */
    @Excel(name = "批准者")
    private String approver;

    /** 制作时间 */
    @Excel(name = "制作时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date timeofmaking;

    /** 检查时间 */
    @Excel(name = "检查时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date inspectiontime;

    /** 批准时间 */
    @Excel(name = "批准时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date approvaltime;

    /** 创建时间 */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date cTime;

    /** 修改时间 */
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date uTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setNumber(String number) 
    {
        this.number = number;
    }

    public String getNumber() 
    {
        return number;
    }
    public void setVersion(String version) 
    {
        this.version = version;
    }

    public String getVersion() 
    {
        return version;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setPartnumber(String partnumber) 
    {
        this.partnumber = partnumber;
    }

    public String getPartnumber() 
    {
        return partnumber;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setPublishstatus(String publishstatus) 
    {
        this.publishstatus = publishstatus;
    }

    public String getPublishstatus() 
    {
        return publishstatus;
    }
    public void setProject(String project) 
    {
        this.project = project;
    }

    public String getProject() 
    {
        return project;
    }
    public void setProducttype(String producttype) 
    {
        this.producttype = producttype;
    }

    public String getProducttype() 
    {
        return producttype;
    }
    public void setPrice(Double price) 
    {
        this.price = price;
    }

    public Double getPrice() 
    {
        return price;
    }
    public void setDesigner(String designer) 
    {
        this.designer = designer;
    }

    public String getDesigner() 
    {
        return designer;
    }
    public void setScrutineer(String scrutineer) 
    {
        this.scrutineer = scrutineer;
    }

    public String getScrutineer() 
    {
        return scrutineer;
    }
    public void setApprover(String approver) 
    {
        this.approver = approver;
    }

    public String getApprover() 
    {
        return approver;
    }
    public void setTimeofmaking(Date timeofmaking) 
    {
        this.timeofmaking = timeofmaking;
    }

    public Date getTimeofmaking() 
    {
        return timeofmaking;
    }
    public void setInspectiontime(Date inspectiontime) 
    {
        this.inspectiontime = inspectiontime;
    }

    public Date getInspectiontime() 
    {
        return inspectiontime;
    }
    public void setApprovaltime(Date approvaltime) 
    {
        this.approvaltime = approvaltime;
    }

    public Date getApprovaltime() 
    {
        return approvaltime;
    }
    public void setcTime(Date cTime) 
    {
        this.cTime = cTime;
    }

    public Date getcTime() 
    {
        return cTime;
    }
    public void setuTime(Date uTime) 
    {
        this.uTime = uTime;
    }

    public Date getuTime() 
    {
        return uTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("number", getNumber())
            .append("version", getVersion())
            .append("name", getName())
            .append("partnumber", getPartnumber())
            .append("description", getDescription())
            .append("publishstatus", getPublishstatus())
            .append("project", getProject())
            .append("producttype", getProducttype())
            .append("price", getPrice())
            .append("designer", getDesigner())
            .append("scrutineer", getScrutineer())
            .append("approver", getApprover())
            .append("timeofmaking", getTimeofmaking())
            .append("inspectiontime", getInspectiontime())
            .append("approvaltime", getApprovaltime())
            .append("remark", getRemark())
            .append("cTime", getcTime())
            .append("uTime", getuTime())
            .toString();
    }
}
