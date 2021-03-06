package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.Material;

import java.util.List;

/**
 * 物料列表Service接口
 * 
 * @author ruoyi
 * @date 2020-06-01
 */
public interface IMaterialService 
{
    /**
     * 查询物料列表
     *
     * @param id 物料列表ID
     * @return 物料列表
     */
    public Material selectMaterialById(Long id);


    /**
     * 得到物料编号
     * @param typeId
     * @param deptId
     * @return
     */
    public String getMaterialcode(Long typeId,Long deptId );

    /**
     * 查询物料列表列表
     * 
     * @param material 物料列表
     * @return 物料列表集合
     */
    public List<Material> selectMaterialList(Material material);

    /**
     * 新增物料列表
     * 
     * @param material 物料列表
     * @return 结果
     */
    public int insertMaterial(Material material);





    /**
     * 批量删除物料列表
     * 
     * @param id 需要删除的数据ID
     * @return 结果
     */
    public AjaxResult deleteMaterial(Long id);





    /**
     * 导入物料数据
     *
     * @param materialList 物料数据列表
     * @return 结果
     */
    public String importMaterial(List<Material> materialList);

    AjaxResult abandoned(String ids);

    AjaxResult recovery(String ids);
}
