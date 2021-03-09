package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.TreeEntity;
import lombok.Data;

/**
 * 物料类型列表对象 materialtype
 * 
 * @author ruoyi
 * @date 2020-06-01
 */
@Data
public class Materialtype extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** 部门id */
    private Long deptId;

    /** 部门名称 */
    @Excel(name = "部门名称")
    private String deptName;



    private String  code;

    /** 负责人 */
    private String leader;

    /** 联系电话 */
    private String phone;

    /** 邮箱 */
    private String email;

    /** 部门状态（0正常 1停用） */
    @Excel(name = "部门状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;


}
