package com.ruoyi.system.vo;

/**
 * @author QC
 * @create 2020-09-25 8:58
 */
public class BomdetailVo {
    private  Integer no;
    private  String comment;
    private  String  footprint;
    private  String description;
    private  String designator;
    private  Integer quantity;
    private  Integer count;
    private  Integer sumcount;
    private  String mmaterialcodes;
    private  String smaterialcodes;
    private  Integer price;

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getMmaterialcodes() {
        return mmaterialcodes;
    }

    public void setMmaterialcodes(String mmaterialcodes) {
        this.mmaterialcodes = mmaterialcodes;
    }

    public String getSmaterialcodes() {
        return smaterialcodes;
    }

    public void setSmaterialcodes(String smaterialcodes) {
        this.smaterialcodes = smaterialcodes;
    }

    public Integer getNo() {
        return no;
    }

    public void setNo(Integer no) {
        this.no = no;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getFootprint() {
        return footprint;
    }

    public void setFootprint(String footprint) {
        this.footprint = footprint;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDesignator() {
        return designator;
    }

    public void setDesignator(String designator) {
        this.designator = designator;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getSumcount() {
        return sumcount;
    }

    public void setSumcount(Integer sumcount) {
        this.sumcount = sumcount;
    }


    @Override
    public String toString() {
        return "BomdetailVo{" +
                "no=" + no +
                ", comment='" + comment + '\'' +
                ", footprint='" + footprint + '\'' +
                ", description='" + description + '\'' +
                ", designator='" + designator + '\'' +
                ", quantity=" + quantity +
                ", count=" + count +
                ", sumcount=" + sumcount +
                '}';
    }
}
