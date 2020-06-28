package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.WarehouseRecordMapper;
import com.ruoyi.system.domain.WarehouseRecord;
import com.ruoyi.system.service.IWarehouseRecordService;
import com.ruoyi.common.core.text.Convert;

/**
 * 库存记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-06-24
 */
@Service
public class WarehouseRecordServiceImpl implements IWarehouseRecordService 
{
    @Autowired
    private WarehouseRecordMapper warehouseRecordMapper;

    /**
     * 查询库存记录
     * 
     * @param id 库存记录ID
     * @return 库存记录
     */
    @Override
    public WarehouseRecord selectWarehouseRecordById(Long id)
    {
        return warehouseRecordMapper.selectWarehouseRecordById(id);
    }

    /**
     * 查询库存记录列表
     * 
     * @param warehouseRecord 库存记录
     * @return 库存记录
     */
    @Override
    public List<WarehouseRecord> selectWarehouseRecordList(WarehouseRecord warehouseRecord)
    {
        return warehouseRecordMapper.selectWarehouseRecordList(warehouseRecord);
    }

    /**
     * 新增库存记录
     * 
     * @param warehouseRecord 库存记录
     * @return 结果
     */
    @Override
    public int insertWarehouseRecord(WarehouseRecord warehouseRecord)
    {
        return warehouseRecordMapper.insertWarehouseRecord(warehouseRecord);
    }

    /**
     * 修改库存记录
     * 
     * @param warehouseRecord 库存记录
     * @return 结果
     */
    @Override
    public int updateWarehouseRecord(WarehouseRecord warehouseRecord)
    {
        return warehouseRecordMapper.updateWarehouseRecord(warehouseRecord);
    }

    /**
     * 删除库存记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWarehouseRecordByIds(String ids)
    {
        return warehouseRecordMapper.deleteWarehouseRecordByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除库存记录信息
     * 
     * @param id 库存记录ID
     * @return 结果
     */
    @Override
    public int deleteWarehouseRecordById(Long id)
    {
        return warehouseRecordMapper.deleteWarehouseRecordById(id);
    }
}
