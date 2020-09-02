package com.ruoyi.system.controller;

import java.util.List;

import com.ruoyi.system.domain.Storage;
import com.ruoyi.system.mapper.StorageMapper;
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
import com.ruoyi.system.domain.WarehouseRecord;
import com.ruoyi.system.service.IWarehouseRecordService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 库存记录Controller
 * 
 * @author ruoyi
 * @date 2020-06-24
 */
@Controller
@RequestMapping("/system/record")
public class WarehouseRecordController extends BaseController
{
    private String prefix = "system/record";

    @Autowired
    private IWarehouseRecordService warehouseRecordService;
    @Autowired
    private StorageMapper storageMapper;
    @RequiresPermissions("system:record:view")
    @GetMapping("/{id}")
    public String record(@PathVariable("id") Long id,ModelMap mmap)
    {
        Storage storage = storageMapper.selectStorageById(id);
        mmap.put("materialcode",storage.getMaterialcode());
        mmap.put("supplier",storage.getSupplier());
        System.out.println(storage.getSerialNumber());
        mmap.put("serialNumber",storage.getSerialNumber());
        return prefix + "/record";
    }

    /**
     * 查询库存记录列表
     */
    @RequiresPermissions("system:record:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WarehouseRecord warehouseRecord)
    {
        startPage();
        List<WarehouseRecord> list = warehouseRecordService.selectWarehouseRecordList(warehouseRecord);
        return getDataTable(list);
    }

    /**
     * 导出库存记录列表
     */
    @RequiresPermissions("system:record:export")
    @Log(title = "库存记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WarehouseRecord warehouseRecord)
    {
        List<WarehouseRecord> list = warehouseRecordService.selectWarehouseRecordList(warehouseRecord);
        ExcelUtil<WarehouseRecord> util = new ExcelUtil<WarehouseRecord>(WarehouseRecord.class);
        return util.exportExcel(list, "record");
    }

    /**
     * 新增库存记录
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存库存记录
     */
    @RequiresPermissions("system:record:add")
    @Log(title = "库存记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(WarehouseRecord warehouseRecord)
    {
        return toAjax(warehouseRecordService.insertWarehouseRecord(warehouseRecord));
    }

    /**
     * 修改库存记录
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        WarehouseRecord warehouseRecord = warehouseRecordService.selectWarehouseRecordById(id);
        mmap.put("warehouseRecord", warehouseRecord);
        return prefix + "/edit";
    }

    /**
     * 修改保存库存记录
     */
    @RequiresPermissions("system:record:edit")
    @Log(title = "库存记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(WarehouseRecord warehouseRecord)
    {
        return toAjax(warehouseRecordService.updateWarehouseRecord(warehouseRecord));
    }

    /**
     * 删除库存记录
     */
    @RequiresPermissions("system:record:remove")
    @Log(title = "库存记录", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(warehouseRecordService.deleteWarehouseRecordByIds(ids));
    }
}
