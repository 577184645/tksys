package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Storagequitbill;

/**
 * 退料单列表Service接口
 * 
 * @author ruoyi
 * @date 2020-06-18
 */
public interface IStoragequitbillService 
{
    /**
     * 查询退料单列表
     * 
     * @param id 退料单列表ID
     * @return 退料单列表
     */
    public Storagequitbill selectStoragequitbillById(Long id);

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
     * 批量删除退料单列表
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteStoragequitbillByIds(String ids);

    /**
     * 删除退料单列表信息
     * 
     * @param id 退料单列表ID
     * @return 结果
     */
    public int deleteStoragequitbillById(Long id);
}
