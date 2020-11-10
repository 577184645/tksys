package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.system.domain.SalesContract;
import com.ruoyi.system.mapper.SalesContractMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.ReturnedMapper;
import com.ruoyi.system.domain.Returned;
import com.ruoyi.system.service.IReturnedService;
import com.ruoyi.common.core.text.Convert;

/**
 * 回款Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-09-18
 */
@Service
public class ReturnedServiceImpl implements IReturnedService 
{
    @Autowired
    private ReturnedMapper returnedMapper;
    @Autowired
    private SalesContractMapper salesContractMapper;

    /**
     * 查询回款
     * 
     * @param returnedId 回款ID
     * @return 回款
     */
    @Override
    public Returned selectReturnedById(Long returnedId)
    {
        return returnedMapper.selectReturnedById(returnedId);
    }

    @Override
    public Double yearsummoney(String yyyy) {
        return returnedMapper.yearsummoney(yyyy);
    }

    /**
     * 查询回款列表
     * 
     * @param returned 回款
     * @return 回款
     */
    @Override
    public List<Returned> selectReturnedList(Returned returned)
    {
        return returnedMapper.selectReturnedList(returned);
    }

    /**
     * 新增回款
     * 
     * @param returned 回款
     * @return 结果
     */
    @Override
    public int insertReturned(Returned returned)
    {
        return returnedMapper.insertReturned(returned);
    }

    /**
     * 修改回款
     * 
     * @param returned 回款
     * @return 结果
     */
    @Override
    public int updateReturned(Returned returned)
    {
        return returnedMapper.updateReturned(returned);
    }

    /**
     * 删除回款对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteReturnedByIds(String ids)
    {
        return returnedMapper.deleteReturnedByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除回款信息
     * 
     * @param returnedId 回款ID
     * @return 结果
     */
    @Override
    public int deleteReturnedById(Long returnedId)
    {
        return returnedMapper.deleteReturnedById(returnedId);
    }
}
