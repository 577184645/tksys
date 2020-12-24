package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 物料列表对象 material
 *
 * @author ruoyi
 * @date 2020-06-01
 */
public class Material extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Long id;


    private String materialcode;

    /** 部门 */
    @Excel(type = Excel.Type.IMPORT,name = "部门",readConverterExp = "00=研发部,01=生产部,02=工程部,03=办公室,04=销售部,05=其他")
    private String deptIdExcel;

    /** 类型id */
    @Excel(type = Excel.Type.IMPORT,name = "类型",readConverterExp = "01=元器件,02=半成品,03=成品,04=办公用品,05=设备设施")
    private String typeIdExcel;

    private Long typeId;

    private Long deptId;

    /**
     * 扩展字段
     * @return
     */

    private String name;

    private String partnumber;

    private String description;

    private String footprint;

    private String manufacture;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPartnumber() {
        return partnumber;
    }

    public void setPartnumber(String partnumber) {
        this.partnumber = partnumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFootprint() {
        return footprint;
    }

    public void setFootprint(String footprint) {
        this.footprint = footprint;
    }

    public String getManufacture() {
        return manufacture;
    }

    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeptIdExcel() {
        return deptIdExcel;
    }

    public void setDeptIdExcel(String deptIdExcel) {
        this.deptIdExcel = deptIdExcel;
    }

    public String getTypeIdExcel() {
        return typeIdExcel;
    }

    public void setTypeIdExcel(String typeIdExcel) {
        this.typeIdExcel = typeIdExcel;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getMaterialcode() {
        return materialcode;
    }

    public void setMaterialcode(String materialcode) {
        this.materialcode = materialcode;
    }
}
