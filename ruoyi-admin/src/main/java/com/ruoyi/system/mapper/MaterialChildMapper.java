package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.MaterialChild;

/**
 * 物料Mapper接口
 * 
 * @author ruoyi
 * @date 2020-12-14
 */
public interface MaterialChildMapper 
{
    /**
     * 查询物料
     * 
     * @param id 物料ID
     * @return 物料
     */
    public MaterialChild selectMaterialChildById(Integer id);

    /**
     * 查询物料列表
     * 
     * @param materialChild 物料
     * @return 物料集合
     */
    public List<MaterialChild> selectMaterialChildList(MaterialChild materialChild);

    /**
     * 新增物料
     * 
     * @param materialChild 物料
     * @return 结果
     */
    public int insertMaterialChild(MaterialChild materialChild);

    /**
     * 修改物料
     * 
     * @param materialChild 物料
     * @return 结果
     */
    public int updateMaterialChild(MaterialChild materialChild);

    /**
     * 删除物料
     * 
     * @param id 物料ID
     * @return 结果
     */
    public int deleteMaterialChildById(Integer id);

    /**
     * 批量删除物料
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMaterialChildByIds(String[] ids);
}
