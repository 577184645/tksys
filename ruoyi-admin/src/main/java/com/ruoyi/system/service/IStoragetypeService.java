package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Storagetype;
import com.ruoyi.common.core.domain.Ztree;

/**
 * 库存类别列表Service接口
 * 
 * @author ruoyi
 * @date 2020-06-03
 */
public interface IStoragetypeService 
{

    /**
     * 查询部门人数
     *
     * @param parentId 部门信息
     * @return 结果
     */
    public int selectMaterialtypeCount(Long parentId);

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
     * 批量删除库存类别列表
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteStoragetypeByIds(String ids);

    /**
     * 删除库存类别列表信息
     * 
     * @param deptId 库存类别列表ID
     * @return 结果
     */
    public int deleteStoragetypeById(Long deptId);

    /**
     * 查询库存类别列表树列表
     * 
     * @return 所有库存类别列表信息
     */
    public List<Ztree> selectStoragetypeTree();

    /**
     * 查询部门是否存在用户
     *
     * @param deptId 部门ID
     * @return 结果 true 存在 false 不存在
     */
    public boolean checkDeptExistUser(Long deptId);
}
