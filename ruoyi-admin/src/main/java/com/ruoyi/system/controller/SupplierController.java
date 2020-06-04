package com.ruoyi.system.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.Supplier;
import com.ruoyi.system.service.ISupplierService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 供应商列表Controller
 * 
 * @author ruoyi
 * @date 2020-06-01
 */
@Controller
@RequestMapping("/system/supplier")
public class SupplierController extends BaseController
{
    private String prefix = "system/supplier";

    @Autowired
    private ISupplierService supplierService;

    @RequiresPermissions("system:supplier:view")
    @GetMapping()
    public String supplier()
    {
        return prefix + "/supplier";
    }


    @RequestMapping("/findsupplier")
    @ResponseBody
    public List<Supplier> findsupplier( ){
       return     supplierService.findListSupplier();
  }
    /**
     * 查询供应商列表列表
     */
    @RequiresPermissions("system:supplier:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Supplier supplier)
    {
        startPage();
        List<Supplier> list = supplierService.selectSupplierList(supplier);
        return getDataTable(list);
    }

    /**
     * 导出供应商列表列表
     */
    @RequiresPermissions("system:supplier:export")
    @Log(title = "供应商列表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Supplier supplier)
    {
        List<Supplier> list = supplierService.selectSupplierList(supplier);
        ExcelUtil<Supplier> util = new ExcelUtil<Supplier>(Supplier.class);
        return util.exportExcel(list, "supplier");
    }

    /**
     * 新增供应商列表
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存供应商列表
     */
    @RequiresPermissions("system:supplier:add")
    @Log(title = "供应商列表", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Supplier supplier)
    {
        return toAjax(supplierService.insertSupplier(supplier));
    }

    /**
     * 修改供应商列表
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Supplier supplier = supplierService.selectSupplierById(id);
        mmap.put("supplier", supplier);
        return prefix + "/edit";
    }

    /**
     * 修改保存供应商列表
     */
    @RequiresPermissions("system:supplier:edit")
    @Log(title = "供应商列表", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Supplier supplier)
    {
        return toAjax(supplierService.updateSupplier(supplier));
    }

    /**
     * 删除供应商列表
     */
    @RequiresPermissions("system:supplier:remove")
    @Log(title = "供应商列表", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(supplierService.deleteSupplierByIds(ids));
    }
}
