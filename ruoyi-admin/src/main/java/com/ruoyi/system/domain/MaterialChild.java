package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * 物料对象 material_child
 * 
 * @author ruoyi
 * @date 2020-12-14
 */
@Data
public class MaterialChild extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
    private Integer id;

    @Excel(name = "名称")
    private String name;

    /** 型号 */
    @Excel(name = "型号")
    private String partnumber;

    /** 描述 */
    @Excel(name = "描述")
    private String description;

    /** 封装 */
    @Excel(name = "封装")
    private String footprint;

    /** 品牌 */
    @Excel(name = "品牌")
    private String manufacture;

    /** 单位 */
    @Excel(name = "单位")
    private String unit;

    /** 物料信息注册者 */

    private String inputoperator;

    /** 物料信息注册时间 */

    private Date inputdate;

    /** 批准者 */

    private String approvedby;

    /** 批准时间 */

    private Date approveddate;

    /** 批准状态 */

    private String approvedstatus;

    /** 最少包装量 */

    private Integer productmpq;

    /** 最少起订量 */

    private Integer productmoq;

    /** 交期 */

    private Integer leadtime;

    /** 备注 */

    private String comments;

    /** 类型id */

    private Long typeId;

    /** 部门 */

    private Long deptId;

    /** 安全库存 */

    private Long safestock;

    /** 参考购买链接 */

    private String link;

    /** 采购员 */

    private String buyer;

    /** 删除状态 */
    private String delFlag;

    /** null */

    private Long materialId;

    /** 创建时间 */

    private Date cTime;

    /** 修改时间 */

    private Date uTime;


}
