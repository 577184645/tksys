package com.ruoyi.system.controller;

import java.util.List;
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
import com.ruoyi.system.domain.Materialdept;
import com.ruoyi.system.service.IMaterialdeptService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 物料部门列表Controller
 * 
 * @author ruoyi
 * @date 2020-06-02
 */
@Controller
@RequestMapping("/system/materialdept")
public class MaterialdeptController extends BaseController
{
    private String prefix = "system/materialdept";

    @Autowired
    private IMaterialdeptService materialdeptService;

    @RequiresPermissions("system:materialdept:view")
    @GetMapping()
    public String materialdept()
    {
        return prefix + "/materialdept";
    }

    /**
     * 查询物料部门列表列表
     */
    @RequiresPermissions("system:materialdept:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Materialdept materialdept)
    {
        startPage();
        List<Materialdept> list = materialdeptService.selectMaterialdeptList(materialdept);
        return getDataTable(list);
    }

    /**
     * 导出物料部门列表列表
     */
    @RequiresPermissions("system:materialdept:export")
    @Log(title = "物料部门列表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Materialdept materialdept)
    {
        List<Materialdept> list = materialdeptService.selectMaterialdeptList(materialdept);
        ExcelUtil<Materialdept> util = new ExcelUtil<Materialdept>(Materialdept.class);
        return util.exportExcel(list, "materialdept");
    }

    /**
     * 新增物料部门列表
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存物料部门列表
     */
    @RequiresPermissions("system:materialdept:add")
    @Log(title = "物料部门列表", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Materialdept materialdept)
    {
        return toAjax(materialdeptService.insertMaterialdept(materialdept));
    }

    /**
     * 修改物料部门列表
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Materialdept materialdept = materialdeptService.selectMaterialdeptById(id);
        mmap.put("materialdept", materialdept);
        return prefix + "/edit";
    }

    /**
     * 修改保存物料部门列表
     */
    @RequiresPermissions("system:materialdept:edit")
    @Log(title = "物料部门列表", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Materialdept materialdept)
    {
        return toAjax(materialdeptService.updateMaterialdept(materialdept));
    }

    /**
     * 删除物料部门列表
     */
    @RequiresPermissions("system:materialdept:remove")
    @Log(title = "物料部门列表", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(materialdeptService.deleteMaterialdeptByIds(ids));
    }
}
