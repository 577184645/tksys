package com.ruoyi.system.mapper;

import java.util.List;

import com.ruoyi.system.domain.Storageinbill;
import com.ruoyi.system.domain.Storageoutbill;

/**
 * 出库单列表Mapper接口
 * 
 * @author ruoyi
 * @date 2020-06-09
 */
public interface StorageoutbillMapper 
{
    /**
     * 查询出库单列表
     * 
     * @param id 出库单列表ID
     * @return 出库单列表
     */
    public Storageoutbill selectStorageoutbillById(Long id);


    public Storageoutbill selectStorageoutbillByStorageoutId(String storageoutid);

    /**
     * 查询出库单列表列表
     * 
     * @param storageoutbill 出库单列表
     * @return 出库单列表集合
     */
    public List<Storageoutbill> selectStorageoutbillList(Storageoutbill storageoutbill);

    /**
     * 新增出库单列表
     * 
     * @param storageoutbill 出库单列表
     * @return 结果
     */
    public int insertStorageoutbill(Storageoutbill storageoutbill);

    /**
     * 修改出库单列表
     * 
     * @param storageoutbill 出库单列表
     * @return 结果
     */
    public int updateStorageoutbill(Storageoutbill storageoutbill);

    /**
     * 删除出库单列表
     * 
     * @param id 出库单列表ID
     * @return 结果
     */
    public int deleteStorageoutbillById(Long id);

    /**
     * 批量删除出库单列表
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteStorageoutbillByIds(String[] ids);


    /**
     * 修改删除状态
     *
     * @param id 入库单列表ID
     * @return 入库单列表
     */
    public int updatedelStatus(Long id);

}
