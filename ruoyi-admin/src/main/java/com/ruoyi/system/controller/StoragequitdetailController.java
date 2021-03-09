package com.ruoyi.system.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.Storagequitdetail;
import com.ruoyi.system.service.IStoragequitbillService;
import com.ruoyi.system.service.IStoragequitdetailService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 退料Controller
 * 
 * @author ruoyi
 * @date 2020-06-19
 */
@Controller
@RequestMapping("/system/storagequitdetail")
public class StoragequitdetailController extends BaseController
{
    private String prefix = "system/storagequitdetail";

    @Autowired
    private IStoragequitdetailService storagequitdetailService;
    @Autowired
    private IStoragequitbillService iStoragequitbillService;


    @GetMapping("/{storagequitbillId}")
    public String storagequitdetail(@PathVariable("storagequitbillId") String storagequitbillId,ModelMap mmap)
    {
        mmap.put("storagequitbillId",storagequitbillId);
        return prefix + "/storagequitdetail";
    }


    @GetMapping()
    public String storagequitdetail()
    {
        return prefix + "/storagequitdetail";
    }

    /**
     * 查询退料列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Storagequitdetail storagequitdetail)
    {
        startPage();
        List<Storagequitdetail> list = storagequitdetailService.selectStoragequitdetailList(storagequitdetail);
        return getDataTable(list);
    }

    /**
     * 导出退料列表
     */
    @RequiresPermissions("system:storagequitdetail:export")
    @Log(title = "退料", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Storagequitdetail storagequitdetail)
    {
        List<Storagequitdetail> list = storagequitdetailService.selectStoragequitdetailList(storagequitdetail);
        ExcelUtil<Storagequitdetail> util = new ExcelUtil<Storagequitdetail>(Storagequitdetail.class);
        return util.exportExcel(list, "storagequitdetail");
    }

    /**
     * 新增退料
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存退料
     */
    @RequiresPermissions("system:storagequitdetail:add")
    @Log(title = "退料", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Storagequitdetail storagequitdetail)
    {
        return toAjax(storagequitdetailService.insertStoragequitdetail(storagequitdetail));
    }

    /**
     * 修改退料
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Storagequitdetail storagequitdetail = storagequitdetailService.selectStoragequitdetailById(id);
        mmap.put("storagequitdetail", storagequitdetail);
        return prefix + "/edit";
    }

    /**
     * 修改保存退料
     */
    @RequiresPermissions("system:storagequitdetail:edit")
    @Log(title = "退料", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Storagequitdetail storagequitdetail)
    {
        return toAjax(storagequitdetailService.updateStoragequitdetail(storagequitdetail));
    }

    /**
     * 删除退料
     */
    @RequiresPermissions("system:storagequitdetail:remove")
    @Log(title = "退料", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(storagequitdetailService.deleteStoragequitdetailByIds(ids));
    }
}
