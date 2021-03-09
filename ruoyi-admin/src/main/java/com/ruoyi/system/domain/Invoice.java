package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 发票对象 invoice
 * 
 * @author ruoyi
 * @date 2020-09-21
 */
@Data
public class Invoice extends BaseEntity
{
    private static final long serialVersionUID = 1L;

   private Long invoiceId;


    private Long contractId;

    /** 发票编号 */
    @Excel(name = "发票编号")
    private String invoiceNumber;

    /** 开票日期 */
    @Excel(name = "开票日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date invoiceDate;

    /** 发票金额 */
    @Excel(name = "发票金额")
    private Double invoiceMoney;


    private Date cTime;

    private Date uTime;


}
