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

    private Long sid;

    /** 所属入库单 */
    @Excel(name = "所属出库单")
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
    private Float price;

    /** 数量 */
    @Excel(name = "数量")
    private Long counts;

    /** 总金额 */
    @Excel(name = "总金额")
    private Float money;

    /** 单位 */
    @Excel(name = "单位")
    private String unit;

    /** 供应商 */
    @Excel(name = "供应商")
    private String supplier;

    /** 税率 */
    @Excel(name = "税率")
    private String rate;


    /** 备注 */
    @Excel(name = "备注")
    private String comments;

    @Excel(name = "封装")
   private String footprint;

    @Excel(name = "描述")
    private String description;


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

    private Integer rownum;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getRownum() {
        return rownum;
    }

    public void setRownum(Integer rownum) {
        this.rownum = rownum;
    }

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
    public void setPrice(Float price)
    {
        this.price = price;
    }

    public Float getPrice()
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
    public void setMoney(Float money)
    {
        this.money = money;
    }

    public Float getMoney()
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
            .append("taxamount", getTaxamount())
            .append("cTime", getcTime())
            .append("uTime", getuTime())
            .toString();
    }
}
