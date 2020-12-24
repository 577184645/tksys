package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.StorageindetailMapper;
import com.ruoyi.system.domain.Storageindetail;
import com.ruoyi.system.service.IStorageindetailService;
import com.ruoyi.common.core.text.Convert;

/**
 * 入库产品列表Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-06-05
 */
@Service
public class StorageindetailServiceImpl implements IStorageindetailService 
{
    @Autowired
    private StorageindetailMapper storageindetailMapper;

    @Override
    public List<Storageindetail> selectStorageindetailByStorageinbillId(String storageinbillid) {
        return storageindetailMapper.selectStorageindetailByStorageinbillId(storageinbillid);
    }

    /**
     * 查询入库产品列表
     * 
     * @param id 入库产品列表ID
     * @return 入库产品列表
     */
    @Override
    public Storageindetail selectStorageindetailById(Long id)
    {
        return storageindetailMapper.selectStorageindetailById(id);
    }

    /**
     * 查询入库产品列表列表
     * 
     * @param storageindetail 入库产品列表
     * @return 入库产品列表
     */
    @Override
    public List<Storageindetail> selectStorageindetailList(Storageindetail storageindetail)
    {
        return storageindetailMapper.selectStorageindetailList(storageindetail);
    }

    /**
     * 新增入库产品列表
     * 
     * @param storageindetail 入库产品列表
     * @return 结果
     */
    @Override
    public int insertStorageindetail(Storageindetail storageindetail)
    {
        return storageindetailMapper.insertStorageindetail(storageindetail);
    }


    /**
     * 删除入库产品列表对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteStorageindetailByIds(String ids)
    {
        return storageindetailMapper.deleteStorageindetailByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除入库产品列表信息
     * 
     * @param id 入库产品列表ID
     * @return 结果
     */
    @Override
    public int deleteStorageindetailById(Long id)
    {
        return storageindetailMapper.deleteStorageindetailById(id);
    }
}
