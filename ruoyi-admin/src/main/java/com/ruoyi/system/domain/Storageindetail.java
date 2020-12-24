package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

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

    private Long sid;

    /** 所属入库单 */
    @Excel(name = "所属出库单")
    private String storageinbillid;

    @Excel(name = "物料编码")
    private  String materialcode;
    /** 单价 */
    @Excel(name = "单价")
    private Double price;

    /** 数量 */
    @Excel(name = "数量")
    private Long counts;

    /** 总金额 */
    @Excel(name = "总金额")
    private Double money;



    /** 供应商 */
    @Excel(name = "供应商")
    private String supplier;

    /** 税率 */
    @Excel(name = "税率")
    private String rate;


    /** 备注 */
    @Excel(name = "备注")
    private String comments;



    @Excel(name = "序列号")
    private  String serialNumber;

    /** 税额 */
    @Excel(name = "税额")
    private Double taxamount;





    public String getRate() {
        return rate;
    }

    public String getComments() {
        return comments;
    }


    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public void setComments(String comments) {
        this.comments = comments;
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


    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setCounts(Long counts)
    {
        this.counts = counts;
    }

    public Long getCounts()
    {
        return counts;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
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


    public void setTaxamount(Double taxamount) 
    {
        this.taxamount = taxamount;
    }

    public Double getTaxamount() 
    {
        return taxamount;
    }

    public String getMaterialcode() {
        return materialcode;
    }

    public void setMaterialcode(String materialcode) {
        this.materialcode = materialcode;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("storageinbillid", getStorageinbillid())
            .append("materialcode", getMaterialcode())
            .append("price", getPrice())
            .append("counts", getCounts())
            .append("money", getMoney())
            .append("supplier", getSupplier())
            .append("taxamount", getTaxamount())
            .toString();
    }
}
