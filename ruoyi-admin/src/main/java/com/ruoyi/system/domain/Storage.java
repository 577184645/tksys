package com.ruoyi.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 库存列表对象 storage
 * 
 * @author ruoyi
 * @date 2020-06-03
 */
public class Storage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
    private Long id;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 编码 */
    @Excel(name = "编码")
    private String materialcode;

    /** 型号 */
    @Excel(name = "型号")
    private String partnumber;

    /** 封装 */
    @Excel(name = "封装")
    private String footprint;



    private  Integer leadtime;

    @Excel(name = "描述")
    private  String description;

    /** 品牌 */
    @Excel(name = "品牌")
    private String manufacture;

    /** 单位 */
    @Excel(name = "单位")
    private String unit;

    /** 单价 */
    @Excel(name = "单价")
    private Double price;


    private Long deptId;


    private Storagetype  storagetype;

    /** 库存量 */
    @Excel(name = "库存量")
    private Long stocks;

    /** 总价 */
    @Excel(name = "总价")
    private Double money;

    /** 库存类别 */
    @Excel(name = "库存类别")
    private Long typeId;

    @Excel(name = "供应商")
   private  String supplier;
    /** 安全库存 */


    /** 序列号 */
    @Excel(name = "序列号")
    private String serialNumber;



    private Integer status;

    /** 创建时间 */
    private Date cTime;

    /** 最后入库时间 */
    @Excel(name = "最后入库时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date uTime;

    /** 最后入库时间 */
    @Excel(name = "最后出库时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date oTime;

    @Excel(name = "最后退料时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date qTime;

      //所属位置
    private String  ancestors;

    private String   deptName;







    public String getAncestors() {
        return ancestors;
    }

    public void setAncestors(String ancestors) {
        this.ancestors = ancestors;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Integer getLeadtime() {
        return leadtime;
    }

    public void setLeadtime(Integer leadtime) {
        this.leadtime = leadtime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getoTime() {
        return oTime;
    }

    public void setoTime(Date oTime) {
        this.oTime = oTime;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getName()
    {
        return name;
    }
    public void setMaterialcode(String materialcode) 
    {
        this.materialcode = materialcode;
    }

    public String getMaterialcode() 
    {
        return materialcode;
    }
    public void setPartnumber(String partnumber) 
    {
        this.partnumber = partnumber;
    }

    public String getPartnumber() 
    {
        return partnumber;
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


    public Long getStocks() {
        return stocks;
    }

    public void setStocks(Long stocks) {
        this.stocks = stocks;
    }

    public Date getqTime() {
        return qTime;
    }

    public void setqTime(Date qTime) {
        this.qTime = qTime;
    }


    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public void setTypeId(Long typeId)
    {
        this.typeId = typeId;
    }

    public Long getTypeId() 
    {
        return typeId;
    }


    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
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
            .append("materialcode", getMaterialcode())
            .append("partnumber", getPartnumber())
            .append("footprint", getFootprint())
            .append("manufacture", getManufacture())
            .append("unit", getUnit())
            .append("price", getPrice())
            .append("stocks", getStocks())
            .append("money", getMoney())
            .append("typeId", getTypeId())
            .append("serialNumber", getSerialNumber())
            .append("cTime", getcTime())
            .append("uTime", getuTime())
            .toString();
    }
}
