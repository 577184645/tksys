package com.ruoyi.system.service;

import com.ruoyi.system.domain.Bom;

import java.util.List;

/**
 * bom列表Service接口
 * 
 * @author ruoyi
 * @date 2020-07-24
 */
public interface IBomService 
{
    /**
     * 查询bom列表
     * 
     * @param id bom列表ID
     * @return bom列表
     */
    public Bom selectBomById(Long id);

    /**
     * 查询bom列表列表
     * 
     * @param bom bom列表
     * @return bom列表集合
     */
    public List<Bom> selectBomList(Bom bom);

    /**
     * 新增bom列表
     * 
     * @param bom bomList
     * @return 结果
     */

    public int addBom(String bomList,Bom bom);


    /**
     * 批量删除bom列表
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBomByIds(String ids);

    /**
     * 删除bom列表信息
     * 
     * @param id bom列表ID
     * @return 结果
     */
    public int deleteBomById(Long id);

    boolean refresh(String ids);
}
