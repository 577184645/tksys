package com.ruoyi.system.controller;

import java.util.List;

import com.ruoyi.system.domain.Supplier;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.Project;
import com.ruoyi.system.service.IProjectService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 项目列表Controller
 * 
 * @author ruoyi
 * @date 2020-06-04
 */
@Controller
@RequestMapping("/system/project")
public class ProjectController extends BaseController
{
    private String prefix = "system/project";

    @Autowired
    private IProjectService projectService;


    @RequestMapping("/findproject")
    @ResponseBody
    public List<Project> findproject( ){
        return     projectService.selectProjectList(null);
    }

    @RequiresPermissions("system:project:view")
    @GetMapping()
    public String project()
    {
        return prefix + "/project";
    }

    /**
     * 查询项目列表列表
     */
    @RequiresPermissions("system:project:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Project project)
    {
        startPage();
        List<Project> list = projectService.selectProjectList(project);
        return getDataTable(list);
    }

    /**
     * 导出项目列表列表
     */
    @RequiresPermissions("system:project:export")
    @Log(title = "项目列表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Project project)
    {
        List<Project> list = projectService.selectProjectList(project);
        ExcelUtil<Project> util = new ExcelUtil<Project>(Project.class);
        return util.exportExcel(list, "project");
    }

    /**
     * 新增项目列表
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存项目列表
     */
    @RequiresPermissions("system:project:add")
    @Log(title = "项目列表", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Project project)
    {
        return toAjax(projectService.insertProject(project));
    }

    /**
     * 修改项目列表
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Project project = projectService.selectProjectById(id);
        mmap.put("project", project);
        return prefix + "/edit";
    }

    /**
     * 修改保存项目列表
     */
    @RequiresPermissions("system:project:edit")
    @Log(title = "项目列表", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Project project)
    {
        return toAjax(projectService.updateProject(project));
    }

    /**
     * 删除项目列表
     */
    @RequiresPermissions("system:project:remove")
    @Log(title = "项目列表", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(projectService.deleteProjectByIds(ids));
    }
}
