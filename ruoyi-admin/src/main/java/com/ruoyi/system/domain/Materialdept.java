package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 物料部门对象 materialdept
 * 
 * @author ruoyi
 * @date 2020-06-02
 */
@Data
public class Materialdept extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
    private Long id;

    /** 部门名称 */
    @Excel(name = "部门名称")
    private String deptname;

    /** 排序 */
    @Excel(name = "排序")
    private Long orderNum;

    /** 编码 */
    @Excel(name = "编码")
    private String code;

    /** 创建时间 */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date cTime;

    /** 修改时间 */
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date uTime;



}
