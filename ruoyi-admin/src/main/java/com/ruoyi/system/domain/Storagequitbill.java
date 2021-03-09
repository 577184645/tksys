package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * 退料单列表对象 storagequitbill
 * 
 * @author ruoyi
 * @date 2020-06-18
 */
@Data
@NoArgsConstructor
public class Storagequitbill extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;


    /** 退料单号 */
    @Excel(name = "退料单号")
    private String storagequitbillid;

    /** 外协库 */
    @Excel(name = "外协库")
    private String outsourcewarehouse;

    /** 外协库id */
    @Excel(name = "外协库id")
    private Long outsourcewarehouseid;

    /** 退库人 */
    @Excel(name = "退库人")
    private String outpeople;

    /** 退库原因 */
    @Excel(name = "退库原因")
    private String outstoragecause;

    /** 备注 */
    @Excel(name = "备注")
    private String comments;

    /** 退库时间 */
    @Excel(name = "退库时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date cTime;

    /** 修改时间 */
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date uTime;

    private Integer delStatus;


    @Excel(name = "退料金额")
    private Double quitmoney;

    /**
     * 扩展字段
     */
    private List<Storagequitdetail> storagequitdetailList;


    public Storagequitbill(Long id, Double quitmoney) {
        this.id = id;
        this.quitmoney = quitmoney;
    }
}
