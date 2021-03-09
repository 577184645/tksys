package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.TreeEntity;
import lombok.Data;

/**
 * 库存类别列表对象 storagetype
 * 
 * @author ruoyi
 * @date 2020-06-03
 */

@Data
public class Storagetype extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** 仓库id */
    private Long deptId;

    /** 仓库名称 */
    @Excel(name = "仓库名称")
    private String deptName;

    /** 负责人 */
    private String leader;

    /** 联系电话 */
    private String phone;

    /** 邮箱 */
    private String email;

    /** 部门状态（0正常 1停用） */
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;


}
