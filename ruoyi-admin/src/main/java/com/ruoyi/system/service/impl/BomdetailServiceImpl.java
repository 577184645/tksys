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


    
}
