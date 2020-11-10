package com.ruoyi.system.service;

import com.ruoyi.system.domain.Projectreport;

import java.util.List;

/**
 * 项目报备Service接口
 * 
 * @author ruoyi
 * @date 2020-11-05
 */
public interface IProjectreportService 
{
    /**
     * 查询项目报备
     * 
     * @param projectreportId 项目报备ID
     * @return 项目报备
     */
    public Projectreport selectProjectreportById(Long projectreportId);

    /**
     * 查询项目报备列表
     * 
     * @param projectreport 项目报备
     * @return 项目报备集合
     */
    public List<Projectreport> selectProjectreportList(Projectreport projectreport);

    /**
     * 新增项目报备
     * 
     * @param projectreport 项目报备
     * @return 结果
     */
    public int insertProjectreport(Projectreport projectreport);

    /**
     * 修改项目报备
     * 
     * @param projectreport 项目报备
     * @return 结果
     */
    public int updateProjectreport(Projectreport projectreport);

    /**
     * 批量删除项目报备
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteProjectreportByIds(String ids);

    /**
     * 删除项目报备信息
     * 
     * @param projectreportId 项目报备ID
     * @return 结果
     */
    public int deleteProjectreportById(Long projectreportId);
}
