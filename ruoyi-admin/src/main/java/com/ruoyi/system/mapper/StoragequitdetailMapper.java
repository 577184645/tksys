package com.ruoyi.system.mapper;

import java.util.List;

import com.ruoyi.system.domain.Storage;
import com.ruoyi.system.domain.Storageoutdetail;
import com.ruoyi.system.domain.Storagequitdetail;
import org.apache.ibatis.annotations.Param;

/**
 * 退料Mapper接口
 * 
 * @author ruoyi
 * @date 2020-06-19
 */
public interface StoragequitdetailMapper 
{
    /**
     * 查询退料
     * 
     * @param id 退料ID
     * @return 退料
     */
    public Storagequitdetail selectStoragequitdetailById(Long id);


    /**
     * 查询退料单列表
     *
     * @param storagequitbillid 退料单列表ID
     * @return 退料单列表
     */
    public Storagequitdetail selectStoragequitbilldetailByStoragequitbillId(String storagequitbillid);
    /**
     * 查询入库产品列表
     */
    public List<Storagequitdetail> selectStorageindetailByStoragequitbillId(String storagequitbillid);


    public int updateMaterial(@Param("name") String name, @Param("partnumber") String partnumber, @Param("footprint") String footprint, @Param("unit") String unit, @Param("manufacture") String manufacture, @Param("materialcode") String materialcode);

    /**
     * 查询退料列表
     * 
     * @param storagequitdetail 退料
     * @return 退料集合
     */
    public List<Storagequitdetail> selectStoragequitdetailList(Storagequitdetail storagequitdetail);

    /**
     * 新增退料
     * 
     * @param storagequitdetail 退料
     * @return 结果
     */
    public int insertStoragequitdetail(Storagequitdetail storagequitdetail);

    /**
     * 修改退料
     * 
     * @param storagequitdetail 退料
     * @return 结果
     */
    public int updateStoragequitdetail(Storagequitdetail storagequitdetail);

    /**
     * 删除退料
     * 
     * @param id 退料ID
     * @return 结果
     */
    public int deleteStoragequitdetailById(Long id);

    /**
     * 批量删除退料
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteStoragequitdetailByIds(String[] ids);
}
