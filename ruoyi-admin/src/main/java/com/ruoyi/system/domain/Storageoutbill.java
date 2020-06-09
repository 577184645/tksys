package com.ruoyi.system.domain;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 出库单列表对象 storageoutbill
 * 
 * @author ruoyi
 * @date 2020-06-09
 */
public class Storageoutbill extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
    private Long id;

    /** 出库单号 */
    @Excel(name = "出库单号")
    private String storageoutid;

    /** 外协库 */
    @Excel(name = "外协库")
    private String outsourcewarehouse;

    /** 外协库id */
    private Long outsourcewarehouseid;

    /** 领料单 */
    @Excel(name = "领料单")
    private String stockrequisition;

    /** 库管员 */
    @Excel(name = "库管员")
    private String warehouseadmin;

    /** 领料人 */
    @Excel(name = "领料人")
    private String stockpeople;

    /** 出库日期 */
    @Excel(name = "出库日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date storageouttime;

    /** 备注 */
    @Excel(name = "备注")
    private String comments;

    /** 创建时间 */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date cTime;

    /** 修改时间 */
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date uTime;

    /** 删除状态 */
    private Long delStatus;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setStorageoutid(String storageoutid) 
    {
        this.storageoutid = storageoutid;
    }

    public String getStorageoutid() 
    {
        return storageoutid;
    }
    public void setOutsourcewarehouse(String outsourcewarehouse) 
    {
        this.outsourcewarehouse = outsourcewarehouse;
    }

    public String getOutsourcewarehouse() 
    {
        return outsourcewarehouse;
    }
    public void setOutsourcewarehouseid(Long outsourcewarehouseid) 
    {
        this.outsourcewarehouseid = outsourcewarehouseid;
    }

    public Long getOutsourcewarehouseid() 
    {
        return outsourcewarehouseid;
    }
    public void setStockrequisition(String stockrequisition) 
    {
        this.stockrequisition = stockrequisition;
    }

    public String getStockrequisition() 
    {
        return stockrequisition;
    }
    public void setWarehouseadmin(String warehouseadmin) 
    {
        this.warehouseadmin = warehouseadmin;
    }

    public String getWarehouseadmin() 
    {
        return warehouseadmin;
    }
    public void setStockpeople(String stockpeople) 
    {
        this.stockpeople = stockpeople;
    }

    public String getStockpeople() 
    {
        return stockpeople;
    }
    public void setStorageouttime(Date storageouttime) 
    {
        this.storageouttime = storageouttime;
    }

    public Date getStorageouttime() 
    {
        return storageouttime;
    }
    public void setComments(String comments) 
    {
        this.comments = comments;
    }

    public String getComments() 
    {
        return comments;
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
    public void setDelStatus(Long delStatus) 
    {
        this.delStatus = delStatus;
    }

    public Long getDelStatus() 
    {
        return delStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("storageoutid", getStorageoutid())
            .append("outsourcewarehouse", getOutsourcewarehouse())
            .append("outsourcewarehouseid", getOutsourcewarehouseid())
            .append("stockrequisition", getStockrequisition())
            .append("warehouseadmin", getWarehouseadmin())
            .append("stockpeople", getStockpeople())
            .append("storageouttime", getStorageouttime())
            .append("comments", getComments())
            .append("cTime", getcTime())
            .append("uTime", getuTime())
            .append("delStatus", getDelStatus())
            .toString();
    }
}
