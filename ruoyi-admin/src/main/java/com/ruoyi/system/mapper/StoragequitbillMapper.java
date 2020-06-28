package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Storagequitbill;
import com.ruoyi.system.domain.Storagequitdetail;

/**
 * 退料单列表Mapper接口
 * 
 * @author ruoyi
 * @date 2020-06-18
 */
public interface StoragequitbillMapper 
{
    /**
     * 查询退料单列表
     * 
     * @param id 退料单列表ID
     * @return 退料单列表
     */
    public Storagequitbill selectStoragequitbillById(Long id);


    /**
     * 查询退料单列表
     *
     * @param storagequitbillid 退料单列表ID
     * @return 退料单列表
     */
    public Storagequitbill selectStoragequitbillByStoragequitbillId(String storagequitbillid);

    /**
     * 查询退料单列表列表
     * 
     * @param storagequitbill 退料单列表
     * @return 退料单列表集合
     */
    public List<Storagequitbill> selectStoragequitbillList(Storagequitbill storagequitbill);

    /**
     * 新增退料单列表
     * 
     * @param storagequitbill 退料单列表
     * @return 结果
     */
    public int insertStoragequitbill(Storagequitbill storagequitbill);

    /**
     * 修改退料单列表
     * 
     * @param storagequitbill 退料单列表
     * @return 结果
     */
    public int updateStoragequitbill(Storagequitbill storagequitbill);

    /**
     * 删除退料单列表
     * 
     * @param id 退料单列表ID
     * @return 结果
     */
    public int deleteStoragequitbillById(Long id);

    /**
     * 批量删除退料单列表
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteStoragequitbillByIds(String[] ids);


    /**
     * 修改删除状态
     *
     * @param id 入库单列表ID
     * @return 入库单列表
     */
    public int updatedelStatus(Long id);
}
