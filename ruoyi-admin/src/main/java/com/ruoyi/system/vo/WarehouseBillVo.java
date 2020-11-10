package com.ruoyi.system.vo;

import java.math.BigDecimal;

/**
 * @author QC
 * @create 2020-08-26 10:45
 */
public class WarehouseBillVo {
   private Integer id;
    private Integer rownum;
    private String number;
   private String name;
   private  String description;
   private  String materialcode;
   private  String partnumber;
   private  String unit;
   private BigDecimal inprice;
    private Double inmoney;
    private Integer incount;

    private BigDecimal outprice;
    private Double outmoney;
    private Integer outcount;
    private Long thiscount;
    private Float thismoney;
    private Float thisprice;



    private Long prevcount;
    private Double prevmoney;
    private BigDecimal prevprice;


    private String supplier;


    public Long getPrevcount() {
        return prevcount;
    }

    public void setPrevcount(Long prevcount) {
        this.prevcount = prevcount;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Double getPrevmoney() {
        return prevmoney;
    }

    public void setPrevmoney(Double prevmoney) {
        this.prevmoney = prevmoney;
    }

    public BigDecimal getPrevprice() {
        return prevprice;
    }

    public void setPrevprice(BigDecimal prevprice) {
        this.prevprice = prevprice;
    }

    public BigDecimal getOutprice() {
        return outprice;
    }

    public Long getThiscount() {
        return thiscount;
    }

    public void setThiscount(Long thiscount) {
        this.thiscount = thiscount;
    }

    public Float getThismoney() {
        return thismoney;
    }

    public void setThismoney(Float thismoney) {
        this.thismoney = thismoney;
    }

    public Float getThisprice() {
        return thisprice;
    }

    public void setThisprice(Float thisprice) {
        this.thisprice = thisprice;
    }

    public void setOutprice(BigDecimal outprice) {
        this.outprice = outprice;
    }

    public Double getOutmoney() {
        return outmoney;
    }

    public void setOutmoney(Double outmoney) {
        this.outmoney = outmoney;
    }

    public Integer getOutcount() {
        return outcount;
    }

    public void setOutcount(Integer outcount) {
        this.outcount = outcount;
    }

    public Integer getIncount() {
        return incount;
    }

    public Double getInmoney() {
        return inmoney;
    }

    public void setInmoney(Double inmoney) {
        this.inmoney = inmoney;
    }

    public void setIncount(Integer incount) {
        this.incount = incount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRownum() {
        return rownum;
    }

    public void setRownum(Integer rownum) {
        this.rownum = rownum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMaterialcode() {
        return materialcode;
    }

    public void setMaterialcode(String materialcode) {
        this.materialcode = materialcode;
    }

    public String getPartnumber() {
        return partnumber;
    }

    public BigDecimal getInprice() {
        return inprice;
    }

    public void setInprice(BigDecimal inprice) {
        this.inprice = inprice;
    }

    public void setInmoney(double inmoney) {
        this.inmoney = inmoney;
    }

    public void setPartnumber(String partnumber) {
        this.partnumber = partnumber;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }




    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }


    @Override
    public String toString() {
        return "WarehouseBillVo{" +
                "id=" + id +
                ", rownum=" + rownum +
                ", number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", materialcode='" + materialcode + '\'' +
                ", partnumber='" + partnumber + '\'' +
                ", unit='" + unit + '\'' +
                ", inprice=" + inprice +
                ", inmoney=" + inmoney +
                ", incount=" + incount +
                ", outprice=" + outprice +
                ", outmoney=" + outmoney +
                ", outcount=" + outcount +
                ", thiscount=" + thiscount +
                ", thismoney=" + thismoney +
                ", thisprice=" + thisprice +
                ", prevcount=" + prevcount +
                ", prevmoney=" + prevmoney +
                ", prevprice=" + prevprice +
                ", supplier='" + supplier + '\'' +
                '}';
    }
}
