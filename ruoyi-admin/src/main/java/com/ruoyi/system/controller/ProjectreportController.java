package com.ruoyi.system.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.Projectreport;
import com.ruoyi.system.service.IProjectreportService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 项目报备Controller
 * 
 * @author ruoyi
 * @date 2020-11-05
 */
@Controller
@RequestMapping("/system/projectreport")
public class ProjectreportController extends BaseController
{
    private String prefix = "system/projectreport";

    @Autowired
    private IProjectreportService projectreportService;

    @RequiresPermissions("system:projectreport:view")
    @GetMapping()
    public String projectreport( ModelMap map)
    {
       map.put("projectreportType",0);
        return prefix + "/projectreport";
    }

    @RequiresPermissions("system:projectreport:view")
    @GetMapping("/honeywell")
    public String honeywell( ModelMap map)
    {
        map.put("projectreportType",1);
        return prefix + "/projectreporthoneywell";
    }


    /**
     * 查询项目报备列表
     */
    @RequiresPermissions("system:projectreport:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Projectreport projectreport)
    {
        startPage();
        List<Projectreport> list = projectreportService.selectProjectreportList(projectreport);
        return getDataTable(list);
    }

    /**
     * 导出项目报备列表
     */
    @RequiresPermissions("system:projectreport:export")
    @Log(title = "项目报备", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Projectreport projectreport)
    {
        List<Projectreport> list = projectreportService.selectProjectreportList(projectreport);
        ExcelUtil<Projectreport> util = new ExcelUtil<Projectreport>(Projectreport.class);
        return util.exportExcel(list, "projectreport");
    }

    /**
     * 新增项目报备
     */
    @GetMapping("/add")
    public String add(ModelMap map )
    {
        map.put("projectreportType",0);
        return prefix + "/add";
    }

    /**
     * 新增项目报备
     */
    @GetMapping("/honeywelladd")
    public String honeywelladd(ModelMap map )
    {
        map.put("projectreportType",1);
        return prefix + "/add";
    }

    /**
     * 新增保存项目报备
     */
    @RequiresPermissions("system:projectreport:add")
    @Log(title = "项目报备", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Projectreport projectreport)
    {
        return toAjax(projectreportService.insertProjectreport(projectreport));
    }

    /**
     * 修改项目报备
     */
    @GetMapping("/edit/{projectreportId}")
    public String edit(@PathVariable("projectreportId") Long projectreportId, ModelMap mmap)
    {
        Projectreport projectreport = projectreportService.selectProjectreportById(projectreportId);
        mmap.put("projectreport", projectreport);
        return prefix + "/edit";
    }
    @GetMapping("/confirm/{projectreportId}")
    public String confirm(@PathVariable("projectreportId") Long projectreportId, ModelMap mmap)
    {
        Projectreport projectreport = projectreportService.selectProjectreportById(projectreportId);
        mmap.put("projectreport", projectreport);
        return prefix + "/confirm";
    }

    /**
     * 修改保存项目报备
     */
    @RequiresPermissions("system:projectreport:confirm")
    @Log(title = "项目报备", businessType = BusinessType.UPDATE)
    @PostMapping("/confirm")
    @ResponseBody
    public AjaxResult confirm(Projectreport projectreport)
    {
        return toAjax(projectreportService.updateProjectreport(projectreport));
    }

    /**
     * 修改保存项目报备
     */
    @RequiresPermissions("system:projectreport:edit")
    @Log(title = "项目报备", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Projectreport projectreport)
    {
        return toAjax(projectreportService.updateProjectreport(projectreport));
    }

    /**
     * 删除项目报备
     */
    @RequiresPermissions("system:projectreport:remove")
    @Log(title = "项目报备", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(projectreportService.deleteProjectreportByIds(ids));
    }
}
