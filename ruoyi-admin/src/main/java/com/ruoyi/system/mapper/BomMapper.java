package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Bom;

/**
 * bom列表Mapper接口
 * 
 * @author ruoyi
 * @date 2020-07-24
 */
public interface BomMapper 
{
    /**
     * 查询bom列表
     * 
     * @param id bom列表ID
     * @return bom列表
     */
    public Bom selectBomById(Long id);

    /**
     * 查询bom列表列表
     * 
     * @param bom bom列表
     * @return bom列表集合
     */
    public List<Bom> selectBomList(Bom bom);

    /**
     * 新增bom列表
     * 
     * @param bom bom列表
     * @return 结果
     */
    public int insertBom(Bom bom);

    /**
     * 修改bom列表
     * 
     * @param bom bom列表
     * @return 结果
     */
    public int updateBom(Bom bom);

    /**
     * 删除bom列表
     * 
     * @param id bom列表ID
     * @return 结果
     */
    public int deleteBomById(Long id);

    /**
     * 批量删除bom列表
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBomByIds(String[] ids);
}
