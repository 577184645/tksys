package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.MaterialChildMapper;
import com.ruoyi.system.domain.MaterialChild;
import com.ruoyi.system.service.IMaterialChildService;
import com.ruoyi.common.core.text.Convert;

/**
 * 物料Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-12-14
 */
@Service
public class MaterialChildServiceImpl implements IMaterialChildService 
{
    @Autowired
    private MaterialChildMapper materialChildMapper;

    /**
     * 查询物料
     * 
     * @param id 物料ID
     * @return 物料
     */
    @Override
    public MaterialChild selectMaterialChildById(Integer id)
    {
        return materialChildMapper.selectMaterialChildById(id);
    }

    /**
     * 查询物料列表
     * 
     * @param materialChild 物料
     * @return 物料
     */
    @Override
    public List<MaterialChild> selectMaterialChildList(MaterialChild materialChild)
    {
        return materialChildMapper.selectMaterialChildByMaterialId(materialChild.getId());
    }

    /**
     * 新增物料
     * 
     * @param materialChild 物料
     * @return 结果
     */
    @Override
    public int insertMaterialChild(MaterialChild materialChild)
    {
        return materialChildMapper.insertMaterialChild(materialChild);
    }

    /**
     * 修改物料
     * 
     * @param materialChild 物料
     * @return 结果
     */
    @Override
    public int updateMaterialChild(MaterialChild materialChild)
    {
        return materialChildMapper.updateMaterialChild(materialChild);
    }

    /**
     * 删除物料对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteMaterialChildByIds(String ids)
    {
        return materialChildMapper.deleteMaterialChildByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除物料信息
     * 
     * @param id 物料ID
     * @return 结果
     */
    @Override
    public int deleteMaterialChildById(Integer id)
    {
        return materialChildMapper.deleteMaterialChildById(id);
    }
}
