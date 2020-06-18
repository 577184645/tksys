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
import com.ruoyi.system.domain.Storagequitbill;
import com.ruoyi.system.service.IStoragequitbillService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 退料单列表Controller
 * 
 * @author ruoyi
 * @date 2020-06-18
 */
@Controller
@RequestMapping("/system/storagequitbill")
public class StoragequitbillController extends BaseController
{
    private String prefix = "system/storagequitbill";

    @Autowired
    private IStoragequitbillService storagequitbillService;

    @RequiresPermissions("system:storagequitbill:view")
    @GetMapping()
    public String storagequitbill()
    {
        return prefix + "/storagequitbill";
    }

    /**
     * 查询退料单列表列表
     */
    @RequiresPermissions("system:storagequitbill:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Storagequitbill storagequitbill)
    {
        startPage();
        List<Storagequitbill> list = storagequitbillService.selectStoragequitbillList(storagequitbill);
        return getDataTable(list);
    }

    /**
     * 导出退料单列表列表
     */
    @RequiresPermissions("system:storagequitbill:export")
    @Log(title = "退料单列表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Storagequitbill storagequitbill)
    {
        List<Storagequitbill> list = storagequitbillService.selectStoragequitbillList(storagequitbill);
        ExcelUtil<Storagequitbill> util = new ExcelUtil<Storagequitbill>(Storagequitbill.class);
        return util.exportExcel(list, "storagequitbill");
    }

    /**
     * 新增退料单列表
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存退料单列表
     */
    @RequiresPermissions("system:storagequitbill:add")
    @Log(title = "退料单列表", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Storagequitbill storagequitbill)
    {
        return toAjax(storagequitbillService.insertStoragequitbill(storagequitbill));
    }

    /**
     * 修改退料单列表
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Storagequitbill storagequitbill = storagequitbillService.selectStoragequitbillById(id);
        mmap.put("storagequitbill", storagequitbill);
        return prefix + "/edit";
    }

    /**
     * 修改保存退料单列表
     */
    @RequiresPermissions("system:storagequitbill:edit")
    @Log(title = "退料单列表", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Storagequitbill storagequitbill)
    {
        return toAjax(storagequitbillService.updateStoragequitbill(storagequitbill));
    }

    /**
     * 删除退料单列表
     */
    @RequiresPermissions("system:storagequitbill:remove")
    @Log(title = "退料单列表", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(storagequitbillService.deleteStoragequitbillByIds(ids));
    }
}
