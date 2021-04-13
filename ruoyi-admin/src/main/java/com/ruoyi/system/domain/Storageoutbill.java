package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * 出库单列表对象 storageoutbill
 * 
 * @author ruoyi
 * @date 2020-06-09
 */
@Data
@NoArgsConstructor
public class Storageoutbill extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
    private Long id;

    /** 出库单号 */
    @Excel(name = "出库单号")
    private String storageoutid;


    /** 外协库 */
    @Excel(name = "外协库")
    private String outsourcewarehouse;

    /** 外协库id */
    private Long outsourcewarehouseid;

    /** 领料单 */
    @Excel(name = "领料单")
    private String stockrequisition;

    /** 库管员 */
    @Excel(name = "库管员")
    private String warehouseadmin;

    /** 领料人 */
    @Excel(name = "领料人")
    private String stockpeople;




    @Excel(name = "采购单号")
    private String purchaseid;
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
    private String customer;

    /** 快递单号 */
    @Excel(name = "快递单号")
    private String expressid;

    /** 进库原因 */
    @Excel(name = "出库原因")
    private String instoragecause;

    /** 所属项目 */
    @Excel(name = "所属项目")
    private String projectname;

    /** 申请人 */
    @Excel(name = "申请人")
    private String proposer;

    @Excel(name = "出库金额")
    private Double outmoney;



    /** 备注 */
    @Excel(name = "备注")
    private String comments;

    /** 创建时间 */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date cTime;

    /** 修改时间 */
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date uTime;


    /** 删除状态 */
    private Long delStatus;

    private Integer   storageouttype;


    private List<Storageoutdetail> storageoutdetailList;


    public Storageoutbill(Long id, Double outmoney) {
        this.id = id;
        this.outmoney = outmoney;
    }
}
