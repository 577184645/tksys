package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Storageoutbill;

/**
 * 出库单列表Service接口
 * 
 * @author ruoyi
 * @date 2020-06-09
 */
public interface IStorageoutbillService 
{
    /**
     * 查询出库单列表
     * 
     * @param id 出库单列表ID
     * @return 出库单列表
     */
    public Storageoutbill selectStorageoutbillById(Long id);

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
     * 批量删除出库单列表
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteStorageoutbillByIds(String ids);

    /**
     * 删除出库单列表信息
     * 
     * @param id 出库单列表ID
     * @return 结果
     */
    public int deleteStorageoutbillById(Long id);
}
