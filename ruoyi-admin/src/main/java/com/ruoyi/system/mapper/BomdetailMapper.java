package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.Bomdetail;

import java.util.List;

/**
 * bom详细清单Mapper接口
 * 
 * @author ruoyi
 * @date 2020-07-24
 */
public interface BomdetailMapper 
{
    /**
     * 查询bom详细清单
     * 
     * @param id bom详细清单ID
     * @return bom详细清单
     */
    public Bomdetail selectBomdetailById(Integer id);

    /**
     * 查询bom详细清单列表
     * 
     * @param bomdetail bom详细清单
     * @return bom详细清单集合
     */
    public List<Bomdetail> selectBomdetailList(Bomdetail bomdetail);

    public List<Bomdetail> selectBomBybomId(Long bomid);


    /**
     * 新增bom详细清单
     * 
     * @param bomdetail bom详细清单
     * @return 结果
     */
    public int insertBomdetail(Bomdetail bomdetail);

    /**
     * 修改bom详细清单
     * 
     * @param bomdetail bom详细清单
     * @return 结果
     */
    public int updateBomdetail(Bomdetail bomdetail);

    /**
     * 删除bom详细清单
     * 
     * @param id bom详细清单ID
     * @return 结果
     */
    public int deleteBomdetailById(Long id);

    /**
     * 批量删除bom详细清单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBomdetailByIds(String[] ids);
}
