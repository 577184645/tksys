package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

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

    /** 编码 */
    @Excel(name = "编码")
    private String materialcode;



    /** 单价 */
    @Excel(name = "单价")
    private Double price;

    /** 库存类型 */
    private Long deptId;


    /** 物料id */
   private Long  materialId;



    /** 库存量 */
    @Excel(name = "库存量")
    private Long stocks;

    /** 总价 */
    @Excel(name = "总价")
    private Double money;

    /** 库存类别 */
    private Long typeId;


    /** 是否为0 */
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

    //部门名称
    private String   deptName;






    /**
     * 扩展字段
     * @return
     */
    /** 库存类型信息 */
    private Storagetype  storagetype;
    private String name;
    private String partnumber;
    private String description;
    private String footprint;
    private String manufacture;
    public Long getDeptId() {
        return deptId;
    }
    public String getName() {
        return name;
    }


    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPartnumber() {
        return partnumber;
    }

    public void setPartnumber(String partnumber) {
        this.partnumber = partnumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFootprint() {
        return footprint;
    }

    public void setFootprint(String footprint) {
        this.footprint = footprint;
    }

    public String getManufacture() {
        return manufacture;
    }

    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }

    public Storagetype getStoragetype() {
        return storagetype;
    }

    public void setStoragetype(Storagetype storagetype) {
        this.storagetype = storagetype;
    }

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



    public Date getoTime() {
        return oTime;
    }

    public void setoTime(Date oTime) {
        this.oTime = oTime;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    public void setMaterialcode(String materialcode) 
    {
        this.materialcode = materialcode;
    }

    public String getMaterialcode() 
    {
        return materialcode;
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
            .append("materialcode", getMaterialcode())
            .append("price", getPrice())
            .append("stocks", getStocks())
            .append("money", getMoney())
            .append("typeId", getTypeId())
            .append("cTime", getcTime())
            .append("uTime", getuTime())
            .toString();
    }
}
