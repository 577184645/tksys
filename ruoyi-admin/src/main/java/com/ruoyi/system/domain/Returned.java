package com.ruoyi.system.domain;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 回款对象 returned
 * 
 * @author ruoyi
 * @date 2020-09-18
 */
public class Returned extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long returnedId;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long contractId;

    /** 回款金额 */
    @Excel(name = "回款金额")
    private Double returnedMoney;

    /** 回款日期 */
    @Excel(name = "回款日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date returnedDate;

    /** 创建时间 */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date cTime;

    /** 修改时间 */
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date uTime;

    public void setReturnedId(Long returnedId) 
    {
        this.returnedId = returnedId;
    }

    public Long getReturnedId() 
    {
        return returnedId;
    }
    public void setContractId(Long contractId) 
    {
        this.contractId = contractId;
    }

    public Long getContractId() 
    {
        return contractId;
    }
    public void setReturnedMoney(Double returnedMoney) 
    {
        this.returnedMoney = returnedMoney;
    }

    public Double getReturnedMoney() 
    {
        return returnedMoney;
    }
    public void setReturnedDate(Date returnedDate) 
    {
        this.returnedDate = returnedDate;
    }

    public Date getReturnedDate() 
    {
        return returnedDate;
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
            .append("returnedId", getReturnedId())
            .append("contractId", getContractId())
            .append("returnedMoney", getReturnedMoney())
            .append("returnedDate", getReturnedDate())
            .append("cTime", getcTime())
            .append("uTime", getuTime())
            .toString();
    }
}
