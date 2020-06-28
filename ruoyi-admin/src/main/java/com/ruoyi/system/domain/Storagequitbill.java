package com.ruoyi.system.domain;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 退料单列表对象 storagequitbill
 * 
 * @author ruoyi
 * @date 2020-06-18
 */
public class Storagequitbill extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 退料单号 */
    @Excel(name = "退料单号")
    private String storagequitbillid;

    /** 外协库 */
    @Excel(name = "外协库")
    private String outsourcewarehouse;

    /** 外协库id */
    @Excel(name = "外协库id")
    private Long outsourcewarehouseid;

    /** 退库人 */
    @Excel(name = "退库人")
    private String outpeople;

    /** 退库原因 */
    @Excel(name = "退库原因")
    private String outstoragecause;

    /** 备注 */
    @Excel(name = "备注")
    private String comments;

    /** 退库时间 */
    @Excel(name = "退库时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date cTime;

    /** 修改时间 */
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date uTime;

    private Integer delStatus;

    public Integer getDelStatus() {
        return delStatus;
    }

    public void setDelStatus(Integer delStatus) {
        this.delStatus = delStatus;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setStoragequitbillid(String storagequitbillid) 
    {
        this.storagequitbillid = storagequitbillid;
    }

    public String getStoragequitbillid() 
    {
        return storagequitbillid;
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
    public void setOutpeople(String outpeople) 
    {
        this.outpeople = outpeople;
    }

    public String getOutpeople() 
    {
        return outpeople;
    }
    public void setOutstoragecause(String outstoragecause) 
    {
        this.outstoragecause = outstoragecause;
    }

    public String getOutstoragecause() 
    {
        return outstoragecause;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("storagequitbillid", getStoragequitbillid())
            .append("outsourcewarehouse", getOutsourcewarehouse())
            .append("outsourcewarehouseid", getOutsourcewarehouseid())
            .append("outpeople", getOutpeople())
            .append("outstoragecause", getOutstoragecause())
            .append("comments", getComments())
            .append("cTime", getcTime())
            .append("uTime", getuTime())
            .toString();
    }
}
