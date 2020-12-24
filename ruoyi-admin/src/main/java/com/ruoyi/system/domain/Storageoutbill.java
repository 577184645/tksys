package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 出库单列表对象 storageoutbill
 * 
 * @author ruoyi
 * @date 2020-06-09
 */
public class Storageoutbill extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
    private Long id;

    /** 出库单号 */
    @Excel(name = "出库单号")
    private String storageoutid;


    /** 外协库 */
    @Excel(name = "外协库")
    private String outsourcewarehouse;

    /** 外协库id */
    private Long outsourcewarehouseid;

    /** 领料单 */
    @Excel(name = "领料单")
    private String stockrequisition;

    /** 库管员 */
    @Excel(name = "库管员")
    private String warehouseadmin;

    /** 领料人 */
    @Excel(name = "领料人")
    private String stockpeople;




    @Excel(name = "采购单号")
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




    @Excel(name = "供应商")
    private String customer;

    /** 快递单号 */
    @Excel(name = "快递单号")
    private String expressid;

    /** 进库原因 */
    @Excel(name = "出库原因")
    private String instoragecause;

    /** 所属项目 */
    @Excel(name = "所属项目")
    private String projectname;

    /** 申请人 */
    @Excel(name = "申请人")
    private String proposer;

    /** 出库日期 */
    @Excel(name = "出库日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date storageouttime;

    /** 备注 */
    @Excel(name = "备注")
    private String comments;

    /** 创建时间 */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date cTime;

    /** 修改时间 */
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date uTime;


    private double money;

    public String getPurchaseid() {
        return purchaseid;
    }

    public void setPurchaseid(String purchaseid) {
        this.purchaseid = purchaseid;
    }

    /** 删除状态 */
    private Long delStatus;




    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setStorageoutid(String storageoutid) 
    {
        this.storageoutid = storageoutid;
    }

    public String getStorageoutid() 
    {
        return storageoutid;
    }
    public void setOutsourcewarehouse(String outsourcewarehouse) 
    {
        this.outsourcewarehouse = outsourcewarehouse;
    }

    public String getOutsourcewarehouse() 
    {
        return outsourcewarehouse;
    }
    public void setOutsourcewarehouseid(Long outsourcewarehouseid) 
    {
        this.outsourcewarehouseid = outsourcewarehouseid;
    }

    public Long getOutsourcewarehouseid() 
    {
        return outsourcewarehouseid;
    }
    public void setStockrequisition(String stockrequisition) 
    {
        this.stockrequisition = stockrequisition;
    }

    public String getStockrequisition() 
    {
        return stockrequisition;
    }
    public void setWarehouseadmin(String warehouseadmin) 
    {
        this.warehouseadmin = warehouseadmin;
    }




    public String getWarehouseadmin()
    {
        return warehouseadmin;
    }
    public void setStockpeople(String stockpeople) 
    {
        this.stockpeople = stockpeople;
    }

    public String getStockpeople() 
    {
        return stockpeople;
    }
    public void setStorageouttime(Date storageouttime) 
    {
        this.storageouttime = storageouttime;
    }

    public Date getStorageouttime() 
    {
        return storageouttime;
    }
    public void setComments(String comments) 
    {
        this.comments = comments;
    }

    public String getComments() 
    {
        return comments;
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
    public void setDelStatus(Long delStatus) 
    {
        this.delStatus = delStatus;
    }

    public String getApplyid() {
        return applyid;
    }

    public void setApplyid(String applyid) {
        this.applyid = applyid;
    }

    public String getContractid() {
        return contractid;
    }

    public void setContractid(String contractid) {
        this.contractid = contractid;
    }

    public String getInvoiceid() {
        return invoiceid;
    }

    public void setInvoiceid(String invoiceid) {
        this.invoiceid = invoiceid;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getExpressid() {
        return expressid;
    }

    public void setExpressid(String expressid) {
        this.expressid = expressid;
    }

    public String getInstoragecause() {
        return instoragecause;
    }

    public void setInstoragecause(String instoragecause) {
        this.instoragecause = instoragecause;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public String getProposer() {
        return proposer;
    }

    public void setProposer(String proposer) {
        this.proposer = proposer;
    }

    public Long getDelStatus()
    {
        return delStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("storageoutid", getStorageoutid())
            .append("outsourcewarehouse", getOutsourcewarehouse())
            .append("outsourcewarehouseid", getOutsourcewarehouseid())
            .append("stockrequisition", getStockrequisition())
            .append("warehouseadmin", getWarehouseadmin())
            .append("stockpeople", getStockpeople())
            .append("storageouttime", getStorageouttime())
            .append("comments", getComments())
            .append("cTime", getcTime())
            .append("uTime", getuTime())
            .append("delStatus", getDelStatus())
            .toString();
    }
}
