package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.Storagequitdetail;

import java.util.List;

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
     * 查询入库产品列表
     */
    public List<Storagequitdetail> selectStorageindetailByStoragequitbillId(Long storagequitbillid);



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
