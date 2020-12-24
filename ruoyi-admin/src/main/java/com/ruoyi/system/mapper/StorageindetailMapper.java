package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.Storageindetail;
import org.apache.ibatis.annotations.Param;

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


    /**
     * 查询入库产品列表
     */
    public List<Storageindetail> selectStorageindetailByStorageinbillSid(Long storageinbillid);


    public int updateMaterial(@Param("name") String name, @Param("partnumber") String partnumber, @Param("footprint") String footprint, @Param("unit") String unit, @Param("manufacture") String manufacture, @Param("materialcode") String materialcode);
    /**
     * 查询入库产品列表
     */
    public List<Storageindetail> selectStorageindetailByStorageinbillId(String storageinbillid);
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

    /**
     * 批量删除入库产品列表
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteStorageindetailByIds(String[] ids);
}
