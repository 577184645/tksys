package com.ruoyi.system.service;

import com.ruoyi.system.domain.Storageoutdetail;

import java.util.List;

/**
 * 出库产品列表Service接口
 * 
 * @author ruoyi
 * @date 2020-06-09
 */
public interface IStorageoutdetailService 
{
    /**
     * 查询出库产品列表
     * 
     * @param id 出库产品列表ID
     * @return 出库产品列表
     */
    public Storageoutdetail selectStorageoutdetailById(Long id);




    /**
     * 查询入库产品列表
     */
    public List<Storageoutdetail> selectStorageindetailByStorageoutdetailId(Long storageoutbillid);

    /**
     * 查询出库产品列表列表
     * 
     * @param storageoutdetail 出库产品列表
     * @return 出库产品列表集合
     */
    public List<Storageoutdetail> selectStorageoutdetailList(Storageoutdetail storageoutdetail);

    /**
     * 新增出库产品列表
     * 
     * @param storageoutdetail 出库产品列表
     * @return 结果
     */
    public int insertStorageoutdetail(Storageoutdetail storageoutdetail);



    /**
     * 批量删除出库产品列表
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteStorageoutdetailByIds(String ids);

    /**
     * 删除出库产品列表信息
     * 
     * @param id 出库产品列表ID
     * @return 结果
     */
    public int deleteStorageoutdetailById(Long id);
}
