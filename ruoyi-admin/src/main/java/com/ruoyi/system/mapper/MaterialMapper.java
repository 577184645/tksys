package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Material;
import org.apache.ibatis.annotations.Param;

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



    public int selectMaterialRepetition(@Param("name") String name,@Param("partnumber") String partnumber,@Param("footprint") String footprint,@Param("manufacture") String manufacture,@Param("deptId") Long deptId);

    /**
     * 查询物料列表
     *
     * @param materialcode 物料列表ID
     * @return 物料列表
     */
    public String selectMaterialByMaterialcode(String materialcode);

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
    public int updateMaterial(Material material);

    /**
     * 删除物料列表
     * 
     * @param id 物料列表ID
     * @return 结果
     */
    public int deleteMaterialById(Long id);

    /**
     * 批量删除物料列表
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMaterialByIds(String[] ids);
}
