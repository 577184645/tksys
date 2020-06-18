package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Materialtype;
import com.ruoyi.system.domain.SysDept;

/**
 * 物料类型列表Mapper接口
 * 
 * @author ruoyi
 * @date 2020-06-01
 */
public interface MaterialtypeMapper 
{
    /**
     * 查询物料类型列表
     * 
     * @param deptId 物料类型列表ID
     * @return 物料类型列表
     */
    public Materialtype selectMaterialtypeById(Long deptId);
    /**
     * 查询物料类型列表
     *
     * @param code 物料类型列表code
     * @return 物料类型列表
     */
    public Materialtype selectMaterialtypeByCode(String code);



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
     * 删除物料类型列表
     * 
     * @param deptId 物料类型列表ID
     * @return 结果
     */
    public int deleteMaterialtypeById(Long deptId);

    /**
     * 批量删除物料类型列表
     * 
     * @param deptIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteMaterialtypeByIds(String[] deptIds);



    /**
     * 查询部门人数
     *
     * @param dept 部门信息
     * @return 结果
     */
    public int selectMaterialtypeCount(Materialtype dept);


    /**
     * 查询部门是否存在用户
     *
     * @param deptId 部门ID
     * @return 结果
     */
    public int checkDeptExistUser(Long deptId);
}
