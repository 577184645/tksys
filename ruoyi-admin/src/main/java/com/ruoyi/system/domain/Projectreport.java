package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 项目报备对象 projectreport
 * 
 * @author ruoyi
 * @date 2020-11-05
 */
@Data
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


}
