package com.ruoyi.system.mapper;

import java.util.List;

import com.ruoyi.system.domain.Materialtype;
import com.ruoyi.system.domain.Storagetype;

/**
 * 库存类别列表Mapper接口
 * 
 * @author ruoyi
 * @date 2020-06-03
 */
public interface StoragetypeMapper 
{

    /**
     * 查询部门人数
     *
     * @param dept 部门信息
     * @return 结果
     */
    public int selectMaterialtypeCount(Storagetype dept);




    /**
     * 查询库存类别列表
     * 
     * @param deptId 库存类别列表ID
     * @return 库存类别列表
     */
    public Storagetype selectStoragetypeById(Long deptId);

    /**
     * 查询库存类别列表列表
     * 
     * @param storagetype 库存类别列表
     * @return 库存类别列表集合
     */
    public List<Storagetype> selectStoragetypeList(Storagetype storagetype);

    /**
     * 新增库存类别列表
     * 
     * @param storagetype 库存类别列表
     * @return 结果
     */
    public int insertStoragetype(Storagetype storagetype);

    /**
     * 修改库存类别列表
     * 
     * @param storagetype 库存类别列表
     * @return 结果
     */
    public int updateStoragetype(Storagetype storagetype);

    /**
     * 删除库存类别列表
     * 
     * @param deptId 库存类别列表ID
     * @return 结果
     */
    public int deleteStoragetypeById(Long deptId);

    /**
     * 批量删除库存类别列表
     * 
     * @param deptIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteStoragetypeByIds(String[] deptIds);


    /**
     * 查询部门是否存在用户
     *
     * @param deptId 部门ID
     * @return 结果
     */
    public int checkDeptExistUser(Long deptId);
}
