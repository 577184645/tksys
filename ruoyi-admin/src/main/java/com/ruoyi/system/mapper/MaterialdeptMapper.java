package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Materialdept;

/**
 * 物料部门列表Mapper接口
 * 
 * @author ruoyi
 * @date 2020-06-02
 */
public interface MaterialdeptMapper 
{
    /**
     * 查询物料部门列表
     * 
     * @param id 物料部门列表ID
     * @return 物料部门列表
     */
    public Materialdept selectMaterialdeptById(Long id);

    /**
     * 查询物料部门列表
     *
     * @param code 物料部门列表ID
     * @return 物料部门列表
     */
    public Materialdept selectMaterialdeptByCode(String code);

    /**
     * 查询物料部门列表列表
     * 
     * @param materialdept 物料部门列表
     * @return 物料部门列表集合
     */
    public List<Materialdept> selectMaterialdeptList(Materialdept materialdept);

    /**
     * 新增物料部门列表
     * 
     * @param materialdept 物料部门列表
     * @return 结果
     */
    public int insertMaterialdept(Materialdept materialdept);

    /**
     * 修改物料部门列表
     * 
     * @param materialdept 物料部门列表
     * @return 结果
     */
    public int updateMaterialdept(Materialdept materialdept);

    /**
     * 删除物料部门列表
     * 
     * @param id 物料部门列表ID
     * @return 结果
     */
    public int deleteMaterialdeptById(Long id);

    /**
     * 批量删除物料部门列表
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMaterialdeptByIds(String[] ids);
}
