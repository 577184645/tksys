package com.ruoyi.system.domain;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 销售合同对象 sales_contract
 * 
 * @author ruoyi
 * @date 2020-09-18
 */
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

    /** 合同未执行完毕应付款 */
    @Excel(name = "合同未执行完毕应付款")
    private Double contractStartmoney;

    /** 合同已执行完毕应付款 */
    @Excel(name = "合同已执行完毕应付款")
    private Double contractEndmoney;

    /** 创建时间 */
    private Date cTime;

    /** 修改时间 */
    private Date uTime;

    public void setContractId(Long contractId) 
    {
        this.contractId = contractId;
    }

    public Long getContractId() 
    {
        return contractId;
    }
    public void setContractNumber(String contractNumber) 
    {
        this.contractNumber = contractNumber;
    }

    public String getContractNumber() 
    {
        return contractNumber;
    }
    public void setContractCustomer(String contractCustomer) 
    {
        this.contractCustomer = contractCustomer;
    }

    public String getContractCustomer() 
    {
        return contractCustomer;
    }
    public void setContractMoney(Double contractMoney) 
    {
        this.contractMoney = contractMoney;
    }

    public Double getContractMoney() 
    {
        return contractMoney;
    }
    public void setContractInvoicemoney(Double contractInvoicemoney) 
    {
        this.contractInvoicemoney = contractInvoicemoney;
    }

    public Double getContractInvoicemoney() 
    {
        return contractInvoicemoney;
    }
    public void setContractPayment(String contractPayment) 
    {
        this.contractPayment = contractPayment;
    }

    public String getContractPayment() 
    {
        return contractPayment;
    }
    public void setContractDeliverstatus(String contractDeliverstatus) 
    {
        this.contractDeliverstatus = contractDeliverstatus;
    }

    public String getContractDeliverstatus() 
    {
        return contractDeliverstatus;
    }
    public void setContractReturnedmoney(Double contractReturnedmoney) 
    {
        this.contractReturnedmoney = contractReturnedmoney;
    }

    public Double getContractReturnedmoney() 
    {
        return contractReturnedmoney;
    }
    public void setContractStartmoney(Double contractStartmoney) 
    {
        this.contractStartmoney = contractStartmoney;
    }

    public Double getContractStartmoney() 
    {
        return contractStartmoney;
    }
    public void setContractEndmoney(Double contractEndmoney) 
    {
        this.contractEndmoney = contractEndmoney;
    }

    public Double getContractEndmoney() 
    {
        return contractEndmoney;
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
            .append("contractId", getContractId())
            .append("contractNumber", getContractNumber())
            .append("contractCustomer", getContractCustomer())
            .append("contractMoney", getContractMoney())
            .append("contractInvoicemoney", getContractInvoicemoney())
            .append("contractPayment", getContractPayment())
            .append("contractDeliverstatus", getContractDeliverstatus())
            .append("contractReturnedmoney", getContractReturnedmoney())
            .append("contractStartmoney", getContractStartmoney())
            .append("contractEndmoney", getContractEndmoney())
            .append("cTime", getcTime())
            .append("uTime", getuTime())
            .toString();
    }
}
