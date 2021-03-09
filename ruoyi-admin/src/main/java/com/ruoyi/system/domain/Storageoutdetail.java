package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 出库产品列表对象 storageoutdetail
 * 
 * @author ruoyi
 * @date 2020-06-09
 */
@Data
@NoArgsConstructor
public class Storageoutdetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
    private Long id;

    private Long sid;

    /** 所属入库单 */
    @Excel(name = "所属入库单")
    private Long storageoutbillId;

    /** 物料代码 */
    @Excel(name = "物料代码")
    private String materialcode;

    /** 序列号 */
    @Excel(name = "序列号")
    private String serialNumber;


    /** 数量 */
    @Excel(name = "数量")
    private Integer counts;


    /** 备注 */
    @Excel(name = "备注")
    private String comments;

    private Long materialId;

    /**
     * 扩展字段
     */
    private Double price;
    private Double money;

    private List<MaterialChild> materialChildList;

    public Storageoutdetail(Long sid, Long storageoutbillId, String materialcode, String serialNumber, Integer counts, String comments,Long materialId) {
        this.sid = sid;
        this.storageoutbillId = storageoutbillId;
        this.materialcode = materialcode;
        this.serialNumber = serialNumber;
        this.counts = counts;
        this.comments = comments;
        this.materialId=materialId;
    }
}
