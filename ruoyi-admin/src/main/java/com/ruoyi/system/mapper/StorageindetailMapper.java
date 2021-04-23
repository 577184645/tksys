package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.Storageindetail;

import java.util.List;

/**
 * 入库产品列表Mapper接口
 * 
 * @author ruoyi
 * @date 2020-06-05
 */
public interface StorageindetailMapper 
{
    /**
     * 查询入库产品列表
     * 
     * @param id 入库产品列表ID
     * @return 入库产品列表
     */
    public Storageindetail selectStorageindetailById(Long id);


    public List<Storageindetail> findByStorageinbillId(Long id);
    /**
     * 查询入库产品列表
     */
    public List<Storageindetail> selectStorageindetailByStorageinbillSid(Long storageinbillid);



    /**
     * 查询入库产品列表列表
     * 
     * @param storageindetail 入库产品列表
     * @return 入库产品列表集合
     */
    public List<Storageindetail> selectStorageindetailList(Storageindetail storageindetail);

    /**
     * 新增入库产品列表
     * 
     * @param storageindetail 入库产品列表
     * @return 结果
     */
    public int insertStorageindetail(Storageindetail storageindetail);



    /**
     * 删除入库产品列表
     * 
     * @param id 入库产品列表ID
     * @return 结果
     */
    public int deleteStorageindetailById(Long id);



}
