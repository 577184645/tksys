package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.StorageMapper;
import com.ruoyi.system.domain.Storage;
import com.ruoyi.system.service.IStorageService;
import com.ruoyi.common.core.text.Convert;

/**
 * 库存列表Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-06-03
 */
@Service
public class StorageServiceImpl implements IStorageService 
{
    @Autowired
    private StorageMapper storageMapper;

    /**
     * 查询库存列表
     * 
     * @param id 库存列表ID
     * @return 库存列表
     */
    @Override
    public Storage selectStorageById(Integer id)
    {
        return storageMapper.selectStorageById(id);
    }

    /**
     * 查询库存列表列表
     * 
     * @param storage 库存列表
     * @return 库存列表
     */
    @Override
    public List<Storage> selectStorageList(Storage storage)
    {
        return storageMapper.selectStorageList(storage);
    }

    /**
     * 新增库存列表
     * 
     * @param storage 库存列表
     * @return 结果
     */
    @Override
    public int insertStorage(Storage storage)
    {
        return storageMapper.insertStorage(storage);
    }

    /**
     * 修改库存列表
     * 
     * @param storage 库存列表
     * @return 结果
     */
    @Override
    public int updateStorage(Storage storage)
    {
        return storageMapper.updateStorage(storage);
    }

    /**
     * 删除库存列表对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteStorageByIds(String ids)
    {
        return storageMapper.deleteStorageByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除库存列表信息
     * 
     * @param id 库存列表ID
     * @return 结果
     */
    @Override
    public int deleteStorageById(Integer id)
    {
        return storageMapper.deleteStorageById(id);
    }
}
