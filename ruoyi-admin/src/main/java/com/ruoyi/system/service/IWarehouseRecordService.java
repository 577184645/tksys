package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.WarehouseRecord;

/**
 * 库存记录Service接口
 * 
 * @author ruoyi
 * @date 2020-06-24
 */
public interface IWarehouseRecordService 
{
    /**
     * 查询库存记录
     * 
     * @param id 库存记录ID
     * @return 库存记录
     */
    public WarehouseRecord selectWarehouseRecordById(Long id);

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
     * 批量删除库存记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWarehouseRecordByIds(String ids);

    /**
     * 删除库存记录信息
     * 
     * @param id 库存记录ID
     * @return 结果
     */
    public int deleteWarehouseRecordById(Long id);
}
