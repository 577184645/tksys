package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.TestssMapper;
import com.ruoyi.system.domain.Testss;
import com.ruoyi.system.service.ITestssService;
import com.ruoyi.common.core.text.Convert;

/**
 * 测试Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-06-01
 */
@Service
public class TestssServiceImpl implements ITestssService 
{
    @Autowired
    private TestssMapper testssMapper;

    /**
     * 查询测试
     * 
     * @param id 测试ID
     * @return 测试
     */
    @Override
    public Testss selectTestssById(Long id)
    {
        return testssMapper.selectTestssById(id);
    }

    /**
     * 查询测试列表
     * 
     * @param testss 测试
     * @return 测试
     */
    @Override
    public List<Testss> selectTestssList(Testss testss)
    {
        return testssMapper.selectTestssList(testss);
    }

    /**
     * 新增测试
     * 
     * @param testss 测试
     * @return 结果
     */
    @Override
    public int insertTestss(Testss testss)
    {
        return testssMapper.insertTestss(testss);
    }

    /**
     * 修改测试
     * 
     * @param testss 测试
     * @return 结果
     */
    @Override
    public int updateTestss(Testss testss)
    {
        return testssMapper.updateTestss(testss);
    }

    /**
     * 删除测试对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTestssByIds(String ids)
    {
        return testssMapper.deleteTestssByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除测试信息
     * 
     * @param id 测试ID
     * @return 结果
     */
    @Override
    public int deleteTestssById(Long id)
    {
        return testssMapper.deleteTestssById(id);
    }

    @Override
    public String importUser(List<Testss> sellDetailList) {

        if (StringUtils.isNull(sellDetailList) || sellDetailList.size() == 0)
        {
            throw new BusinessException("导入数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (Testss sellDetail : sellDetailList)
        {
            try
            {
                insertTestss(sellDetail);
                successNum++;
            }
            catch (Exception e)
            {
                failureNum++;
            }
        }
        if (failureNum > 0)
        {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new BusinessException(failureMsg.toString());
        }
        else
        {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();

    }
}
