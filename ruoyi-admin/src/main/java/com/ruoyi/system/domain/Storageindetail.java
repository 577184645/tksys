package com.ruoyi.system.domain;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 入库产品列表对象 storageindetail
 * 
 * @author ruoyi
 * @date 2020-06-05
 */
public class Storageindetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
    private Long id;

    /** 所属入库单 */
    @Excel(name = "所属入库单")
    private String storageinbillid;

    /** 物料代码 */
    @Excel(name = "物料代码")
    private String materialcode;

    /** 物料名称 */
    @Excel(name = "物料名称")
    private String name;

    /** 物料型号 */
    @Excel(name = "物料型号")
    private String partnumber;

    /** 品牌 */
    @Excel(name = "品牌")
    private String manufacture;

    /** 单价 */
    @Excel(name = "单价")
    private Double price;

    /** 数量 */
    @Excel(name = "数量")
    private Long counts;

    /** 总金额 */
    @Excel(name = "总金额")
    private Double money;

    /** 单位 */
    @Excel(name = "单位")
    private String unit;

    /** 供应商 */
    @Excel(name = "供应商")
    private String supplier;

    /** 税率 */
    @Excel(name = "税率")
    private String rate;

    /** 采购编号 */
    @Excel(name = "采购编号")
    private String purchaseid;

    /** 申请单号 */
    @Excel(name = "申请单号")
    private String applyid;

    /** 合同单号 */
    @Excel(name = "合同单号")
    private String contractid;

    /** 发票单号 */
    @Excel(name = "发票单号")
    private String invoiceid;

    /** 快递单号 */
    @Excel(name = "快递单号")
    private String expressid;

    /** 进库原因 */
    @Excel(name = "进库原因")
    private String instoragecause;

    /** 所属项目 */
    @Excel(name = "所属项目")
    private String projectname;

    /** 备注 */
    @Excel(name = "备注")
    private String comments;

    @Excel(name = "封装")
   private String footprint;

    /** 申请人 */
    @Excel(name = "申请人")
    private String proposer;


    @Excel(name = "序列号")
    private  String serialNumber;

    /** 税额 */
    @Excel(name = "税额")
    private Double taxamount;

    /** 创建时间 */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date cTime;

    /** 修改时间 */
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date uTime;

    public void setFootprint(String footprint) {
        this.footprint = footprint;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getFootprint() {
        return footprint;
    }

    public Long getId()
    {
        return id;
    }
    public void setStorageinbillid(String storageinbillid)
    {
        this.storageinbillid = storageinbillid;
    }

    public String getStorageinbillid()
    {
        return storageinbillid;
    }
    public void setMaterialcode(String materialcode) 
    {
        this.materialcode = materialcode;
    }

    public String getMaterialcode() 
    {
        return materialcode;
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
    public void setManufacture(String manufacture) 
    {
        this.manufacture = manufacture;
    }

    public String getManufacture() 
    {
        return manufacture;
    }
    public void setPrice(Double price) 
    {
        this.price = price;
    }

    public Double getPrice() 
    {
        return price;
    }
    public void setCounts(Long counts)
    {
        this.counts = counts;
    }

    public Long getCounts()
    {
        return counts;
    }
    public void setMoney(Double money) 
    {
        this.money = money;
    }

    public Double getMoney() 
    {
        return money;
    }
    public void setUnit(String unit) 
    {
        this.unit = unit;
    }

    public String getUnit() 
    {
        return unit;
    }
    public void setSupplier(String supplier) 
    {
        this.supplier = supplier;
    }

    public String getSupplier() 
    {
        return supplier;
    }
    public void setRate(String rate) 
    {
        this.rate = rate;
    }

    public String getRate() 
    {
        return rate;
    }
    public void setPurchaseid(String purchaseid) 
    {
        this.purchaseid = purchaseid;
    }

    public String getPurchaseid() 
    {
        return purchaseid;
    }
    public void setApplyid(String applyid) 
    {
        this.applyid = applyid;
    }

    public String getApplyid() 
    {
        return applyid;
    }
    public void setContractid(String contractid) 
    {
        this.contractid = contractid;
    }

    public String getContractid() 
    {
        return contractid;
    }
    public void setInvoiceid(String invoiceid) 
    {
        this.invoiceid = invoiceid;
    }

    public String getInvoiceid() 
    {
        return invoiceid;
    }
    public void setExpressid(String expressid) 
    {
        this.expressid = expressid;
    }

    public String getExpressid() 
    {
        return expressid;
    }
    public void setInstoragecause(String instoragecause) 
    {
        this.instoragecause = instoragecause;
    }

    public String getInstoragecause() 
    {
        return instoragecause;
    }
    public void setProjectname(String projectname) 
    {
        this.projectname = projectname;
    }

    public String getProjectname() 
    {
        return projectname;
    }
    public void setComments(String comments) 
    {
        this.comments = comments;
    }

    public String getComments() 
    {
        return comments;
    }
    public void setProposer(String proposer) 
    {
        this.proposer = proposer;
    }

    public String getProposer() 
    {
        return proposer;
    }
    public void setTaxamount(Double taxamount) 
    {
        this.taxamount = taxamount;
    }

    public Double getTaxamount() 
    {
        return taxamount;
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
            .append("storageinbillid", getStorageinbillid())
            .append("materialcode", getMaterialcode())
            .append("name", getName())
            .append("partnumber", getPartnumber())
            .append("manufacture", getManufacture())
            .append("price", getPrice())
            .append("counts", getCounts())
            .append("money", getMoney())
            .append("unit", getUnit())
            .append("supplier", getSupplier())
            .append("rate", getRate())
            .append("purchaseid", getPurchaseid())
            .append("applyid", getApplyid())
            .append("contractid", getContractid())
            .append("invoiceid", getInvoiceid())
            .append("expressid", getExpressid())
            .append("instoragecause", getInstoragecause())
            .append("projectname", getProjectname())
            .append("comments", getComments())
            .append("proposer", getProposer())
            .append("taxamount", getTaxamount())
            .append("cTime", getcTime())
            .append("uTime", getuTime())
            .toString();
    }
}
