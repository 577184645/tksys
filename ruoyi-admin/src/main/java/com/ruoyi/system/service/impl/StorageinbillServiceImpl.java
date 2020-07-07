package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.system.domain.Storage;
import com.ruoyi.system.domain.Storageindetail;
import com.ruoyi.system.domain.WarehouseRecord;
import com.ruoyi.system.mapper.StorageMapper;
import com.ruoyi.system.mapper.StorageindetailMapper;
import com.ruoyi.system.mapper.WarehouseRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.StorageinbillMapper;
import com.ruoyi.system.domain.Storageinbill;
import com.ruoyi.system.service.IStorageinbillService;
import com.ruoyi.common.core.text.Convert;
import org.springframework.transaction.annotation.Transactional;

/**
 * 入库单列表Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-06-04
 */
@Service
public class StorageinbillServiceImpl implements IStorageinbillService 
{
    @Autowired
    private StorageinbillMapper storageinbillMapper;
    @Autowired
    private StorageindetailMapper storageindetailMapper;
    @Autowired
    private StorageMapper storageMapper;
    @Autowired
    private WarehouseRecordMapper warehouseRecordMapper;
    /**
     * 查询入库单列表
     * 
     * @param id 入库单列表ID
     * @return 入库单列表
     */
    @Override
    public Storageinbill selectStorageinbillById(Long id)
    {
        return storageinbillMapper.selectStorageinbillById(id);
    }

    @Override
    public int updateStorageinbillApply(Long id) {
        return storageinbillMapper.updateStorageinbillApply(id);
    }

    @Override
    public int updateStorageinbillFatify(Long id) {
        return storageinbillMapper.updateStorageinbillFatify(id);
    }

    @Override
    public int updateStorageinbillTurn(Long id) {
        return storageinbillMapper.updateStorageinbillTurn(id);
    }

    /**
     * 查询入库单列表列表
     * 
     * @param storageinbill 入库单列表
     * @return 入库单列表
     */
    @Override
    public List<Storageinbill> selectStorageinbillList(Storageinbill storageinbill)
    {
        return storageinbillMapper.selectStorageinbillList(storageinbill);
    }

    /**
     * 新增入库单列表
     * 
     * @param storageinbill 入库单列表
     * @return 结果
     */
    @Override
    public int insertStorageinbill(Storageinbill storageinbill)
    {
        return storageinbillMapper.insertStorageinbill(storageinbill);
    }

    /**
     * 修改入库单列表
     * 
     * @param storageinbill 入库单列表
     * @return 结果
     */
    @Override
    public int updateStorageinbill(Storageinbill storageinbill)
    {
        return storageinbillMapper.updateStorageinbill(storageinbill);
    }

    /**
     * 删除入库单列表对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteStorageinbillByIds(String ids)
    {
        return storageinbillMapper.deleteStorageinbillByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除入库单列表信息
     * 
     * @param id 入库单列表ID
     * @return 结果
     */
    @Override
    public int deleteStorageinbillById(Long id)
    {
        return storageinbillMapper.deleteStorageinbillById(id);
    }

    @Override
    @Transactional
    public int reddashed(Long id) {
        Storageinbill storageinbill = storageinbillMapper.selectStorageinbillById(id);
        List<Storageindetail> storageindetails = storageindetailMapper.selectStorageindetailByStorageinbillId(storageinbill.getStockinid());
        for (Storageindetail storageindetail:
        storageindetails) {
            Storage storage=new Storage();
            WarehouseRecord warehouseRecord=new WarehouseRecord();
            storage.setStocks(storageindetail.getCounts());
            storage.setMoney(storageindetail.getMoney());
            storage.setMaterialcode(storageindetail.getMaterialcode());
            storage.setTypeId(storageinbill.getOutsourcewarehouseid());
            storage.setSerialNumber(storageindetail.getSerialNumber());
            storage.setSupplier(storageindetail.getSupplier());
            storageMapper.updatereducestocks(storage);
            warehouseRecord.setType("4");
            warehouseRecord.setNumber(storageinbill.getStockinid());
            warehouseRecord.setMaterialcode(storageindetail.getMaterialcode());
            warehouseRecord.setName(storageindetail.getName());
            warehouseRecord.setCount(storageindetail.getCounts());
            warehouseRecord.setPrice(storageindetail.getPrice());
            warehouseRecord.setMoney(storageindetail.getMoney());
            warehouseRecord.setSerialNumber(storageindetail.getSerialNumber());
            warehouseRecord.setSupplier(storageindetail.getSupplier());
            warehouseRecordMapper.insertWarehouseRecord(warehouseRecord);
        }
        return storageinbillMapper.updatedelStatus(id);
    }
}