package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 物料列表对象 material
 *
 * @author ruoyi
 * @date 2020-06-01
 */
@Data
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



}
