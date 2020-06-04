package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Project;

/**
 * 项目列表Service接口
 * 
 * @author ruoyi
 * @date 2020-06-04
 */
public interface IProjectService 
{
    /**
     * 查询项目列表
     * 
     * @param id 项目列表ID
     * @return 项目列表
     */
    public Project selectProjectById(Long id);

    /**
     * 查询项目列表列表
     * 
     * @param project 项目列表
     * @return 项目列表集合
     */
    public List<Project> selectProjectList(Project project);

    /**
     * 新增项目列表
     * 
     * @param project 项目列表
     * @return 结果
     */
    public int insertProject(Project project);

    /**
     * 修改项目列表
     * 
     * @param project 项目列表
     * @return 结果
     */
    public int updateProject(Project project);

    /**
     * 批量删除项目列表
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteProjectByIds(String ids);

    /**
     * 删除项目列表信息
     * 
     * @param id 项目列表ID
     * @return 结果
     */
    public int deleteProjectById(Long id);
}
