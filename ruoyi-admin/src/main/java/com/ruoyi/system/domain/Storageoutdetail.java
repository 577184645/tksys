package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 出库产品列表对象 storageoutdetail
 * 
 * @author ruoyi
 * @date 2020-06-09
 */
public class Storageoutdetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
    private Long id;

    private Long sid;





    /** 所属入库单 */
    @Excel(name = "所属入库单")
    private String storageoutbillid;

    /** 物料代码 */
    @Excel(name = "物料代码")
    private String materialcode;



    /** 序列号 */
    @Excel(name = "序列号")
    private String serialNumber;





    /** 数量 */
    @Excel(name = "数量")
    private Long counts;





    /** 供应商 */
    @Excel(name = "供应商")
    private String supplier;





    /** 备注 */
    @Excel(name = "备注")
    private String comments;


    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }


    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setStorageoutbillid(String storageoutbillid) 
    {
        this.storageoutbillid = storageoutbillid;
    }

    public String getStorageoutbillid() 
    {
        return storageoutbillid;
    }
    public void setMaterialcode(String materialcode) 
    {
        this.materialcode = materialcode;
    }

    public String getMaterialcode() 
    {
        return materialcode;
    }




    public Long getCounts() {
        return counts;
    }

    public void setCounts(Long counts) {
        this.counts = counts;
    }

    public void setSupplier(String supplier) 
    {
        this.supplier = supplier;
    }

    public String getSupplier() 
    {
        return supplier;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("storageoutbillid", getStorageoutbillid())
            .append("materialcode", getMaterialcode())
            .append("counts", getCounts())
            .append("supplier", getSupplier())
            .toString();
    }
}
