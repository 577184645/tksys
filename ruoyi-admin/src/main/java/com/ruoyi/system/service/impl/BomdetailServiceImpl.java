package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.BomdetailMapper;
import com.ruoyi.system.domain.Bomdetail;
import com.ruoyi.system.service.IBomdetailService;
import com.ruoyi.common.core.text.Convert;

/**
 * bom详细清单Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-07-24
 */
@Service
public class BomdetailServiceImpl implements IBomdetailService 
{
    @Autowired
    private BomdetailMapper bomdetailMapper;

    /**
     * 查询bom详细清单
     * 
     * @param id bom详细清单ID
     * @return bom详细清单
     */
    @Override
    public Bomdetail selectBomdetailById(Integer id)
    {
        return bomdetailMapper.selectBomdetailById(id);
    }

    /**
     * 查询bom详细清单列表
     * 
     * @param bomdetail bom详细清单
     * @return bom详细清单
     */
    @Override
    public List<Bomdetail> selectBomdetailList(Bomdetail bomdetail)
    {
        return bomdetailMapper.selectBomdetailList(bomdetail);
    }

    /**
     * 新增bom详细清单
     * 
     * @param bomdetail bom详细清单
     * @return 结果
     */
    @Override
    public int insertBomdetail(Bomdetail bomdetail)
    {
        return bomdetailMapper.insertBomdetail(bomdetail);
    }

    /**
     * 修改bom详细清单
     * 
     * @param bomdetail bom详细清单
     * @return 结果
     */
    @Override
    public int updateBomdetail(Bomdetail bomdetail)
    {
        return bomdetailMapper.updateBomdetail(bomdetail);
    }

    /**
     * 删除bom详细清单对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteBomdetailByIds(String ids)
    {
        return bomdetailMapper.deleteBomdetailByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除bom详细清单信息
     * 
     * @param id bom详细清单ID
     * @return 结果
     */
    @Override
    public int deleteBomdetailById(Integer id)
    {
        return bomdetailMapper.deleteBomdetailById(id);
    }

    @Override
    public String importBomdetail(List<Bomdetail> bomdetailList) {
        if (StringUtils.isNull(bomdetailList) || bomdetailList.size() == 0)
    {
        throw new BusinessException("导入数据不能为空！");
    }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (Bomdetail bomdetail : bomdetailList)
        {
            try
            {

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
