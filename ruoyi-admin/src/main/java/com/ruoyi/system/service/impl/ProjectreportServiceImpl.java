package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.ProjectreportMapper;
import com.ruoyi.system.domain.Projectreport;
import com.ruoyi.system.service.IProjectreportService;
import com.ruoyi.common.core.text.Convert;

/**
 * 项目报备Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-10-29
 */
@Service
public class ProjectreportServiceImpl implements IProjectreportService 
{
    @Autowired
    private ProjectreportMapper projectreportMapper;

    /**
     * 查询项目报备
     * 
     * @param projectreportId 项目报备ID
     * @return 项目报备
     */
    @Override
    public Projectreport selectProjectreportById(Long projectreportId)
    {
        return projectreportMapper.selectProjectreportById(projectreportId);
    }

    /**
     * 查询项目报备列表
     * 
     * @param projectreport 项目报备
     * @return 项目报备
     */
    @Override
    public List<Projectreport> selectProjectreportList(Projectreport projectreport)
    {
        return projectreportMapper.selectProjectreportList(projectreport);
    }

    /**
     * 新增项目报备
     * 
     * @param projectreport 项目报备
     * @return 结果
     */
    @Override
    public int insertProjectreport(Projectreport projectreport)
    {
        return projectreportMapper.insertProjectreport(projectreport);
    }

    /**
     * 修改项目报备
     * 
     * @param projectreport 项目报备
     * @return 结果
     */
    @Override
    public int updateProjectreport(Projectreport projectreport)
    {
        return projectreportMapper.updateProjectreport(projectreport);
    }

    /**
     * 删除项目报备对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteProjectreportByIds(String ids)
    {
        return projectreportMapper.deleteProjectreportByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除项目报备信息
     * 
     * @param projectreportId 项目报备ID
     * @return 结果
     */
    @Override
    public int deleteProjectreportById(Long projectreportId)
    {
        return projectreportMapper.deleteProjectreportById(projectreportId);
    }
}
