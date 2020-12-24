package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 物料对象 material_child
 * 
 * @author ruoyi
 * @date 2020-12-14
 */
public class MaterialChild extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
    private Integer id;

    /** 名称 */
    @Excel(name = "名称")
    private String name;


    /** 型号 */
    @Excel(name = "型号")
    private String partnumber;

    /** 描述 */
    @Excel(name = "描述")
    private String description;

    /** 封装 */
    @Excel(name = "封装")
    private String footprint;

    /** 品牌 */
    @Excel(name = "品牌")
    private String manufacture;

    /** 单位 */
    @Excel(name = "单位")
    private String unit;

    /** 物料信息注册者 */
    @Excel(name = "物料信息注册者")
    private String inputoperator;

    /** 物料信息注册时间 */
    @Excel(name = "物料信息注册时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date inputdate;

    /** 批准者 */
    @Excel(name = "批准者")
    private String approvedby;

    /** 批准时间 */
    @Excel(name = "批准时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date approveddate;

    /** 批准状态 */
    @Excel(name = "批准状态")
    private String approvedstatus;

    /** 最少包装量 */
    @Excel(name = "最少包装量")
    private Integer productmpq;

    /** 最少起订量 */
    @Excel(name = "最少起订量")
    private Integer productmoq;

    /** 交期 */
    @Excel(name = "交期")
    private Integer leadtime;

    /** 备注 */
    @Excel(name = "备注")
    private String comments;

    /** 类型id */
    @Excel(name = "类型id")
    private Long typeId;

    /** 部门 */
    @Excel(name = "部门")
    private Long deptId;

    /** 安全库存 */
    @Excel(name = "安全库存")
    private Long safestock;

    /** 供方料号 */
    @Excel(name = "供方料号")
    private String ordernumber;

    /** 采购员 */
    @Excel(name = "采购员")
    private String buyer;

    /** 删除状态 */
    private String delFlag;

    /** null */
    @Excel(name = "null")
    private Long materialId;

    /** 创建时间 */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date cTime;

    /** 修改时间 */
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date uTime;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
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
    public void setFootprint(String footprint) 
    {
        this.footprint = footprint;
    }

    public String getFootprint() 
    {
        return footprint;
    }
    public void setManufacture(String manufacture) 
    {
        this.manufacture = manufacture;
    }

    public String getManufacture() 
    {
        return manufacture;
    }
    public void setUnit(String unit) 
    {
        this.unit = unit;
    }

    public String getUnit() 
    {
        return unit;
    }
    public void setInputoperator(String inputoperator) 
    {
        this.inputoperator = inputoperator;
    }

    public String getInputoperator() 
    {
        return inputoperator;
    }
    public void setInputdate(Date inputdate) 
    {
        this.inputdate = inputdate;
    }

    public Date getInputdate() 
    {
        return inputdate;
    }
    public void setApprovedby(String approvedby) 
    {
        this.approvedby = approvedby;
    }

    public String getApprovedby() 
    {
        return approvedby;
    }
    public void setApproveddate(Date approveddate) 
    {
        this.approveddate = approveddate;
    }

    public Date getApproveddate() 
    {
        return approveddate;
    }
    public void setApprovedstatus(String approvedstatus) 
    {
        this.approvedstatus = approvedstatus;
    }

    public String getApprovedstatus() 
    {
        return approvedstatus;
    }
    public void setProductmpq(Integer productmpq) 
    {
        this.productmpq = productmpq;
    }

    public Integer getProductmpq() 
    {
        return productmpq;
    }
    public void setProductmoq(Integer productmoq) 
    {
        this.productmoq = productmoq;
    }

    public Integer getProductmoq() 
    {
        return productmoq;
    }
    public void setLeadtime(Integer leadtime) 
    {
        this.leadtime = leadtime;
    }

    public Integer getLeadtime() 
    {
        return leadtime;
    }
    public void setComments(String comments) 
    {
        this.comments = comments;
    }

    public String getComments() 
    {
        return comments;
    }
    public void setTypeId(Long typeId) 
    {
        this.typeId = typeId;
    }

    public Long getTypeId() 
    {
        return typeId;
    }
    public void setDeptId(Long deptId) 
    {
        this.deptId = deptId;
    }

    public Long getDeptId() 
    {
        return deptId;
    }
    public void setSafestock(Long safestock) 
    {
        this.safestock = safestock;
    }

    public Long getSafestock() 
    {
        return safestock;
    }
    public void setOrdernumber(String ordernumber) 
    {
        this.ordernumber = ordernumber;
    }

    public String getOrdernumber() 
    {
        return ordernumber;
    }
    public void setBuyer(String buyer) 
    {
        this.buyer = buyer;
    }

    public String getBuyer() 
    {
        return buyer;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }
    public void setMaterialId(Long materialId) 
    {
        this.materialId = materialId;
    }

    public Long getMaterialId() 
    {
        return materialId;
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
            .append("name", getName())
            .append("partnumber", getPartnumber())
            .append("description", getDescription())
            .append("footprint", getFootprint())
            .append("manufacture", getManufacture())
            .append("unit", getUnit())
            .append("inputoperator", getInputoperator())
            .append("inputdate", getInputdate())
            .append("approvedby", getApprovedby())
            .append("approveddate", getApproveddate())
            .append("approvedstatus", getApprovedstatus())
            .append("productmpq", getProductmpq())
            .append("productmoq", getProductmoq())
            .append("leadtime", getLeadtime())
            .append("comments", getComments())
            .append("typeId", getTypeId())
            .append("deptId", getDeptId())
            .append("safestock", getSafestock())
            .append("ordernumber", getOrdernumber())
            .append("buyer", getBuyer())
            .append("delFlag", getDelFlag())
            .append("materialId", getMaterialId())
            .append("cTime", getcTime())
            .append("uTime", getuTime())
            .toString();
    }
}
