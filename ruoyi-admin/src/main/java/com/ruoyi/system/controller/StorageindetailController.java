package com.ruoyi.system.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.Storageindetail;
import com.ruoyi.system.service.IStorageinbillService;
import com.ruoyi.system.service.IStorageindetailService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 入库产品列表Controller
 * 
 * @author ruoyi
 * @date 2020-06-05
 */
@Controller
@RequestMapping("/system/storageindetail")
public class StorageindetailController extends BaseController
{
    private String prefix = "system/storageindetail";

    @Autowired
    private IStorageindetailService storageindetailService;
    @Autowired
    private IStorageinbillService iStorageinbillService;


    @GetMapping("/{storageinbillId}")
public String storageindetail(@PathVariable("storageinbillId") String storageinbillId,ModelMap mmap)
{
    mmap.put("storageinbillId",storageinbillId);
    return prefix + "/storageindetail";
}


    @GetMapping()
    public String storageindetaillist()
    {

        return prefix + "/storageindetail";
    }

    /**
     * 查询入库产品列表列表
     */

    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Storageindetail storageindetail)
    {
        startPage();
        List<Storageindetail> list = storageindetailService.selectStorageindetailList(storageindetail);
        return getDataTable(list);
    }

    /**
     * 导出入库产品列表列表
     */

    @Log(title = "入库产品列表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Storageindetail storageindetail)
    {
        List<Storageindetail> list = storageindetailService.selectStorageindetailList(storageindetail);
        ExcelUtil<Storageindetail> util = new ExcelUtil<Storageindetail>(Storageindetail.class);
        return util.exportExcel(list, "storageindetail");
    }

    /**
     * 新增入库产品列表
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }




    /**
     * 新增保存入库产品列表
     */
    @RequiresPermissions("system:storageindetail:add")
    @Log(title = "入库产品列表", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Storageindetail storageindetail)
    {
        return toAjax(storageindetailService.insertStorageindetail(storageindetail));
    }

    /**
     * 修改入库产品列表
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Storageindetail storageindetail = storageindetailService.selectStorageindetailById(id);
        mmap.put("storageindetail", storageindetail);
        return prefix + "/edit";
    }




}
