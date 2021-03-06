package com.ruoyi.system.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.Storageoutdetail;
import com.ruoyi.system.service.IStorageoutbillService;
import com.ruoyi.system.service.IStorageoutdetailService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 出库产品列表Controller
 * 
 * @author ruoyi
 * @date 2020-06-09
 */
@Controller
@RequestMapping("/system/storageoutdetail")
public class StorageoutdetailController extends BaseController
{
    private String prefix = "system/storageoutdetail";

    @Autowired
    private IStorageoutdetailService storageoutdetailService;
    @Autowired
    private IStorageoutbillService iStorageoutbillService;


    @GetMapping("/{storageoutbillId}")
    public String storageoutdetail(@PathVariable("storageoutbillId") Long storageoutbillId,ModelMap mmap)
    {
        mmap.put("storageoutbillId",storageoutbillId);
        return prefix + "/storageoutdetail";
    }

    @GetMapping()
    public String storageoutdetaillist()
    {
        return prefix + "/storageoutdetail";
    }

    /**
     * 查询出库产品列表列表
     */

    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Storageoutdetail storageoutdetail)
    {
        startPage();
        List<Storageoutdetail> list = storageoutdetailService.selectStorageoutdetailList(storageoutdetail);
        return getDataTable(list);
    }

    /**
     * 导出出库产品列表列表
     */

    @Log(title = "出库产品列表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Storageoutdetail storageoutdetail)
    {
        List<Storageoutdetail> list = storageoutdetailService.selectStorageoutdetailList(storageoutdetail);
        ExcelUtil<Storageoutdetail> util = new ExcelUtil<Storageoutdetail>(Storageoutdetail.class);
        return util.exportExcel(list, "storageoutdetail");
    }

    /**
     * 新增出库产品列表
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存出库产品列表
     */
    @RequiresPermissions("system:storageoutdetail:add")
    @Log(title = "出库产品列表", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Storageoutdetail storageoutdetail)
    {
        return toAjax(storageoutdetailService.insertStorageoutdetail(storageoutdetail));
    }

    /**
     * 修改出库产品列表
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Storageoutdetail storageoutdetail = storageoutdetailService.selectStorageoutdetailById(id);
        mmap.put("storageoutdetail", storageoutdetail);
        return prefix + "/edit";
    }



    /**
     * 删除出库产品列表
     */
    @RequiresPermissions("system:storageoutdetail:remove")
    @Log(title = "出库产品列表", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(storageoutdetailService.deleteStorageoutdetailByIds(ids));
    }
}
