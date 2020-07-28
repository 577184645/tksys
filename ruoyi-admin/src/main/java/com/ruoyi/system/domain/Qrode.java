package com.ruoyi.system.domain;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 二维码列表对象 qrode
 * 
 * @author ruoyi
 * @date 2020-07-22
 */
public class Qrode extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
    private Long id;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String name;

    /** 产品型号 */
    @Excel(name = "产品型号")
    private String partnumber;

    /** 序列号 */
    @Excel(name = "序列号")
    private String serialnumber;

    /** ip地址 */
    @Excel(name = "客户")
    private String customer;

    /** 官网 */
    @Excel(name = "官网")
    private String website;

    /** 手册地址 */
    @Excel(name = "手册地址")
    private String enchiridionaddress;

    /** MAC地址 */
    @Excel(name = "MAC地址")
    private String macaddress;


    /** 生产日期 */
    @Excel(name = "生产日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date productiontime;

    /** 出厂日期 */
    @Excel(name = "出厂日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date leavefactorytime;

    /** 保修日期 */
    @Excel(name = "保修日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date warrantytime;





    private Date cTime;



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
    public void setPartnumber(String partnumber) 
    {
        this.partnumber = partnumber;
    }

    public String getPartnumber() 
    {
        return partnumber;
    }
    public void setSerialnumber(String serialnumber) 
    {
        this.serialnumber = serialnumber;
    }

    public String getSerialnumber() 
    {
        return serialnumber;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public void setWebsite(String website)
    {
        this.website = website;
    }

    public String getWebsite() 
    {
        return website;
    }
    public void setEnchiridionaddress(String enchiridionaddress) 
    {
        this.enchiridionaddress = enchiridionaddress;
    }

    public String getEnchiridionaddress() 
    {
        return enchiridionaddress;
    }
    public void setProductiontime(Date productiontime) 
    {
        this.productiontime = productiontime;
    }

    public Date getProductiontime() 
    {
        return productiontime;
    }
    public void setLeavefactorytime(Date leavefactorytime) 
    {
        this.leavefactorytime = leavefactorytime;
    }

    public Date getLeavefactorytime() 
    {
        return leavefactorytime;
    }
    public void setWarrantytime(Date warrantytime) 
    {
        this.warrantytime = warrantytime;
    }

    public Date getWarrantytime() 
    {
        return warrantytime;
    }
    public void setMacaddress(String macaddress) 
    {
        this.macaddress = macaddress;
    }

    public String getMacaddress() 
    {
        return macaddress;
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
            .append("partnumber", getPartnumber())
            .append("serialnumber", getSerialnumber())
            .append("customer", getCustomer())
            .append("website", getWebsite())
            .append("enchiridionaddress", getEnchiridionaddress())
            .append("productiontime", getProductiontime())
            .append("leavefactorytime", getLeavefactorytime())
            .append("warrantytime", getWarrantytime())
            .append("macaddress", getMacaddress())
            .append("cTime", getcTime())
            .append("uTime", getuTime())
            .toString();
    }
}
