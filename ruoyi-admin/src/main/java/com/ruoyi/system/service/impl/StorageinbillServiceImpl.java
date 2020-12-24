package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.common.Const;
import com.ruoyi.system.domain.Storage;
import com.ruoyi.system.domain.Storageinbill;
import com.ruoyi.system.domain.Storageindetail;
import com.ruoyi.system.domain.WarehouseRecord;
import com.ruoyi.system.mapper.StorageMapper;
import com.ruoyi.system.mapper.StorageinbillMapper;
import com.ruoyi.system.mapper.StorageindetailMapper;
import com.ruoyi.system.mapper.WarehouseRecordMapper;
import com.ruoyi.system.service.IStorageinbillService;
import com.ruoyi.system.util.BigDecimalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        if (StringUtils.isNotEmpty(storageinbill.getInvoiceid())){
            storageinbill.setStorageStatus(2);
        }
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
        //根据入单号号获得入库单所有的信息
        List<Storageindetail> storageindetails = storageindetailMapper.selectStorageindetailByStorageinbillId(storageinbill.getStockinid());
        //循环入库单的产品
        for (Storageindetail storageindetail:
        storageindetails) {
            Storage storage=new Storage();
            WarehouseRecord warehouseRecord=new WarehouseRecord();
            //根据入库单的产品sid得到该产品当前库存信息
            Storage oldstorage = storageMapper.selectStorageById(storageindetail.getSid());
            //设置库存的id  和最终库存
            storage.setId(storageindetail.getSid());
            storage.setStocks(oldstorage.getStocks()-storageindetail.getCounts());
            //根据入库单的产品sid得到历史该产品的入库信息
            List<Storageindetail> oldstorageindetails = storageindetailMapper.selectStorageindetailByStorageinbillSid(storageindetail.getSid());
            double  sum=0;
            //取所有入库金额的总额用于计算平均价格
            for (Storageindetail oldstorageindetail1 : oldstorageindetails) {
                sum+=oldstorageindetail1.getPrice();
            }
            //减去当前红冲价格
            sum-=storageindetail.getPrice();

            //计算平均价格
            storage.setPrice(sum!=0?BigDecimalUtil.div(sum,oldstorageindetails.size()-1,4).doubleValue():0);
            //得到总价
            storage.setMoney(BigDecimalUtil.mul(storage.getPrice(),storage.getStocks()).doubleValue());
            storageMapper.updateStorageById(storage);
            //添加至查询记录
            warehouseRecord.setType(Const.WarehouseRecordStatus.STORAGE_IN_HC);
            warehouseRecord.setNumber(storageinbill.getStockinid());
            warehouseRecord.setMaterialcode(storageindetail.getMaterialcode());
            warehouseRecord.setCount(storageindetail.getCounts());
            warehouseRecord.setPrice(storageindetail.getPrice());
            warehouseRecord.setMoney(storageindetail.getMoney());
            warehouseRecord.setSerialNumber(storageindetail.getSerialNumber());
            warehouseRecord.setSupplier(storageindetail.getSupplier());
            warehouseRecord.setRemark(storageindetail.getRemark());

            warehouseRecordMapper.insertWarehouseRecord(warehouseRecord);
        }
        //修改入库单的状态
        return storageinbillMapper.updatedelStatus(id);
    }
}
