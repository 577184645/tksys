package com.ruoyi.system.domain;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 供应商列表对象 supplier
 * 
 * @author ruoyi
 * @date 2020-06-01
 */
public class Supplier extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
    private Long id;

    /** 供应商名称 */
    @Excel(name = "供应商名称")
    private String name;

    /** 厂商类型 */
    @Excel(name = "厂商类型")
    private String suppiertype;

    /** 地址 */
    @Excel(name = "地址")
    private String address;

    /** 联系方式 */
    @Excel(name = "联系方式")
    private String tel;

    /** 公司主页 */
    @Excel(name = "公司主页")
    private String page;

    /** 开户行 */
    @Excel(name = "开户行")
    private String openbank;

    /** 银行账户 */
    @Excel(name = "银行账户")
    private String bankaccounts;

    /** 经营类型 */
    @Excel(name = "经营类型")
    private Long enterprisetype;

    /** 备注 */
    @Excel(name = "备注")
    private String comments;

    /** 创建时间 */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date cTime;

    /** 最后修改时间 */
    @Excel(name = "最后修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date uTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setSuppiertype(String suppiertype) 
    {
        this.suppiertype = suppiertype;
    }

    public String getSuppiertype() 
    {
        return suppiertype;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setTel(String tel) 
    {
        this.tel = tel;
    }

    public String getTel() 
    {
        return tel;
    }
    public void setPage(String page) 
    {
        this.page = page;
    }

    public String getPage() 
    {
        return page;
    }
    public void setOpenbank(String openbank) 
    {
        this.openbank = openbank;
    }

    public String getOpenbank() 
    {
        return openbank;
    }
    public void setBankaccounts(String bankaccounts) 
    {
        this.bankaccounts = bankaccounts;
    }

    public String getBankaccounts() 
    {
        return bankaccounts;
    }
    public void setEnterprisetype(Long enterprisetype) 
    {
        this.enterprisetype = enterprisetype;
    }

    public Long getEnterprisetype() 
    {
        return enterprisetype;
    }
    public void setComments(String comments) 
    {
        this.comments = comments;
    }

    public String getComments() 
    {
        return comments;
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
            .append("id", getId())
            .append("name", getName())
            .append("suppiertype", getSuppiertype())
            .append("address", getAddress())
            .append("tel", getTel())
            .append("page", getPage())
            .append("openbank", getOpenbank())
            .append("bankaccounts", getBankaccounts())
            .append("enterprisetype", getEnterprisetype())
            .append("comments", getComments())
            .append("cTime", getcTime())
            .append("uTime", getuTime())
            .toString();
    }
}
