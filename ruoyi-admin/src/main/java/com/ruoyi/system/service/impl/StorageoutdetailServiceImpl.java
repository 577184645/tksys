package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.StorageoutdetailMapper;
import com.ruoyi.system.domain.Storageoutdetail;
import com.ruoyi.system.service.IStorageoutdetailService;
import com.ruoyi.common.core.text.Convert;

/**
 * 出库产品列表Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-06-09
 */
@Service
public class StorageoutdetailServiceImpl implements IStorageoutdetailService 
{
    @Autowired
    private StorageoutdetailMapper storageoutdetailMapper;

    /**
     * 查询出库产品列表
     * 
     * @param id 出库产品列表ID
     * @return 出库产品列表
     */
    @Override
    public Storageoutdetail selectStorageoutdetailById(Long id)
    {
        return storageoutdetailMapper.selectStorageoutdetailById(id);
    }

    @Override
    public List<Storageoutdetail> selectStorageindetailByStorageoutdetailId(Long storageoutbillid) {
        return storageoutdetailMapper.selectStorageindetailByStorageoutbillId(storageoutbillid);
    }

    /**
     * 查询出库产品列表列表
     * 
     * @param storageoutdetail 出库产品列表
     * @return 出库产品列表
     */
    @Override
    public List<Storageoutdetail> selectStorageoutdetailList(Storageoutdetail storageoutdetail)
    {
        return storageoutdetailMapper.selectStorageoutdetailList(storageoutdetail);
    }

    /**
     * 新增出库产品列表
     * 
     * @param storageoutdetail 出库产品列表
     * @return 结果
     */
    @Override
    public int insertStorageoutdetail(Storageoutdetail storageoutdetail)
    {
        return storageoutdetailMapper.insertStorageoutdetail(storageoutdetail);
    }



    /**
     * 删除出库产品列表对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteStorageoutdetailByIds(String ids)
    {
        return storageoutdetailMapper.deleteStorageoutdetailByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除出库产品列表信息
     * 
     * @param id 出库产品列表ID
     * @return 结果
     */
    @Override
    public int deleteStorageoutdetailById(Long id)
    {
        return storageoutdetailMapper.deleteStorageoutdetailById(id);
    }
}
