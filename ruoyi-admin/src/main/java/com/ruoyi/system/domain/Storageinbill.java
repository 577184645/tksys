package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * 入库单列表对象 storageinbill
 * 
 * @author ruoyi
 * @date 2020-06-04
 */
@Data
@NoArgsConstructor
public class Storageinbill extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
    private Long id;


    @Excel(name = "入库金额")
    private Double money;

    /** 入库单号 */
    @Excel(name = "入库单号")
    private String stockinid;

    /** 外协库 */
    @Excel(name = "外协库")
    private String outsourcewarehouse;


    @Excel(name = "报检单号")
    private String  checknumber;

    /** 采购单号 */
    @Excel(name = "采购单号")
    private String purchaseid;

    /** 送货人 */
    @Excel(name = "送货人")
    private String deliveryman;

    /** 库管员 */
    @Excel(name = "库管员")
    private String warehouseadmin;




    /** 申请单号 */
    @Excel(name = "申请单号")
    private String applyid;

    /** 合同单号 */
    @Excel(name = "合同单号")
    private String contractid;

    /** 发票单号 */
    @Excel(name = "发票单号")
    private String invoiceid;

    @Excel(name = "供应商")
    private String supplier;

    /** 快递单号 */
    @Excel(name = "快递单号")
    private String expressid;

    /** 进库原因 */
    @Excel(name = "入库原因")
    private String instoragecause;

    /** 所属项目 */
    @Excel(name = "所属项目")
    private String projectname;

    /** 申请人 */
    @Excel(name = "申请人")
    private String proposer;

    /** 备注 */
    @Excel(name = "备注")
    private String comments;

   private Integer storageStatus;

    private Integer delStatus;

    /** 删除状态 */
    private Long status;

    /** 创建时间 */
    private Date cTime;


    /** 修改时间 */
    private Date uTime;

    private Long outsourcewarehouseid;

    private Integer storageintype;




    private List<Storageindetail> storageindetailList;


    public Storageinbill(Long id, Double money) {
        this.id = id;
        this.money = money;
    }
}
