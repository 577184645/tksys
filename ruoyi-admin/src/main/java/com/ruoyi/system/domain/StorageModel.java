package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;

public class StorageModel {
    private Integer id;


    private String name;
    private String materialcode;
    private String partnumber;
    private String description;
    private String footprint;
    private String manufacture;
    private String unit;
    private double price;
    private Integer counts;
    private double money;
    private double taxamount;
    private String rate;
    private String serialNumber;
    private String supplier;
    private String purchaseid;
    private String applyid;
    private String contractid;
    private String invoiceid;
    private String expressid;
    private String instoragecause;
    private String projectname;
    private String proposer;
    private String comments;

    public StorageModel(Integer id, String name, String materialcode, String partnumber, String description, String footprint, String manufacture, String unit, double price, Integer counts, double money, double taxamount, String rate, String serialNumber, String supplier, String purchaseid, String applyid, String contractid, String invoiceid, String expressid, String instoragecause, String projectname, String proposer, String comments) {
        this.id = id;
        this.name = name;
        this.materialcode = materialcode;
        this.partnumber = partnumber;
        this.description = description;
        this.footprint = footprint;
        this.manufacture = manufacture;
        this.unit = unit;
        this.price = price;
        this.counts = counts;
        this.money = money;
        this.taxamount = taxamount;
        this.rate = rate;
        this.serialNumber = serialNumber;
        this.supplier = supplier;
        this.purchaseid = purchaseid;
        this.applyid = applyid;
        this.contractid = contractid;
        this.invoiceid = invoiceid;
        this.expressid = expressid;
        this.instoragecause = instoragecause;
        this.projectname = projectname;
        this.proposer = proposer;
        this.comments = comments;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public double getTaxamount() {
        return taxamount;
    }

    public void setTaxamount(double taxamount) {
        this.taxamount = taxamount;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getPurchaseid() {
        return purchaseid;
    }

    public void setPurchaseid(String purchaseid) {
        this.purchaseid = purchaseid;
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

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
