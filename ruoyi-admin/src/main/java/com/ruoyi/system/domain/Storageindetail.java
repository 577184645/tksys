package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 入库产品列表对象 storageindetail
 * 
 * @author ruoyi
 * @date 2020-06-05
 */
@Data
@NoArgsConstructor
public class Storageindetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
    private Long id;

    private Long sid;

    /** 所属入库单 */
    @Excel(name = "所属出库单")
    private Long storageinbillId;

    @Excel(name = "物料编码")
    private  String materialcode;
    /** 单价 */
    @Excel(name = "单价")
    private Double price;

    /** 数量 */
    @Excel(name = "数量")
    private Integer counts;

    /** 总金额 */
    @Excel(name = "总金额")
    private Double money;



    /** 供应商 */
    @Excel(name = "供应商")
    private String supplier;

    /** 税率 */
    @Excel(name = "税率")
    private String rate;


    /** 备注 */
    @Excel(name = "备注")
    private String comments;



    @Excel(name = "序列号")
    private  String serialNumber;

    /** 税额 */
    @Excel(name = "税额")
    private Double taxamount;


    private Long materialId;


   private List<MaterialChild> materialChildList;


    public Storageindetail(Long materialId, String materialcode,Integer counts, Double price,  Double money,String rate,  Double taxamount, String comments, String serialNumber,String supplier,Long storageinbillId) {
        this.materialId = materialId;
        this.materialcode = materialcode;
        this.price = price;
        this.counts = counts;
        this.money = money;
        this.rate = rate;
        this.taxamount=taxamount;
        this.comments = comments;
        this.serialNumber = serialNumber;
        this.supplier=supplier;
        this.storageinbillId=storageinbillId;
    }

}
