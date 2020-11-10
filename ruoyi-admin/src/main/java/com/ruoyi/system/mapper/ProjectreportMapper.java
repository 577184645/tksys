package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Projectreport;

/**
 * 项目报备Mapper接口
 * 
 * @author ruoyi
 * @date 2020-11-05
 */
public interface ProjectreportMapper 
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
     * 删除项目报备
     * 
     * @param projectreportId 项目报备ID
     * @return 结果
     */
    public int deleteProjectreportById(Long projectreportId);

    /**
     * 批量删除项目报备
     * 
     * @param projectreportIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteProjectreportByIds(String[] projectreportIds);
}
