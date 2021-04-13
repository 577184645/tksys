package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.Material;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 物料列表Mapper接口
 * 
 * @author ruoyi
 * @date 2020-06-01
 */
public interface MaterialMapper 
{
    /**
     * 查询物料列表
     * 
     * @param id 物料列表ID
     * @return 物料列表
     */
    public Material selectMaterialById(Long id);




    /**
     * 查询物料列表
     *
     * @param materialcode 物料列表ID
     * @return 物料列表
     */
    public String selectMaterialMaxMaterialcode(String materialcode);



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
     * 修改物料列表
     * 
     * @param material 物料列表
     * @return 结果
     */

    /**
     * 删除物料列表
     * 
     * @param id 物料列表ID
     * @return 结果
     */
    public int deleteMaterialById(Long id);


   public   int updateStatus(@Param("status") Integer status,@Param("id") Long id);
}
