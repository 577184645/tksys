package com.ruoyi.system.domain;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 库存记录对象 warehouse_record
 * 
 * @author ruoyi
 * @date 2020-06-24
 */
public class WarehouseRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 类型 */
    @Excel(name = "类型")
    private Integer type;

    /** 单号 */
    @Excel(name = "单号")
    private String number;

    /** 物料编号 */
    @Excel(name = "物料编号")
    private String materialcode;

    /** 商品名称 */
    @Excel(name = "商品名称")
    private String name;

    /** 数量 */
    @Excel(name = "数量")
    private Long count;

    /** 单价 */
    @Excel(name = "单价")
    private Float price;

    /** 总价 */
    @Excel(name = "总价")
    private Float money;

    /** 供应商 */
    @Excel(name = "供应商")
    private String supplier;

    /** 序列号 */
    @Excel(name = "序列号")
    private String serialNumber;

    /** 状态 */
    @Excel(name = "状态")
    private String delStatus;

    /** 时间 */
    @Excel(name = "时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date cTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setNumber(String number)
    {
        this.number = number;
    }

    public String getNumber() 
    {
        return number;
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
    public void setCount(Long count) 
    {
        this.count = count;
    }

    public Long getCount() 
    {
        return count;
    }
    public void setPrice(Float price)
    {
        this.price = price;
    }

    public Float getPrice()
    {
        return price;
    }
    public void setMoney(Float money)
    {
        this.money = money;
    }

    public Float getMoney()
    {
        return money;
    }
    public void setSupplier(String supplier) 
    {
        this.supplier = supplier;
    }

    public String getSupplier() 
    {
        return supplier;
    }
    public void setSerialNumber(String serialNumber) 
    {
        this.serialNumber = serialNumber;
    }

    public String getSerialNumber() 
    {
        return serialNumber;
    }
    public void setDelStatus(String delStatus) 
    {
        this.delStatus = delStatus;
    }

    public String getDelStatus() 
    {
        return delStatus;
    }
    public void setcTime(Date cTime) 
    {
        this.cTime = cTime;
    }

    public Date getcTime() 
    {
        return cTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("type", getType())
            .append("number", getNumber())
            .append("materialcode", getMaterialcode())
            .append("name", getName())
            .append("count", getCount())
            .append("price", getPrice())
            .append("money", getMoney())
            .append("supplier", getSupplier())
            .append("serialNumber", getSerialNumber())
            .append("delStatus", getDelStatus())
            .append("cTime", getcTime())
            .toString();
    }
}
