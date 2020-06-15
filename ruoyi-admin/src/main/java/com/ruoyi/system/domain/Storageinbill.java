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

    /** 备注 */
    @Excel(name = "备注")
    private String comments;

    /** 删除状态 */
    private Long delStatus;

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

    public Long getDelStatus() {
        return delStatus;
    }

    public void setDelStatus(Long delStatus) {
        this.delStatus = delStatus;
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
            .append("delStatus", getDelStatus())
            .append("cTime", getcTime())
            .append("uTime", getuTime())
            .toString();
    }
}
