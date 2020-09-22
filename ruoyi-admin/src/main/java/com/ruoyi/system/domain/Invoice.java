package com.ruoyi.system.domain;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 发票对象 invoice
 * 
 * @author ruoyi
 * @date 2020-09-21
 */
public class Invoice extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long invoiceId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
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

    /** 创建时间 */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date cTime;

    /** 修改时间 */
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date uTime;

    public void setInvoiceId(Long invoiceId) 
    {
        this.invoiceId = invoiceId;
    }

    public Long getInvoiceId() 
    {
        return invoiceId;
    }
    public void setContractId(Long contractId) 
    {
        this.contractId = contractId;
    }

    public Long getContractId() 
    {
        return contractId;
    }
    public void setInvoiceNumber(String invoiceNumber) 
    {
        this.invoiceNumber = invoiceNumber;
    }

    public String getInvoiceNumber() 
    {
        return invoiceNumber;
    }
    public void setInvoiceDate(Date invoiceDate) 
    {
        this.invoiceDate = invoiceDate;
    }

    public Date getInvoiceDate() 
    {
        return invoiceDate;
    }
    public void setInvoiceMoney(Double invoiceMoney) 
    {
        this.invoiceMoney = invoiceMoney;
    }

    public Double getInvoiceMoney() 
    {
        return invoiceMoney;
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
            .append("invoiceId", getInvoiceId())
            .append("contractId", getContractId())
            .append("invoiceNumber", getInvoiceNumber())
            .append("invoiceDate", getInvoiceDate())
            .append("invoiceMoney", getInvoiceMoney())
            .append("cTime", getcTime())
            .append("uTime", getuTime())
            .toString();
    }
}
