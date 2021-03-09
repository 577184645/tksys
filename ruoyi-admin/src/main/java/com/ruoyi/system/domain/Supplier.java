package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 供应商列表对象 supplier
 * 
 * @author ruoyi
 * @date 2020-06-01
 */
@Data
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


}
