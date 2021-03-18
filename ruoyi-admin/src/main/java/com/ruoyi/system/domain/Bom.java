package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * bom列表对象 bom
 * 
 * @author ruoyi
 * @date 2020-07-24
 */
@Data
public class Bom extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
    private Long id;

    /** 编号 */
    @Excel(name = "编号")
    private String number;

    /** 版本 */
    @Excel(name = "版本")
    private String version;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 型号 */
    @Excel(name = "型号")
    private String partnumber;

    /** 描述 */
    @Excel(name = "描述")
    private String description;

    /** 发布状态 */
    @Excel(name = "发布状态")
    private String publishstatus;

    /** 所属项目 */
    @Excel(name = "所属项目")
    private String project;

    /** 产品大类 */
    @Excel(name = "产品大类")
    private String producttype;

    /** 价格 */
    @Excel(name = "价格")
    private Double price;

    /** 设计者 */
    @Excel(name = "设计者")
    private String designer;

    /** 检查者 */
    @Excel(name = "检查者")
    private String scrutineer;

    /** 批准者 */
    @Excel(name = "批准者")
    private String approver;

    /** 制作时间 */
    @Excel(name = "制作时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date timeofmaking;

    /** 检查时间 */
    @Excel(name = "检查时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date inspectiontime;

    /** 批准时间 */
    @Excel(name = "批准时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date approvaltime;

    @Excel(name = "状态")
    private Integer delStatus;

    /** 创建时间 */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date cTime;

    /** 修改时间 */
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date uTime;


}
