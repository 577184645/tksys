package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * bom详细清单对象 bomdetail
 * 
 * @author ruoyi
 * @date 2020-09-28
 */
public class Bomdetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer id;

    /** 所属bom编号 */
    @Excel(name = "所属bom编号")
    private Long bomid;


    private String msid;

    private String  ssid;

    /** Comment */
    @Excel(name = "Comment")
    private String comment;

    /** Footprint */
    @Excel(name = "Footprint")
    private String footprint;

    /** Description */
    @Excel(name = "Description")
    private String description;

    /** Designator */
    @Excel(name = "Designator")
    private String designator;

    /** Quantity */
    @Excel(name = "Quantity")
    private Integer quantity;

    /** 生产数量 */
    @Excel(name = "生产数量")
    private Integer count;

    /** 生产总用量 */
    @Excel(name = "生产总用量")
    private Integer sumcount;

    @Excel(name = "价格")
    private Double price;
    @Excel(name = "供应商")
    private String supplier;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getBomid() {
        return bomid;
    }

    public void setBomid(Long bomid) {
        this.bomid = bomid;
    }

    public String getMsid() {
        return msid;
    }

    public void setMsid(String msid) {
        this.msid = msid;
    }

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
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
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("bomid", getBomid())
            .append("sid", getSsid())
            .append("comment", getComment())
            .append("footprint", getFootprint())
            .append("description", getDescription())
            .append("designator", getDesignator())
            .append("quantity", getQuantity())
            .append("count", getCount())
            .append("sumcount", getSumcount())
            .toString();
    }
}
