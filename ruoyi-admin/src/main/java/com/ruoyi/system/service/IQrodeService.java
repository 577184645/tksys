package com.ruoyi.system.service;

import java.util.List;

import com.ruoyi.system.domain.Material;
import com.ruoyi.system.domain.Qrode;

/**
 * 二维码列表Service接口
 * 
 * @author ruoyi
 * @date 2020-07-22
 */
public interface IQrodeService 
{




    /**
     * 导入物料数据
     *
     * @param qrodes 物料数据列表
     * @return 结果
     */
    public String importQrode(List<Qrode> qrodes);

    /**
     * 查询二维码列表
     * 
     * @param id 二维码列表ID
     * @return 二维码列表
     */
    public Qrode selectQrodeById(Long id);


    public Qrode selectQrodeBySerialnumber(String serialnumber);


    /**
     * 查询二维码列表列表
     * 
     * @param qrode 二维码列表
     * @return 二维码列表集合
     */
    public List<Qrode> selectQrodeList(Qrode qrode);

    /**
     * 新增二维码列表
     * 
     * @param qrode 二维码列表
     * @return 结果
     */
    public int insertQrode(Qrode qrode);

    /**
     * 修改二维码列表
     * 
     * @param qrode 二维码列表
     * @return 结果
     */
    public int updateQrode(Qrode qrode);

    /**
     * 批量删除二维码列表
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteQrodeByIds(String ids);

    /**
     * 删除二维码列表信息
     * 
     * @param id 二维码列表ID
     * @return 结果
     */
    public int deleteQrodeById(Long id);
}
