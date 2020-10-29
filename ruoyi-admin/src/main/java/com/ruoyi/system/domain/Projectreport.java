package com.ruoyi.system.domain;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 项目报备对象 projectreport
 * 
 * @author ruoyi
 * @date 2020-10-29
 */
public class Projectreport extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
    private Long projectreportId;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String projectreportName;

    /** 报备日期 */
    @Excel(name = "报备日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date projectreportDate;

    /** 最终用户 */
    @Excel(name = "最终用户")
    private String lastuser;

    /** 招标人 */
    @Excel(name = "招标人")
    private String tenderee;

    /** 报备人 */
    @Excel(name = "报备人")
    private String projectreportUser;

    /** 电话 */
    @Excel(name = "电话")
    private String phone;

    /** 项目情况说明 */
    @Excel(name = "项目情况说明")
    private String projectreportInfo;

    /** 创建时间 */
    private Date cTime;

    /** 修改时间 */
    private Date uTime;

    public void setProjectreportId(Long projectreportId) 
    {
        this.projectreportId = projectreportId;
    }

    public Long getProjectreportId() 
    {
        return projectreportId;
    }
    public void setProjectreportName(String projectreportName) 
    {
        this.projectreportName = projectreportName;
    }

    public String getProjectreportName() 
    {
        return projectreportName;
    }
    public void setProjectreportDate(Date projectreportDate) 
    {
        this.projectreportDate = projectreportDate;
    }

    public Date getProjectreportDate() 
    {
        return projectreportDate;
    }
    public void setLastuser(String lastuser) 
    {
        this.lastuser = lastuser;
    }

    public String getLastuser() 
    {
        return lastuser;
    }
    public void setTenderee(String tenderee) 
    {
        this.tenderee = tenderee;
    }

    public String getTenderee() 
    {
        return tenderee;
    }
    public void setProjectreportUser(String projectreportUser) 
    {
        this.projectreportUser = projectreportUser;
    }

    public String getProjectreportUser() 
    {
        return projectreportUser;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setProjectreportInfo(String projectreportInfo) 
    {
        this.projectreportInfo = projectreportInfo;
    }

    public String getProjectreportInfo() 
    {
        return projectreportInfo;
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
            .append("projectreportId", getProjectreportId())
            .append("projectreportName", getProjectreportName())
            .append("projectreportDate", getProjectreportDate())
            .append("lastuser", getLastuser())
            .append("tenderee", getTenderee())
            .append("projectreportUser", getProjectreportUser())
            .append("phone", getPhone())
            .append("projectreportInfo", getProjectreportInfo())
            .append("cTime", getcTime())
            .append("uTime", getuTime())
            .toString();
    }
}
