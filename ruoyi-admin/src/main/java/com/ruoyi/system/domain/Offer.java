package com.ruoyi.system.domain;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 报价单对象 offer
 * 
 * @author ruoyi
 * @date 2020-09-07
 */
public class Offer extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
    private Long offerId;

    /** 报价单号 */
    @Excel(name = "报价单号")
    private String offerNumber;


    private String   filename;

    /** 报价人 */
    @Excel(name = "报价人")
    private String offerUsername;

    /** 业务员 */
    @Excel(name = "业务员")
    private String offerSalesman;

    /** 业务员联系方式 */
    @Excel(name = "业务员联系方式")
    private String offerSalesmancontactway;

    /** 报价金额 */
    @Excel(name = "报价金额")
    private Double offerMoney;

    /** 报价项目 */
    @Excel(name = "报价项目")
    private String offerProject;

    /** 报价时间 */
    @Excel(name = "报价时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date offerTime;

    /** 内容 */
    @Excel(name = "内容")
    private String context;

    /** 附件 */
    @Excel(name = "附件")
    private String accessory;

    /** 创建时间 */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date cTime;

    /** 修改时间 */
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date uTime;


    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setOfferId(Long offerId)
    {
        this.offerId = offerId;
    }

    public Long getOfferId() 
    {
        return offerId;
    }
    public void setOfferNumber(String offerNumber) 
    {
        this.offerNumber = offerNumber;
    }

    public String getOfferNumber() 
    {
        return offerNumber;
    }
    public void setOfferUsername(String offerUsername) 
    {
        this.offerUsername = offerUsername;
    }

    public String getOfferUsername() 
    {
        return offerUsername;
    }
    public void setOfferSalesman(String offerSalesman) 
    {
        this.offerSalesman = offerSalesman;
    }

    public String getOfferSalesman() 
    {
        return offerSalesman;
    }
    public void setOfferSalesmancontactway(String offerSalesmancontactway) 
    {
        this.offerSalesmancontactway = offerSalesmancontactway;
    }

    public String getOfferSalesmancontactway() 
    {
        return offerSalesmancontactway;
    }
    public void setOfferMoney(Double offerMoney) 
    {
        this.offerMoney = offerMoney;
    }

    public Double getOfferMoney() 
    {
        return offerMoney;
    }
    public void setOfferProject(String offerProject) 
    {
        this.offerProject = offerProject;
    }

    public String getOfferProject() 
    {
        return offerProject;
    }
    public void setOfferTime(Date offerTime) 
    {
        this.offerTime = offerTime;
    }

    public Date getOfferTime() 
    {
        return offerTime;
    }
    public void setContext(String context) 
    {
        this.context = context;
    }

    public String getContext() 
    {
        return context;
    }
    public void setAccessory(String accessory) 
    {
        this.accessory = accessory;
    }

    public String getAccessory() 
    {
        return accessory;
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
            .append("offerId", getOfferId())
            .append("offerNumber", getOfferNumber())
            .append("offerUsername", getOfferUsername())
            .append("offerSalesman", getOfferSalesman())
            .append("offerSalesmancontactway", getOfferSalesmancontactway())
            .append("offerMoney", getOfferMoney())
            .append("offerProject", getOfferProject())
            .append("offerTime", getOfferTime())
            .append("context", getContext())
            .append("accessory", getAccessory())
            .append("cTime", getcTime())
            .append("uTime", getuTime())
            .toString();
    }
}
