package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.system.common.Const;
import com.ruoyi.system.domain.Storage;
import com.ruoyi.system.domain.Storagequitbill;
import com.ruoyi.system.domain.Storagequitdetail;
import com.ruoyi.system.domain.WarehouseRecord;
import com.ruoyi.system.mapper.StorageMapper;
import com.ruoyi.system.mapper.StoragequitbillMapper;
import com.ruoyi.system.mapper.StoragequitdetailMapper;
import com.ruoyi.system.mapper.WarehouseRecordMapper;
import com.ruoyi.system.service.IStoragequitbillService;
import com.ruoyi.system.util.BigDecimalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * 退料单列表Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-06-18
 */
@Service
@Transactional
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

    public int reddashed(Long id) {
        Storagequitbill storagequitbill = storagequitbillMapper.selectStoragequitbillById(id);
        List<Storagequitdetail> storagequitdetails = storagequitdetailMapper.selectStorageindetailByStoragequitbillId(storagequitbill.getId());
        for (Storagequitdetail storagequitdetail: storagequitdetails) {
            Storage storage=new Storage();
            storage.setId(storagequitdetail.getSid());
            Storage oldstorage = storageMapper.selectStorageById(storage.getId());
            storage.setMoney( BigDecimalUtil.mul(oldstorage.getPrice(), oldstorage.getStocks() - storagequitdetail.getCounts()).doubleValue());
            storage.setStocks(oldstorage.getStocks() - storagequitdetail.getCounts());
            Integer oldstocks = storageMapper.selectStorageById(storagequitdetail.getSid()).getStocks();
            storageMapper.updateStorageById(storage);
            //添加至查询记录
            warehouseRecordMapper.insertWarehouseRecord(new WarehouseRecord(Const.WarehouseRecordStatus.STORAGE_QUIT_HC,storagequitbill.getStoragequitbillid(),storagequitdetail.getMaterialcode(),storagequitdetail.getCounts(),null,null,null,storagequitdetail.getSerialNumber(),storagequitdetail.getComments()));
        }
        //修改退料单状态
        return storagequitbillMapper.updatedelStatus(id);
    }
}
