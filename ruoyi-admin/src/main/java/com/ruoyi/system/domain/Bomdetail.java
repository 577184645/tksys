package com.ruoyi.system.domain;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * bom详细清单对象 bomdetail
 * 
 * @author ruoyi
 * @date 2020-07-24
 */
public class Bomdetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Integer id;

    /** 所属bom编号 */
  /*  @Excel(name = "所属bom编号")*/
    private Long bomid;



    /** 编码 */
    @Excel(name = "编码")
    private String materialcode;


    /** 名称 */
    @Excel(name = "名称")
    private String name;



    /** 型号 */
    @Excel(name = "型号")
    private String partnumber;

    /** 单位 */
    @Excel(name = "单位")
    private String unit;

    /** 单价 */
    @Excel(name = "单价")
    private Double price;

    /** 品牌 */
    @Excel(name = "品牌")
    private String manufacture;

    /** 供应商 */
    @Excel(name = "供应商")
    private String supplier;

    /** 数量 */
    @Excel(name = "数量")
    private Integer count;

    /** 创建时间 */
   /* @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")*/
    private Date cTime;

    /** 修改时间 */
   /* @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd")*/
    private Date uTime;

    /** 交期 */
    @Excel(name = "交期")
    private Integer leadtime;

    /** 封装 */
    @Excel(name = "封装")
    private String footprint;

    /** 备注 */
    @Excel(name = "备注")
    private String comments;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setBomid(Long bomid) 
    {
        this.bomid = bomid;
    }

    public Long getBomid() 
    {
        return bomid;
    }
    public void setName(String name) 
    {
        this.name = name;
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
    public void setUnit(String unit) 
    {
        this.unit = unit;
    }

    public String getUnit() 
    {
        return unit;
    }
    public void setPrice(Double price) 
    {
        this.price = price;
    }

    public Double getPrice() 
    {
        return price;
    }
    public void setManufacture(String manufacture) 
    {
        this.manufacture = manufacture;
    }

    public String getManufacture() 
    {
        return manufacture;
    }
    public void setSupplier(String supplier) 
    {
        this.supplier = supplier;
    }

    public String getSupplier() 
    {
        return supplier;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
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
    public void setLeadtime(Integer leadtime) 
    {
        this.leadtime = leadtime;
    }

    public Integer getLeadtime() 
    {
        return leadtime;
    }
    public void setFootprint(String footprint) 
    {
        this.footprint = footprint;
    }

    public String getFootprint() 
    {
        return footprint;
    }
    public void setComments(String comments) 
    {
        this.comments = comments;
    }

    public String getComments() 
    {
        return comments;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("bomid", getBomid())
            .append("name", getName())
            .append("materialcode", getMaterialcode())
            .append("partnumber", getPartnumber())
            .append("unit", getUnit())
            .append("price", getPrice())
            .append("manufacture", getManufacture())
            .append("supplier", getSupplier())
            .append("count", getCount())
            .append("cTime", getcTime())
            .append("uTime", getuTime())
            .append("leadtime", getLeadtime())
            .append("footprint", getFootprint())
            .append("comments", getComments())
            .toString();
    }
}
