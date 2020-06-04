package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.StorageinbillMapper;
import com.ruoyi.system.domain.Storageinbill;
import com.ruoyi.system.service.IStorageinbillService;
import com.ruoyi.common.core.text.Convert;

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
}
