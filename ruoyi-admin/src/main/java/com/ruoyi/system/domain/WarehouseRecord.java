package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 库存记录对象 warehouse_record
 * 
 * @author ruoyi
 * @date 2020-06-24
 */
@Data
@NoArgsConstructor
public class WarehouseRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 类型 */
    @Excel(name = "类型")
    private Integer type;

    /** 单号 */
    @Excel(name = "单号")
    private String number;

    /** 物料编号 */
    @Excel(name = "物料编号")
    private String materialcode;



    /** 数量 */
    @Excel(name = "数量")
    private Integer count;

    /** 单价 */
    @Excel(name = "单价")
    private Double price;

    /** 总价 */
    @Excel(name = "总价")
    private Double money;

    /** 供应商 */
    @Excel(name = "供应商")
    private String supplier;

    /** 序列号 */
    @Excel(name = "序列号")
    private String serialNumber;

    /** 状态 */
    @Excel(name = "状态")
    private String delStatus;

    @Excel(name = "备注")
    private String remark;


    /** 时间 */
    @Excel(name = "时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date cTime;

    public WarehouseRecord( Integer type, String number, String materialcode, Integer count, Double price, Double money, String supplier, String serialNumber, String remark) {
        this.type = type;
        this.number = number;
        this.materialcode = materialcode;
        this.count = count;
        this.price = price;
        this.money = money;
        this.supplier = supplier;
        this.serialNumber = serialNumber;
        this.remark = remark;
    }
}
