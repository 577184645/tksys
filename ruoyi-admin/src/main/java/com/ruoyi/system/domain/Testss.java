package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 测试对象 testss
 * 
 * @author ruoyi
 * @date 2020-06-01
 */
public class Testss extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
    private Long id;

    /** 继盛达单价 */
    @Excel(name = "继盛达单价")
    private Double a;

    /** 华秋单价 */
    @Excel(name = "华秋单价")
    private Double b;

    /** 开票状态 */
    @Excel(name = "开票状态")
    private String c;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setA(Double a) 
    {
        this.a = a;
    }

    public Double getA() 
    {
        return a;
    }
    public void setB(Double b) 
    {
        this.b = b;
    }

    public Double getB() 
    {
        return b;
    }
    public void setC(String c) 
    {
        this.c = c;
    }

    public String getC() 
    {
        return c;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("a", getA())
            .append("b", getB())
            .append("c", getC())
            .toString();
    }
}
