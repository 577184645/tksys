package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 库存列表对象 storage
 * 
 * @author ruoyi
 * @date 2020-06-03
 */
@Data
public class Storage extends BaseEntity
{


    private static final long serialVersionUID = 1L;

    /** null */
    private Long id;

    /** 编码 */
    @Excel(name = "编码")
    private String materialcode;



    /** 单价 */
    @Excel(name = "单价")
    private Double price;

    /** 库存类型 */
    private Long deptId;


    /** 物料id */
   private Long  materialId;



    /** 库存量 */
    @Excel(name = "库存量")
    private Integer stocks;


    /** 总价 */
    private Double money;

    /** 库存类别 */
    private Long typeId;


    /** 是否为0 */
    private Integer status;


    /** 最后入库时间 */
    @Excel(name = "最后入库时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date uTime;

    /** 最后入库时间 */
    @Excel(name = "最后出库时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date oTime;

    @Excel(name = "最后退料时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date qTime;

      //所属位置
    private String  ancestors;

    //部门名称
    private String   deptName;






    /**
     * 扩展字段
     * @return
     */
    /** 库存类型信息 */
    private Storagetype  storagetype;
    private String name;
    private String partnumber;
    private String description;
    private String footprint;
    private String manufacture;
    private List<MaterialChild> materialChildList;




}
