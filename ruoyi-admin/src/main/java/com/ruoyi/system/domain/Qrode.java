package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 二维码列表对象 qrode
 * 
 * @author ruoyi
 * @date 2020-07-22
 */
@Data
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


}
