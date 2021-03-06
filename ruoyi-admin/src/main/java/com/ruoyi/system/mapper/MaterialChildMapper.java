package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.MaterialChild;

import java.util.List;

/**
 * 物料Mapper接口
 * 
 * @author ruoyi
 * @date 2020-12-14
 */
public interface MaterialChildMapper 
{

    public List<MaterialChild> selectMaterialChildByMaterialId(Long materialId);


    /**
     * 查询物料
     * 
     * @param id 物料ID
     * @return 物料
     */
    public MaterialChild selectMaterialChildById(Integer id);



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




    public int deleteMaterialChildByMaterialId(Long id);

    /**
     * 批量删除物料
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMaterialChildByIds(String[] ids);
}
