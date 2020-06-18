package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.system.domain.Material;
import com.ruoyi.system.domain.Storageinbill;
import com.ruoyi.system.domain.Testss;

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



    /** 提交申请
     * 修改status为1
     * @param
     * @return
     */
    public int updateStorageinbillApply(Long id);
    /** 批准
     * 修改status为2
     * @param
     * @return
     */
    public int updateStorageinbillFatify(Long id);

    /**驳回
     * 修改status为0
     * @param
     * @return
     */
    public int updateStorageinbillTurn(Long id);

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
