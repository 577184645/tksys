package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 回款对象 returned
 * 
 * @author ruoyi
 * @date 2020-09-18
 */
@Data
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


}
