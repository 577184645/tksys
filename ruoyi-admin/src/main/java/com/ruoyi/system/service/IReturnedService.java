package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Returned;

/**
 * 回款Service接口
 * 
 * @author ruoyi
 * @date 2020-09-18
 */
public interface IReturnedService 
{
    /**
     * 查询回款
     * 
     * @param returnedId 回款ID
     * @return 回款
     */
    public Returned selectReturnedById(Long returnedId);

    /**
     * 查询回款列表
     * 
     * @param returned 回款
     * @return 回款集合
     */
    public List<Returned> selectReturnedList(Returned returned);

    /**
     * 新增回款
     * 
     * @param returned 回款
     * @return 结果
     */
    public int insertReturned(Returned returned);

    /**
     * 修改回款
     * 
     * @param returned 回款
     * @return 结果
     */
    public int updateReturned(Returned returned);

    /**
     * 批量删除回款
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteReturnedByIds(String ids);

    /**
     * 删除回款信息
     * 
     * @param returnedId 回款ID
     * @return 结果
     */
    public int deleteReturnedById(Long returnedId);
}
