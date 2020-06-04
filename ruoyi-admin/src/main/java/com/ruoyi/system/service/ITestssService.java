package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Testss;

/**
 * 测试Service接口
 * 
 * @author ruoyi
 * @date 2020-06-01
 */
public interface ITestssService 
{
    /**
     * 查询测试
     * 
     * @param id 测试ID
     * @return 测试
     */
    public Testss selectTestssById(Long id);

    /**
     * 查询测试列表
     * 
     * @param testss 测试
     * @return 测试集合
     */
    public List<Testss> selectTestssList(Testss testss);

    /**
     * 新增测试
     * 
     * @param testss 测试
     * @return 结果
     */
    public int insertTestss(Testss testss);

    /**
     * 修改测试
     * 
     * @param testss 测试
     * @return 结果
     */
    public int updateTestss(Testss testss);

    /**
     * 批量删除测试
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTestssByIds(String ids);

    /**
     * 删除测试信息
     * 
     * @param id 测试ID
     * @return 结果
     */
    public int deleteTestssById(Long id);


    /**
     * 导入用户数据
     *
     * @param sellDetailList 商品数据列表
     * @return 结果
     */
    public String importUser(List<Testss> sellDetailList);
}
