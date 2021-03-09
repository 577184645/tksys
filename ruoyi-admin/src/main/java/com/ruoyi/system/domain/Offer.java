package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 报价单对象 offer
 * 
 * @author ruoyi
 * @date 2020-09-07
 */
@Data
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

    private String isGroup;

    private String  approvestatus;

    private String status;


}
