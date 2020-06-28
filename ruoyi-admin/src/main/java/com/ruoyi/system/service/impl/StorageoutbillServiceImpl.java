package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.system.domain.*;
import com.ruoyi.system.mapper.StorageMapper;
import com.ruoyi.system.mapper.StorageoutdetailMapper;
import com.ruoyi.system.mapper.WarehouseRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.StorageoutbillMapper;
import com.ruoyi.system.service.IStorageoutbillService;
import com.ruoyi.common.core.text.Convert;

/**
 * 出库单列表Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-06-09
 */
@Service
public class StorageoutbillServiceImpl implements IStorageoutbillService 
{
    @Autowired
    private StorageoutbillMapper storageoutbillMapper;


    @Autowired
    private StorageoutdetailMapper storageoutdetailMapper;

    @Autowired
    private StorageMapper storageMapper;

    @Autowired
    private WarehouseRecordMapper warehouseRecordMapper;

    /**
     * 查询出库单列表
     * 
     * @param id 出库单列表ID
     * @return 出库单列表
     */
    @Override
    public Storageoutbill selectStorageoutbillById(Long id)
    {
        return storageoutbillMapper.selectStorageoutbillById(id);
    }

    /**
     * 查询出库单列表列表
     * 
     * @param storageoutbill 出库单列表
     * @return 出库单列表
     */
    @Override
    public List<Storageoutbill> selectStorageoutbillList(Storageoutbill storageoutbill)
    {
        return storageoutbillMapper.selectStorageoutbillList(storageoutbill);
    }

    /**
     * 新增出库单列表
     * 
     * @param storageoutbill 出库单列表
     * @return 结果
     */
    @Override
    public int insertStorageoutbill(Storageoutbill storageoutbill)
    {
        return storageoutbillMapper.insertStorageoutbill(storageoutbill);
    }

    /**
     * 修改出库单列表
     * 
     * @param storageoutbill 出库单列表
     * @return 结果
     */
    @Override
    public int updateStorageoutbill(Storageoutbill storageoutbill)
    {
        return storageoutbillMapper.updateStorageoutbill(storageoutbill);
    }

    /**
     * 删除出库单列表对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteStorageoutbillByIds(String ids)
    {
        return storageoutbillMapper.deleteStorageoutbillByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除出库单列表信息
     * 
     * @param id 出库单列表ID
     * @return 结果
     */
    @Override
    public int deleteStorageoutbillById(Long id)
    {
        return storageoutbillMapper.deleteStorageoutbillById(id);
    }

    @Override
    public int reddashed(Long id) {
        Storageoutbill storageoutbill = storageoutbillMapper.selectStorageoutbillById(id);
        List<Storageoutdetail> storageoutdetails = storageoutdetailMapper.selectStorageindetailByStorageoutdetailId(storageoutbill.getStorageoutid());
        for (Storageoutdetail storageoutdetail: storageoutdetails) {
            Storage storage=new Storage();
            WarehouseRecord warehouseRecord=new WarehouseRecord();
            storage.setStocks(storageoutdetail.getCounts());
            storage.setMoney(storageoutdetail.getMoney());
            storage.setMaterialcode(storageoutdetail.getMaterialcode());
            storage.setTypeId(storageoutbill.getOutsourcewarehouseid());
            storage.setSerialNumber(storageoutdetail.getSerialNumber());
            storage.setSupplier(storageoutdetail.getSupplier());
            storageMapper.updateaddstocks(storage);
            warehouseRecord.setType("5");
            warehouseRecord.setNumber(storageoutbill.getStorageoutid());
            warehouseRecord.setMaterialcode(storageoutdetail.getMaterialcode());
            warehouseRecord.setName(storageoutdetail.getName());
            warehouseRecord.setCount(storageoutdetail.getCounts());
            warehouseRecord.setPrice(storageoutdetail.getPrice());
            warehouseRecord.setMoney(storageoutdetail.getMoney());
            warehouseRecord.setSerialNumber(storageoutdetail.getSerialNumber());
            warehouseRecord.setSupplier(storageoutdetail.getSupplier());
            warehouseRecordMapper.insertWarehouseRecord(warehouseRecord);
        }
        return storageoutbillMapper.updatedelStatus(id);
    }

}
