package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.Material;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.QrodeMapper;
import com.ruoyi.system.domain.Qrode;
import com.ruoyi.system.service.IQrodeService;
import com.ruoyi.common.core.text.Convert;

/**
 * 二维码列表Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-07-22
 */
@Service
public class QrodeServiceImpl implements IQrodeService 
{
    @Autowired
    private QrodeMapper qrodeMapper;

    @Override
    public String importQrode(List<Qrode> qrodes) {
        if (StringUtils.isNull(qrodes) || qrodes.size() == 0) {
            throw new BusinessException("导入数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (Qrode qrode : qrodes) {
            try
            {
                qrodeMapper.insertQrode(qrode);
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

    /**
     * 查询二维码列表
     * 
     * @param id 二维码列表ID
     * @return 二维码列表
     */
    @Override
    public Qrode selectQrodeById(Long id)
    {
        return qrodeMapper.selectQrodeById(id);
    }

    @Override
    public Qrode selectQrodeBySerialnumber(String serialnumber) {
        return qrodeMapper.selectQrodeBySerialnumber(serialnumber);
    }

    /**
     * 查询二维码列表列表
     * 
     * @param qrode 二维码列表
     * @return 二维码列表
     */
    @Override
    public List<Qrode> selectQrodeList(Qrode qrode)
    {
        return qrodeMapper.selectQrodeList(qrode);
    }

    /**
     * 新增二维码列表
     * 
     * @param qrode 二维码列表
     * @return 结果
     */
    @Override
    public int insertQrode(Qrode qrode)
    {
        return qrodeMapper.insertQrode(qrode);
    }

    /**
     * 修改二维码列表
     * 
     * @param qrode 二维码列表
     * @return 结果
     */
    @Override
    public int updateQrode(Qrode qrode)
    {
        return qrodeMapper.updateQrode(qrode);
    }

    /**
     * 删除二维码列表对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteQrodeByIds(String ids)
    {
        return qrodeMapper.deleteQrodeByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除二维码列表信息
     * 
     * @param id 二维码列表ID
     * @return 结果
     */
    @Override
    public int deleteQrodeById(Long id)
    {
        return qrodeMapper.deleteQrodeById(id);
    }
}
