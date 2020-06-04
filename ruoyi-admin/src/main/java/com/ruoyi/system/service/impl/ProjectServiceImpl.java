package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.ProjectMapper;
import com.ruoyi.system.domain.Project;
import com.ruoyi.system.service.IProjectService;
import com.ruoyi.common.core.text.Convert;

/**
 * 项目列表Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-06-04
 */
@Service
public class ProjectServiceImpl implements IProjectService 
{
    @Autowired
    private ProjectMapper projectMapper;

    /**
     * 查询项目列表
     * 
     * @param id 项目列表ID
     * @return 项目列表
     */
    @Override
    public Project selectProjectById(Long id)
    {
        return projectMapper.selectProjectById(id);
    }

    /**
     * 查询项目列表列表
     * 
     * @param project 项目列表
     * @return 项目列表
     */
    @Override
    public List<Project> selectProjectList(Project project)
    {
        return projectMapper.selectProjectList(project);
    }

    /**
     * 新增项目列表
     * 
     * @param project 项目列表
     * @return 结果
     */
    @Override
    public int insertProject(Project project)
    {
        return projectMapper.insertProject(project);
    }

    /**
     * 修改项目列表
     * 
     * @param project 项目列表
     * @return 结果
     */
    @Override
    public int updateProject(Project project)
    {
        return projectMapper.updateProject(project);
    }

    /**
     * 删除项目列表对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteProjectByIds(String ids)
    {
        return projectMapper.deleteProjectByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除项目列表信息
     * 
     * @param id 项目列表ID
     * @return 结果
     */
    @Override
    public int deleteProjectById(Long id)
    {
        return projectMapper.deleteProjectById(id);
    }
}
