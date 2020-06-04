package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Materialtype;
import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.system.domain.SysDept;

/**
 * 物料类型列表Service接口
 * 
 * @author ruoyi
 * @date 2020-06-01
 */
public interface IMaterialtypeService 
{
    /**
     * 查询物料类型列表
     * 
     * @param deptId 物料类型列表ID
     * @return 物料类型列表
     */
    public Materialtype selectMaterialtypeById(Long deptId);

    /**
     * 查询物料类型列表列表
     * 
     * @param materialtype 物料类型列表
     * @return 物料类型列表集合
     */
    public List<Materialtype> selectMaterialtypeList(Materialtype materialtype);

    /**
     * 新增物料类型列表
     * 
     * @param materialtype 物料类型列表
     * @return 结果
     */
    public int insertMaterialtype(Materialtype materialtype);

    /**
     * 修改物料类型列表
     * 
     * @param materialtype 物料类型列表
     * @return 结果
     */
    public int updateMaterialtype(Materialtype materialtype);

    /**
     * 批量删除物料类型列表
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMaterialtypeByIds(String ids);

    /**
     * 删除物料类型列表信息
     * 
     * @param deptId 物料类型列表ID
     * @return 结果
     */
    public int deleteMaterialtypeById(Long deptId);

    /**
     * 查询物料类型列表树列表
     * 
     * @return 所有物料类型列表信息
     */
    public List<Ztree> selectMaterialtypeTree();



    /**
     * 查询部门人数
     *
     * @param parentId 部门信息
     * @return 结果
     */
    public int selectMaterialtypeCount(Long parentId);


    /**
     * 查询部门是否存在用户
     *
     * @param deptId 部门ID
     * @return 结果 true 存在 false 不存在
     */
    public boolean checkDeptExistUser(Long deptId);

}
