package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Storageinbill;

/**
 * 入库单列表Service接口
 * 
 * @author ruoyi
 * @date 2020-06-04
 */
public interface IStorageinbillService 
{
    /**
     * 查询入库单列表
     * 
     * @param id 入库单列表ID
     * @return 入库单列表
     */
    public Storageinbill selectStorageinbillById(Long id);

    /**
     * 查询入库单列表列表
     * 
     * @param storageinbill 入库单列表
     * @return 入库单列表集合
     */
    public List<Storageinbill> selectStorageinbillList(Storageinbill storageinbill);

    /**
     * 新增入库单列表
     * 
     * @param storageinbill 入库单列表
     * @return 结果
     */
    public int insertStorageinbill(Storageinbill storageinbill);

    /**
     * 修改入库单列表
     * 
     * @param storageinbill 入库单列表
     * @return 结果
     */
    public int updateStorageinbill(Storageinbill storageinbill);

    /**
     * 批量删除入库单列表
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteStorageinbillByIds(String ids);

    /**
     * 删除入库单列表信息
     * 
     * @param id 入库单列表ID
     * @return 结果
     */
    public int deleteStorageinbillById(Long id);
}
