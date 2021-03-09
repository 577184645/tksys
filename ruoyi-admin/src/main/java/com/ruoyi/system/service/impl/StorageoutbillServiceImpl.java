package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.system.common.Const;
import com.ruoyi.system.domain.Storage;
import com.ruoyi.system.domain.Storageoutbill;
import com.ruoyi.system.domain.Storageoutdetail;
import com.ruoyi.system.domain.WarehouseRecord;
import com.ruoyi.system.mapper.StorageMapper;
import com.ruoyi.system.mapper.StorageoutbillMapper;
import com.ruoyi.system.mapper.StorageoutdetailMapper;
import com.ruoyi.system.mapper.WarehouseRecordMapper;
import com.ruoyi.system.service.IStorageoutbillService;
import com.ruoyi.system.util.BigDecimalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * 出库单列表Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-06-09
 */
@Service
@Transactional
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
        List<Storageoutdetail> storageoutdetails = storageoutdetailMapper.selectStorageindetailByStorageoutbillId(storageoutbill.getId());
        for (Storageoutdetail storageoutdetail: storageoutdetails) {
            Storage storage=new Storage();
            storage.setId(storageoutdetail.getSid());
            //得到原有库存相减得到更新库存价格
            Storage oldstorage = storageMapper.selectStorageById(storage.getId());
            storage.setMoney( BigDecimalUtil.mul(oldstorage.getPrice(), oldstorage.getStocks() + storageoutdetail.getCounts()).doubleValue());
            storage.setStocks(oldstorage.getStocks() + storageoutdetail.getCounts());
            Integer oldstocks = storageMapper.selectStorageById(storageoutdetail.getSid()).getStocks();
            storage.setStocks(oldstocks+storageoutdetail.getCounts());
            storageMapper.updateStorageById(storage);
            //添加至查询记录
            warehouseRecordMapper.insertWarehouseRecord(new WarehouseRecord(Const.WarehouseRecordStatus.STORAGE_OUT_HC,storageoutbill.getStorageoutid(),storageoutdetail.getMaterialcode(),storageoutdetail.getCounts(),null,null,null,storageoutdetail.getSerialNumber(),storageoutdetail.getComments()));
        }
        //修改出库单的状态
        return storageoutbillMapper.updatedelStatus(id);
    }

}
