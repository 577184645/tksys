package com.ruoyi.system.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.ruoyi.system.common.Const;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.mapper.StorageMapper;
import com.ruoyi.system.mapper.StoragequitdetailMapper;
import com.ruoyi.system.mapper.WarehouseRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.StoragequitbillMapper;
import com.ruoyi.system.service.IStoragequitbillService;
import com.ruoyi.common.core.text.Convert;
import org.springframework.transaction.annotation.Transactional;

/**
 * 退料单列表Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-06-18
 */
@Service
public class StoragequitbillServiceImpl implements IStoragequitbillService 
{
    @Autowired
    private StoragequitbillMapper storagequitbillMapper;
    @Autowired
    private StoragequitdetailMapper storagequitdetailMapper;
    @Autowired
    private StorageMapper storageMapper;

    @Autowired
    private WarehouseRecordMapper warehouseRecordMapper;
    /**
     * 查询退料单列表
     * 
     * @param id 退料单列表ID
     * @return 退料单列表
     */
    @Override
    public Storagequitbill selectStoragequitbillById(Long id)
    {
        return storagequitbillMapper.selectStoragequitbillById(id);
    }

    /**
     * 查询退料单列表列表
     * 
     * @param storagequitbill 退料单列表
     * @return 退料单列表
     */
    @Override
    public List<Storagequitbill> selectStoragequitbillList(Storagequitbill storagequitbill)
    {
        return storagequitbillMapper.selectStoragequitbillList(storagequitbill);
    }

    /**
     * 新增退料单列表
     * 
     * @param storagequitbill 退料单列表
     * @return 结果
     */
    @Override
    public int insertStoragequitbill(Storagequitbill storagequitbill)
    {
        return storagequitbillMapper.insertStoragequitbill(storagequitbill);
    }

    /**
     * 修改退料单列表
     * 
     * @param storagequitbill 退料单列表
     * @return 结果
     */
    @Override
    public int updateStoragequitbill(Storagequitbill storagequitbill)
    {
        return storagequitbillMapper.updateStoragequitbill(storagequitbill);
    }

    /**
     * 删除退料单列表对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteStoragequitbillByIds(String ids)
    {
        return storagequitbillMapper.deleteStoragequitbillByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除退料单列表信息
     * 
     * @param id 退料单列表ID
     * @return 结果
     */
    @Override
    public int deleteStoragequitbillById(Long id)
    {
        return storagequitbillMapper.deleteStoragequitbillById(id);
    }

    @Override
    @Transactional
    public int reddashed(Long id) {
        Storagequitbill storagequitbill = storagequitbillMapper.selectStoragequitbillById(id);
        List<Storagequitdetail> storagequitdetails = storagequitdetailMapper.selectStorageindetailByStoragequitbillId(storagequitbill.getStoragequitbillid());
        for (Storagequitdetail storagequitdetail: storagequitdetails) {
            Storage storage=new Storage();
            WarehouseRecord warehouseRecord=new WarehouseRecord();
            storage.setStocks(storagequitdetail.getCounts());
            storage.setMoney(new BigDecimal(storagequitdetail.getMoney()).setScale(2,BigDecimal.ROUND_HALF_UP));
            storage.setId(storagequitdetail.getSid());
            storageMapper.updatereducestocks(storage);
            if(storageMapper.selectStorageById(storage.getId()).getStocks()==0){
                Storage storage1=new Storage();
                storage1.setId(storage.getId());
                storage1.setMoney(new BigDecimal(0));
                storageMapper.updateStorage(storage1);
            }
            warehouseRecord.setType(Const.WarehouseRecordStatus.STORAGE_QUIT_HC);
            warehouseRecord.setNumber(storagequitbill.getStoragequitbillid());
            warehouseRecord.setMaterialcode(storagequitdetail.getMaterialcode());
            warehouseRecord.setName(storagequitdetail.getName());
            warehouseRecord.setCount(storagequitdetail.getCounts());
            warehouseRecord.setPrice(storagequitdetail.getPrice());
            warehouseRecord.setMoney(storagequitdetail.getMoney());
            warehouseRecord.setSerialNumber(storagequitdetail.getSerialNumber());
            warehouseRecord.setSupplier(storagequitdetail.getSupplier());
            warehouseRecordMapper.insertWarehouseRecord(warehouseRecord);
        }
        return storagequitbillMapper.updatedelStatus(id);
    }
}
