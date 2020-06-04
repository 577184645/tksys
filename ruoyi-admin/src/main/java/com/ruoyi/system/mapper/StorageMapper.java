package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Storage;

/**
 * 库存列表Mapper接口
 * 
 * @author ruoyi
 * @date 2020-06-03
 */
public interface StorageMapper 
{
    /**
     * 查询库存列表
     * 
     * @param id 库存列表ID
     * @return 库存列表
     */
    public Storage selectStorageById(Integer id);

    /**
     * 查询库存列表列表
     * 
     * @param storage 库存列表
     * @return 库存列表集合
     */
    public List<Storage> selectStorageList(Storage storage);

    /**
     * 新增库存列表
     * 
     * @param storage 库存列表
     * @return 结果
     */
    public int insertStorage(Storage storage);

    /**
     * 修改库存列表
     * 
     * @param storage 库存列表
     * @return 结果
     */
    public int updateStorage(Storage storage);

    /**
     * 删除库存列表
     * 
     * @param id 库存列表ID
     * @return 结果
     */
    public int deleteStorageById(Integer id);

    /**
     * 批量删除库存列表
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteStorageByIds(String[] ids);
}
