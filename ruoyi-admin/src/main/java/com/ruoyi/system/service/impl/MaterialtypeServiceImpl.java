package com.ruoyi.system.service.impl;

import java.util.List;
import java.util.ArrayList;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.system.domain.SysDept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.MaterialtypeMapper;
import com.ruoyi.system.domain.Materialtype;
import com.ruoyi.system.service.IMaterialtypeService;
import com.ruoyi.common.core.text.Convert;

/**
 * 物料类型列表Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-06-01
 */
@Service
public class MaterialtypeServiceImpl implements IMaterialtypeService 
{
    @Autowired
    private MaterialtypeMapper materialtypeMapper;

    /**
     * 查询物料类型列表
     * 
     * @param deptId 物料类型列表ID
     * @return 物料类型列表
     */
    @Override
    public Materialtype selectMaterialtypeById(Long deptId)
    {
        return materialtypeMapper.selectMaterialtypeById(deptId);
    }

    /**
     * 查询物料类型列表列表
     * 
     * @param materialtype 物料类型列表
     * @return 物料类型列表
     */
    @Override
    public List<Materialtype> selectMaterialtypeList(Materialtype materialtype)
    {
        return materialtypeMapper.selectMaterialtypeList(materialtype);
    }

    /**
     * 新增物料类型列表
     * 
     * @param materialtype 物料类型列表
     * @return 结果
     */
    @Override
    public int insertMaterialtype(Materialtype materialtype)
    {
        Materialtype info = materialtypeMapper.selectMaterialtypeById(materialtype.getParentId());
        // 如果父节点不为"正常"状态,则不允许新增子节点
        if (!UserConstants.DEPT_NORMAL.equals(info.getStatus()))
        {
            throw new BusinessException("部门停用，不允许新增");
        }
        materialtype.setCreateTime(DateUtils.getNowDate());
        materialtype.setAncestors(info.getAncestors() + "," + materialtype.getParentId());
        return materialtypeMapper.insertMaterialtype(materialtype);
    }

    /**
     * 修改物料类型列表
     * 
     * @param materialtype 物料类型列表
     * @return 结果
     */
    @Override
    public int updateMaterialtype(Materialtype materialtype)
    {
        materialtype.setUpdateTime(DateUtils.getNowDate());
        return materialtypeMapper.updateMaterialtype(materialtype);
    }

    /**
     * 删除物料类型列表对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteMaterialtypeByIds(String ids)
    {
        return materialtypeMapper.deleteMaterialtypeByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除物料类型列表信息
     * 
     * @param deptId 物料类型列表ID
     * @return 结果
     */
    @Override
    public int deleteMaterialtypeById(Long deptId)
    {
        return materialtypeMapper.deleteMaterialtypeById(deptId);
    }

    /**
     * 查询物料类型列表树列表
     * 
     * @return 所有物料类型列表信息
     */
    @Override
    public List<Ztree> selectMaterialtypeTree()
    {
        List<Materialtype> materialtypeList = materialtypeMapper.selectMaterialtypeList(new Materialtype());
        List<Ztree> ztrees = new ArrayList<Ztree>();
        for (Materialtype materialtype : materialtypeList)
        {
            Ztree ztree = new Ztree();
            ztree.setId(materialtype.getDeptId());
            ztree.setpId(materialtype.getParentId());
            ztree.setName(materialtype.getDeptName());
            ztree.setTitle(materialtype.getDeptName());
            ztrees.add(ztree);
        }
        return ztrees;
    }

    @Override
    public int selectMaterialtypeCount(Long parentId) {
        Materialtype materialtype=new Materialtype();
        materialtype.setParentId(parentId);
        return materialtypeMapper.selectMaterialtypeCount(materialtype);
    }

    @Override
    public boolean checkDeptExistUser(Long deptId) {
        int result = materialtypeMapper.checkDeptExistUser(deptId);
        return result > 0 ? true : false;
    }


}
