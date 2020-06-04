package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.MaterialdeptMapper;
import com.ruoyi.system.domain.Materialdept;
import com.ruoyi.system.service.IMaterialdeptService;
import com.ruoyi.common.core.text.Convert;

/**
 * 物料部门列表Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-06-02
 */
@Service
public class MaterialdeptServiceImpl implements IMaterialdeptService 
{
    @Autowired
    private MaterialdeptMapper materialdeptMapper;

    /**
     * 查询物料部门列表
     * 
     * @param id 物料部门列表ID
     * @return 物料部门列表
     */
    @Override
    public Materialdept selectMaterialdeptById(Long id)
    {
        return materialdeptMapper.selectMaterialdeptById(id);
    }

    /**
     * 查询物料部门列表列表
     * 
     * @param materialdept 物料部门列表
     * @return 物料部门列表
     */
    @Override
    public List<Materialdept> selectMaterialdeptList(Materialdept materialdept)
    {
        return materialdeptMapper.selectMaterialdeptList(materialdept);
    }

    /**
     * 新增物料部门列表
     * 
     * @param materialdept 物料部门列表
     * @return 结果
     */
    @Override
    public int insertMaterialdept(Materialdept materialdept)
    {
        return materialdeptMapper.insertMaterialdept(materialdept);
    }

    /**
     * 修改物料部门列表
     * 
     * @param materialdept 物料部门列表
     * @return 结果
     */
    @Override
    public int updateMaterialdept(Materialdept materialdept)
    {
        return materialdeptMapper.updateMaterialdept(materialdept);
    }

    /**
     * 删除物料部门列表对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteMaterialdeptByIds(String ids)
    {
        return materialdeptMapper.deleteMaterialdeptByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除物料部门列表信息
     * 
     * @param id 物料部门列表ID
     * @return 结果
     */
    @Override
    public int deleteMaterialdeptById(Long id)
    {
        return materialdeptMapper.deleteMaterialdeptById(id);
    }
}
