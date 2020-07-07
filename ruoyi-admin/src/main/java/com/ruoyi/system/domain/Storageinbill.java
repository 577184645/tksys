package com.ruoyi.system.domain;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 入库单列表对象 storageinbill
 * 
 * @author ruoyi
 * @date 2020-06-04
 */
public class Storageinbill extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
    private Long id;

    private Double money;

    /** 入库单号 */
    @Excel(name = "入库单号")
    private String stockinid;

    /** 外协库 */
    @Excel(name = "外协库")
    private String outsourcewarehouse;

    /** 采购单号 */
    @Excel(name = "采购单号")
    private String purchaseid;

    /** 送货人 */
    @Excel(name = "送货人")
    private String deliveryman;

    /** 库管员 */
    @Excel(name = "库管员")
    private String warehouseadmin;

    /** 入库时间 */
    @Excel(name = "入库时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date storagentime;


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
    private String supplier;

    /** 快递单号 */
    @Excel(name = "快递单号")
    private String expressid;

    /** 进库原因 */
    @Excel(name = "入库原因")
    private String instoragecause;

    /** 所属项目 */
    @Excel(name = "所属项目")
    private String projectname;

    /** 申请人 */
    @Excel(name = "申请人")
    private String proposer;

    /** 备注 */
    @Excel(name = "备注")
    private String comments;



    private String delStatus;

    /** 删除状态 */
    private Long status;

    /** 创建时间 */
    private Date cTime;

    private String Supplier;

    /** 修改时间 */
    private Date uTime;

    private Long outsourcewarehouseid;

    public String getSupplier() {
        return Supplier;
    }

    public void setSupplier(String supplier) {
        Supplier = supplier;
    }

    public String getDelStatus() {
        return delStatus;
    }

    public void setDelStatus(String delStatus) {
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

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Long getOutsourcewarehouseid() {
        return outsourcewarehouseid;
    }

    public void setOutsourcewarehouseid(Long outsourcewarehouseid) {
        this.outsourcewarehouseid = outsourcewarehouseid;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setStockinid(String stockinid) 
    {
        this.stockinid = stockinid;
    }

    public String getStockinid() 
    {
        return stockinid;
    }
    public void setOutsourcewarehouse(String outsourcewarehouse) 
    {
        this.outsourcewarehouse = outsourcewarehouse;
    }

    public String getOutsourcewarehouse() 
    {
        return outsourcewarehouse;
    }
    public void setPurchaseid(String purchaseid) 
    {
        this.purchaseid = purchaseid;
    }

    public String getPurchaseid() 
    {
        return purchaseid;
    }
    public void setDeliveryman(String deliveryman) 
    {
        this.deliveryman = deliveryman;
    }

    public String getDeliveryman() 
    {
        return deliveryman;
    }
    public void setWarehouseadmin(String warehouseadmin) 
    {
        this.warehouseadmin = warehouseadmin;
    }

    public String getWarehouseadmin() 
    {
        return warehouseadmin;
    }
    public void setStoragentime(Date storagentime) 
    {
        this.storagentime = storagentime;
    }

    public Date getStoragentime() 
    {
        return storagentime;
    }
    public void setComments(String comments) 
    {
        this.comments = comments;
    }

    public String getComments() 
    {
        return comments;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
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
            .append("stockinid", getStockinid())
            .append("outsourcewarehouse", getOutsourcewarehouse())
            .append("purchaseid", getPurchaseid())
            .append("deliveryman", getDeliveryman())
            .append("warehouseadmin", getWarehouseadmin())
            .append("storagentime", getStoragentime())
            .append("comments", getComments())
            .append("cTime", getcTime())
            .append("uTime", getuTime())
            .toString();
    }
}