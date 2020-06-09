package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Storageoutdetail;

/**
 * 出库产品列表Mapper接口
 * 
 * @author ruoyi
 * @date 2020-06-09
 */
public interface StorageoutdetailMapper 
{
    /**
     * 查询出库产品列表
     * 
     * @param id 出库产品列表ID
     * @return 出库产品列表
     */
    public Storageoutdetail selectStorageoutdetailById(Long id);

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
     * 修改出库产品列表
     * 
     * @param storageoutdetail 出库产品列表
     * @return 结果
     */
    public int updateStorageoutdetail(Storageoutdetail storageoutdetail);

    /**
     * 删除出库产品列表
     * 
     * @param id 出库产品列表ID
     * @return 结果
     */
    public int deleteStorageoutdetailById(Long id);

    /**
     * 批量删除出库产品列表
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteStorageoutdetailByIds(String[] ids);
}
