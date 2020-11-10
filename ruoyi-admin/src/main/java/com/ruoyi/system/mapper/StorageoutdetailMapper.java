package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.Storageoutdetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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


    public int updateMaterial(@Param("name") String name, @Param("partnumber") String partnumber, @Param("footprint") String footprint, @Param("unit") String unit, @Param("manufacture") String manufacture, @Param("materialcode") String materialcode);

    /**
     * 查询入库产品列表
     */
    public List<Storageoutdetail> selectStorageindetailByStorageoutdetailId(String storageoutbillid);

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
