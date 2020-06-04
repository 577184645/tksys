package com.ruoyi.system.service.impl;

import java.util.List;
import java.util.ArrayList;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.Materialtype;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.StoragetypeMapper;
import com.ruoyi.system.domain.Storagetype;
import com.ruoyi.system.service.IStoragetypeService;
import com.ruoyi.common.core.text.Convert;

/**
 * 库存类别列表Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-06-03
 */
@Service
public class StoragetypeServiceImpl implements IStoragetypeService 
{
    @Autowired
    private StoragetypeMapper storagetypeMapper;

    @Override
    public int selectMaterialtypeCount(Long parentId) {
        Storagetype storagetype=new Storagetype();
        storagetype.setParentId(parentId);
        return storagetypeMapper.selectMaterialtypeCount(storagetype);
    }

    /**
     * 查询库存类别列表
     * 
     * @param deptId 库存类别列表ID
     * @return 库存类别列表
     */
    @Override
    public Storagetype selectStoragetypeById(Long deptId)
    {
        return storagetypeMapper.selectStoragetypeById(deptId);
    }

    /**
     * 查询库存类别列表列表
     * 
     * @param storagetype 库存类别列表
     * @return 库存类别列表
     */
    @Override
    public List<Storagetype> selectStoragetypeList(Storagetype storagetype)
    {
        return storagetypeMapper.selectStoragetypeList(storagetype);
    }

    /**
     * 新增库存类别列表
     * 
     * @param storagetype 库存类别列表
     * @return 结果
     */
    @Override
    public int insertStoragetype(Storagetype storagetype)
    {
        Storagetype info = storagetypeMapper.selectStoragetypeById(storagetype.getParentId());
        storagetype.setCreateTime(DateUtils.getNowDate());
        storagetype.setAncestors(info.getAncestors() + "," + storagetype.getParentId());
        storagetype.setCreateTime(DateUtils.getNowDate());
        return storagetypeMapper.insertStoragetype(storagetype);
    }

    /**
     * 修改库存类别列表
     * 
     * @param storagetype 库存类别列表
     * @return 结果
     */
    @Override
    public int updateStoragetype(Storagetype storagetype)
    {
        storagetype.setUpdateTime(DateUtils.getNowDate());
        return storagetypeMapper.updateStoragetype(storagetype);
    }

    /**
     * 删除库存类别列表对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteStoragetypeByIds(String ids)
    {
        return storagetypeMapper.deleteStoragetypeByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除库存类别列表信息
     * 
     * @param deptId 库存类别列表ID
     * @return 结果
     */
    @Override
    public int deleteStoragetypeById(Long deptId)
    {
        return storagetypeMapper.deleteStoragetypeById(deptId);
    }

    /**
     * 查询库存类别列表树列表
     * 
     * @return 所有库存类别列表信息
     */
    @Override
    public List<Ztree> selectStoragetypeTree()
    {
        List<Storagetype> storagetypeList = storagetypeMapper.selectStoragetypeList(new Storagetype());
        List<Ztree> ztrees = new ArrayList<Ztree>();
        for (Storagetype storagetype : storagetypeList)
        {
            Ztree ztree = new Ztree();
            ztree.setId(storagetype.getDeptId());
            ztree.setpId(storagetype.getParentId());
            ztree.setName(storagetype.getDeptName());
            ztree.setTitle(storagetype.getDeptName());
            ztrees.add(ztree);
        }
        return ztrees;
    }

    @Override
    public boolean checkDeptExistUser(Long deptId) {
        int result = storagetypeMapper.checkDeptExistUser(deptId);
        return result > 0 ? true : false;
    }
}
