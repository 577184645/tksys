package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 销售合同对象 sales_contract
 * 
 * @author ruoyi
 * @date 2020-09-18
 */
@Data
public class SalesContract extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
    private Long contractId;

    /** 销售合同号 */
    @Excel(name = "销售合同号")
    private String contractNumber;

    /** 单位 */
    @Excel(name = "单位")
    private String contractCustomer;


    /** 金额 */
    @Excel(name = "金额")
    private Double contractMoney;

    /** 是否开票 */
    @Excel(name = "是否开票")
    private Double contractInvoicemoney;

    /** 付款方式 */
    @Excel(name = "付款方式")
    private String contractPayment;

    /** 发货状态 */
    @Excel(name = "发货状态")
    private String contractDeliverstatus;

    /** 回款 */
    @Excel(name = "回款")
    private Double contractReturnedmoney;

    @Excel(name = "集团合同号")
    private String  groupNumber;

    @Excel(name = "项目名称")
    private String contractProjectname;


    /** 创建时间 */
    private Date cTime;

    /** 修改时间 */
    private Date uTime;


    /**
     *   数据库扩展字段
     * @return
     */
    /** 年份 */
     private String year;
    /** 是否完成合同 */
     private String contractStatus;
    /** 是否开票 */
    private String isInvoice;
    /** 是否回款 */
    private String isReturned;



}
