package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 项目报备对象 projectreport
 * 
 * @author ruoyi
 * @date 2020-11-05
 */
public class Projectreport extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
    private Long projectreportId;

    /** 销售识别码 */
    @Excel(name = "销售识别码")
    private String projectreportHeadingcode;

    /** 报备时间 */
    @Excel(name = "报备时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date projectreportDate;

    /** 最终用户 */
    @Excel(name = "最终用户")
    private String projectreportLastuser;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String projectreportName;

    /** 设计院 */
    @Excel(name = "设计院")
    private String projectreportDesigninginstitute;

    /** 资金来源 */
    @Excel(name = "资金来源")
    private String projectreportCapitalsource;

    /** 投资方信息 */
    @Excel(name = "投资方信息")
    private String projectreportInvestorinfo;

    /** 是否是EPC项目 */
    @Excel(name = "是否是EPC项目")
    private String projectreportIsepcproject;

    /** 项目地点 */
    @Excel(name = "项目地点")
    private String projectreportProjectaddress;

    /** 采购方 */
    @Excel(name = "采购方")
    private String projectreportProjectpurchaser;

    /** 投标（年月日） */
    @Excel(name = "投标", readConverterExp = "年=月日")
    private Date projectreportBiddate;

    /** 涉及到的产品（DCS/SIS/FI） */
    @Excel(name = "涉及到的产品", readConverterExp = "D=CS/SIS/FI")
    private String projectreportProduct;

    /** 大致金额 */
    @Excel(name = "大致金额")
    private String projectreportDapproximatelymoney;

    /** 客户联系方式及职位 */
    @Excel(name = "客户联系方式及职位")
    private String projectreportCphoneandposition;

    /** 设计院联系方式及职位 */
    @Excel(name = "设计院联系方式及职位")
    private String projectreportSphoneandposition;

    /** 竞争对手情况 */
    @Excel(name = "竞争对手情况")
    private String projectreportCompetitorinfo;

    /** 招标方（公开/议标） */
    @Excel(name = "招标方", readConverterExp = "公=开/议标")
    private String projectreportTenderer;

    /** 几轮报价（一轮/多轮） */
    @Excel(name = "几轮报价", readConverterExp = "一=轮/多轮")
    private String projectreportOffersize;

    /** 中标概率（0-100%） */
    @Excel(name = "中标概率", readConverterExp = "0=-100%")
    private String projectreportProbability;

    /** 客户关心的问题 */
    @Excel(name = "客户关心的问题")
    private String projectreportProblem;

    /** 能中标的理由 */
    @Excel(name = "能中标的理由")
    private String projectreportReason;

    /** 拜访记录（时间/内容/客户态度） */
    @Excel(name = "拜访记录", readConverterExp = "时=间/内容/客户态度")
    private String projectreportVisitingclients;

    /** 主要关系：最终用户/EPC */
    @Excel(name = "主要关系：最终用户/EPC")
    private String projectreportRelation;

    /** 项目状态 */
    @Excel(name = "项目状态")
    private String projectreportProjectstatus;

    /** Honeywell ConfirmationHoneywell ConfirmationHoneywell Confirmation */
    @Excel(name = "Honeywell ConfirmationHoneywell")
    private String projectreportHoneywellconfirmation;



    private String  projectreportType;


    /** 创建时间 */
    private Date cTime;

    /** 修改时间 */
    private Date uTime;

    public void setProjectreportId(Long projectreportId) 
    {
        this.projectreportId = projectreportId;
    }


    public String getProjectreportType() {
        return projectreportType;
    }

    public void setProjectreportType(String projectreportType) {
        this.projectreportType = projectreportType;
    }

    public Long getProjectreportId()
    {
        return projectreportId;
    }
    public void setProjectreportHeadingcode(String projectreportHeadingcode) 
    {
        this.projectreportHeadingcode = projectreportHeadingcode;
    }

    public String getProjectreportHeadingcode() 
    {
        return projectreportHeadingcode;
    }
    public void setProjectreportDate(Date projectreportDate) 
    {
        this.projectreportDate = projectreportDate;
    }

    public Date getProjectreportDate() 
    {
        return projectreportDate;
    }
    public void setProjectreportLastuser(String projectreportLastuser) 
    {
        this.projectreportLastuser = projectreportLastuser;
    }

    public String getProjectreportLastuser() 
    {
        return projectreportLastuser;
    }
    public void setProjectreportName(String projectreportName) 
    {
        this.projectreportName = projectreportName;
    }

    public String getProjectreportName() 
    {
        return projectreportName;
    }
    public void setProjectreportDesigninginstitute(String projectreportDesigninginstitute) 
    {
        this.projectreportDesigninginstitute = projectreportDesigninginstitute;
    }

    public String getProjectreportDesigninginstitute() 
    {
        return projectreportDesigninginstitute;
    }
    public void setProjectreportCapitalsource(String projectreportCapitalsource) 
    {
        this.projectreportCapitalsource = projectreportCapitalsource;
    }

    public String getProjectreportCapitalsource() 
    {
        return projectreportCapitalsource;
    }
    public void setProjectreportInvestorinfo(String projectreportInvestorinfo) 
    {
        this.projectreportInvestorinfo = projectreportInvestorinfo;
    }

    public String getProjectreportInvestorinfo() 
    {
        return projectreportInvestorinfo;
    }
    public void setProjectreportIsepcproject(String projectreportIsepcproject) 
    {
        this.projectreportIsepcproject = projectreportIsepcproject;
    }

    public String getProjectreportIsepcproject() 
    {
        return projectreportIsepcproject;
    }
    public void setProjectreportProjectaddress(String projectreportProjectaddress) 
    {
        this.projectreportProjectaddress = projectreportProjectaddress;
    }

    public String getProjectreportProjectaddress() 
    {
        return projectreportProjectaddress;
    }
    public void setProjectreportProjectpurchaser(String projectreportProjectpurchaser) 
    {
        this.projectreportProjectpurchaser = projectreportProjectpurchaser;
    }

    public String getProjectreportProjectpurchaser() 
    {
        return projectreportProjectpurchaser;
    }
    public void setProjectreportBiddate(Date projectreportBiddate) 
    {
        this.projectreportBiddate = projectreportBiddate;
    }

    public Date getProjectreportBiddate() 
    {
        return projectreportBiddate;
    }
    public void setProjectreportProduct(String projectreportProduct) 
    {
        this.projectreportProduct = projectreportProduct;
    }

    public String getProjectreportProduct() 
    {
        return projectreportProduct;
    }
    public void setProjectreportDapproximatelymoney(String projectreportDapproximatelymoney) 
    {
        this.projectreportDapproximatelymoney = projectreportDapproximatelymoney;
    }

    public String getProjectreportDapproximatelymoney() 
    {
        return projectreportDapproximatelymoney;
    }
    public void setProjectreportCphoneandposition(String projectreportCphoneandposition) 
    {
        this.projectreportCphoneandposition = projectreportCphoneandposition;
    }

    public String getProjectreportCphoneandposition() 
    {
        return projectreportCphoneandposition;
    }
    public void setProjectreportSphoneandposition(String projectreportSphoneandposition) 
    {
        this.projectreportSphoneandposition = projectreportSphoneandposition;
    }

    public String getProjectreportSphoneandposition() 
    {
        return projectreportSphoneandposition;
    }
    public void setProjectreportCompetitorinfo(String projectreportCompetitorinfo) 
    {
        this.projectreportCompetitorinfo = projectreportCompetitorinfo;
    }

    public String getProjectreportCompetitorinfo() 
    {
        return projectreportCompetitorinfo;
    }
    public void setProjectreportTenderer(String projectreportTenderer) 
    {
        this.projectreportTenderer = projectreportTenderer;
    }

    public String getProjectreportTenderer() 
    {
        return projectreportTenderer;
    }
    public void setProjectreportOffersize(String projectreportOffersize) 
    {
        this.projectreportOffersize = projectreportOffersize;
    }

    public String getProjectreportOffersize() 
    {
        return projectreportOffersize;
    }
    public void setProjectreportProbability(String projectreportProbability) 
    {
        this.projectreportProbability = projectreportProbability;
    }

    public String getProjectreportProbability() 
    {
        return projectreportProbability;
    }
    public void setProjectreportProblem(String projectreportProblem) 
    {
        this.projectreportProblem = projectreportProblem;
    }

    public String getProjectreportProblem() 
    {
        return projectreportProblem;
    }
    public void setProjectreportReason(String projectreportReason) 
    {
        this.projectreportReason = projectreportReason;
    }

    public String getProjectreportReason() 
    {
        return projectreportReason;
    }
    public void setProjectreportVisitingclients(String projectreportVisitingclients) 
    {
        this.projectreportVisitingclients = projectreportVisitingclients;
    }

    public String getProjectreportVisitingclients() 
    {
        return projectreportVisitingclients;
    }
    public void setProjectreportRelation(String projectreportRelation) 
    {
        this.projectreportRelation = projectreportRelation;
    }

    public String getProjectreportRelation() 
    {
        return projectreportRelation;
    }
    public void setProjectreportProjectstatus(String projectreportProjectstatus) 
    {
        this.projectreportProjectstatus = projectreportProjectstatus;
    }

    public String getProjectreportProjectstatus() 
    {
        return projectreportProjectstatus;
    }
    public void setProjectreportHoneywellconfirmation(String projectreportHoneywellconfirmation) 
    {
        this.projectreportHoneywellconfirmation = projectreportHoneywellconfirmation;
    }

    public String getProjectreportHoneywellconfirmation() 
    {
        return projectreportHoneywellconfirmation;
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
            .append("projectreportId", getProjectreportId())
            .append("projectreportHeadingcode", getProjectreportHeadingcode())
            .append("projectreportDate", getProjectreportDate())
            .append("projectreportLastuser", getProjectreportLastuser())
            .append("projectreportName", getProjectreportName())
            .append("projectreportDesigninginstitute", getProjectreportDesigninginstitute())
            .append("projectreportCapitalsource", getProjectreportCapitalsource())
            .append("projectreportInvestorinfo", getProjectreportInvestorinfo())
            .append("projectreportIsepcproject", getProjectreportIsepcproject())
            .append("projectreportProjectaddress", getProjectreportProjectaddress())
            .append("projectreportProjectpurchaser", getProjectreportProjectpurchaser())
            .append("projectreportBiddate", getProjectreportBiddate())
            .append("projectreportProduct", getProjectreportProduct())
            .append("projectreportDapproximatelymoney", getProjectreportDapproximatelymoney())
            .append("projectreportCphoneandposition", getProjectreportCphoneandposition())
            .append("projectreportSphoneandposition", getProjectreportSphoneandposition())
            .append("projectreportCompetitorinfo", getProjectreportCompetitorinfo())
            .append("projectreportTenderer", getProjectreportTenderer())
            .append("projectreportOffersize", getProjectreportOffersize())
            .append("projectreportProbability", getProjectreportProbability())
            .append("projectreportProblem", getProjectreportProblem())
            .append("projectreportReason", getProjectreportReason())
            .append("projectreportVisitingclients", getProjectreportVisitingclients())
            .append("projectreportRelation", getProjectreportRelation())
            .append("projectreportProjectstatus", getProjectreportProjectstatus())
            .append("projectreportHoneywellconfirmation", getProjectreportHoneywellconfirmation())
            .append("cTime", getcTime())
            .append("uTime", getuTime())
            .toString();
    }
}
