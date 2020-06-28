package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.WarehouseRecord;
import org.apache.ibatis.annotations.Param;

/**
 * 库存记录Mapper接口
 * 
 * @author ruoyi
 * @date 2020-06-24
 */
public interface WarehouseRecordMapper 
{
    /**
     * 查询库存记录
     * 
     * @param id 库存记录ID
     * @return 库存记录
     */
    public WarehouseRecord selectWarehouseRecordById(Long id);




   public  int  updateMaterial(@Param("materialcode") String materialcode,@Param("name") String name,@Param("oldmaterialcode") String oldmaterialcode);


    /**
     * 查询库存记录列表
     * 
     * @param warehouseRecord 库存记录
     * @return 库存记录集合
     */
    public List<WarehouseRecord> selectWarehouseRecordList(WarehouseRecord warehouseRecord);

    /**
     * 新增库存记录
     * 
     * @param warehouseRecord 库存记录
     * @return 结果
     */
    public int insertWarehouseRecord(WarehouseRecord warehouseRecord);

    /**
     * 修改库存记录
     * 
     * @param warehouseRecord 库存记录
     * @return 结果
     */
    public int updateWarehouseRecord(WarehouseRecord warehouseRecord);

    /**
     * 删除库存记录
     * 
     * @param id 库存记录ID
     * @return 结果
     */
    public int deleteWarehouseRecordById(Long id);

    /**
     * 批量删除库存记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWarehouseRecordByIds(String[] ids);
}
