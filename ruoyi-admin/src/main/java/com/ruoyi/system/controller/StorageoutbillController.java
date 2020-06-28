package com.ruoyi.system.controller;

import java.util.List;

import com.ruoyi.system.domain.Storageinbill;
import com.ruoyi.system.domain.Storageindetail;
import com.ruoyi.system.domain.Storageoutdetail;
import com.ruoyi.system.service.IStorageoutdetailService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.Storageoutbill;
import com.ruoyi.system.service.IStorageoutbillService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 出库单列表Controller
 * 
 * @author ruoyi
 * @date 2020-06-09
 */
@Controller
@RequestMapping("/system/storageoutbill")
public class StorageoutbillController extends BaseController
{
    private String prefix = "system/storageoutbill";

    @Autowired
    private IStorageoutbillService storageoutbillService;
    @Autowired
    private IStorageoutdetailService storageoutdetailService;

    @PostMapping("/getstorageoutid")
    @ResponseBody
    public Integer getstorageoutid(Storageoutbill storageoutbill){
        return       storageoutbillService.selectStorageoutbillList(storageoutbill).size()+1;
    }



    @GetMapping()
    public String storageoutbill()
    {
        return prefix + "/storageoutbill";
    }

    /**
     * 查询出库单列表列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Storageoutbill storageoutbill)
    {
        startPage();
        List<Storageoutbill> list = storageoutbillService.selectStorageoutbillList(storageoutbill);
        return getDataTable(list);
    }

    /**
     * 导出出库单列表列表
     */

    @Log(title = "出库单列表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Storageoutbill storageoutbill)
    {
        List<Storageoutbill> list = storageoutbillService.selectStorageoutbillList(storageoutbill);
        ExcelUtil<Storageoutbill> util = new ExcelUtil<Storageoutbill>(Storageoutbill.class);
        return util.exportExcel(list, "storageoutbill");
    }

    /**
     * 新增出库单列表
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存出库单列表
     */
    @RequiresPermissions("system:storageoutbill:add")
    @Log(title = "出库单列表", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Storageoutbill storageoutbill)
    {
        return toAjax(storageoutbillService.insertStorageoutbill(storageoutbill));
    }

    /**
     * 修改出库单列表
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Storageoutbill storageoutbill = storageoutbillService.selectStorageoutbillById(id);
        mmap.put("storageoutbill", storageoutbill);
        return prefix + "/edit";
    }

    /**
     * 修改保存出库单列表
     */
    @RequiresPermissions("system:storageoutbill:edit")
    @Log(title = "出库单列表", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Storageoutbill storageoutbill)
    {
        return toAjax(storageoutbillService.updateStorageoutbill(storageoutbill));
    }

    /**
     * 删除出库单列表
     */
    @RequiresPermissions("system:storageoutbill:remove")
    @Log(title = "出库单列表", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(storageoutbillService.deleteStorageoutbillByIds(ids));
    }


    /**
     * 打印入库产品列表
     */
    @GetMapping("/print/{id}")
    public String print(@PathVariable("id") Long id, ModelMap mmap)
    {
        Storageoutbill storageoutbill = storageoutbillService.selectStorageoutbillById(id);
        mmap.put("storageoutbill", storageoutbill);
        List<Storageoutdetail> storageoutdetails = storageoutdetailService.selectStorageindetailByStorageoutdetailId(storageoutbill.getStorageoutid());
        mmap.put("storageoutdetails", storageoutdetails);
        return prefix + "/print";
    }

    @Log(title = "出库单红冲", businessType = BusinessType.DELETE)
    @PostMapping("/reddashed")
    @ResponseBody
    public AjaxResult reddashed(@RequestParam("id") Long id){
        if(storageoutbillService.selectStorageoutbillById(id).getDelStatus()==2){
            return AjaxResult.warn("操作失败！该出库单已红冲");
        }


        if(storageoutbillService.reddashed(id)>0){
            return AjaxResult.warn("操作成功");
        }
        return    AjaxResult.warn("操作失败,请联系管理员")   ;
    }



}
