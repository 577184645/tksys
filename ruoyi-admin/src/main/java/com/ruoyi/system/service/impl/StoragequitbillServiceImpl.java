package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.StoragequitbillMapper;
import com.ruoyi.system.domain.Storagequitbill;
import com.ruoyi.system.service.IStoragequitbillService;
import com.ruoyi.common.core.text.Convert;

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
}
