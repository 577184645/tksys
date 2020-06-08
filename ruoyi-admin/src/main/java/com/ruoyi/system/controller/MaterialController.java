package com.ruoyi.system.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.system.domain.Materialtype;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.*;
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
import com.ruoyi.system.domain.Material;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 物料列表Controller
 * 
 * @author ruoyi
 * @date 2020-06-01
 */
@Controller
@RequestMapping("/system/material")
public class MaterialController extends BaseController
{
    private String prefix = "system/material";

    @Autowired
    private IMaterialService materialService;

    @Autowired
    private ISysUserService iSysUserService;
    @Autowired
    private IMaterialtypeService iMaterialtypeService;
    @Autowired
    private IMaterialdeptService imaterialdeptService;
    @Autowired
    private ISupplierService iSupplierService;



    @RequestMapping("/find")
    @ResponseBody
    public Map<String ,Object> find(Material material)
{
    Map<String,Object> map=new HashMap<>();
    List<Material> list = materialService.selectMaterialList(material);
    map.put("rows",list);
    map.put("total",list.size());
    return map;

}

    @RequiresPermissions("system:material:view")
    @GetMapping()
    public String material()
    {
 //    mmap.put("userList",iSysUserService.selectUserList(null));

        return prefix + "/material";
    }




    /**
     * 查询物料列表列表
     */
    @RequiresPermissions("system:material:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Material material)
    {

        List<Material> list = materialService.selectMaterialList(material);
        startPage();
        return getDataTable(list);
    }

    /**
     * 查询物料列表列表
     */





    /**
     * 导出物料列表列表
     */
    @RequiresPermissions("system:material:export")
    @Log(title = "物料列表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Material material)
    {
        List<Material> list = materialService.selectMaterialList(material);
        ExcelUtil<Material> util = new ExcelUtil<Material>(Material.class);
        return util.exportExcel(list, "material");
    }

    /**
     * 新增物料列表
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
        List<Materialtype> materialtypes = iMaterialtypeService.selectMaterialtypeList(null);
        materialtypes.remove(0);
        mmap.put("materialtypeList",materialtypes);
        mmap.put("materialdeptList",imaterialdeptService.selectMaterialdeptList(null));
        mmap.put("supplierList",iSupplierService.findListSupplier());
        mmap.put("userList",iSysUserService.findList());
        return prefix + "/add";
    }

    /**
     * 新增保存物料列表
     */
    @RequiresPermissions("system:material:add")
    @Log(title = "物料列表", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Material material)
    {
        return toAjax(materialService.insertMaterial(material));
    }

    /**
     * 修改物料列表
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
        Material material = materialService.selectMaterialById(id);
        mmap.put("material", material);
        mmap.put("userList",iSysUserService.findList());
        mmap.put("supplierList",iSupplierService.findListSupplier());
        return prefix + "/edit";
    }

    /**
     * 修改保存物料列表
     */
    @RequiresPermissions("system:material:edit")
    @Log(title = "物料列表", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Material material)
    {
        return toAjax(materialService.updateMaterial(material));
    }

    /**
     * 删除物料列表
     */
    @RequiresPermissions("system:material:remove")
    @Log(title = "物料列表", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(materialService.deleteMaterialByIds(ids));
    }
}
