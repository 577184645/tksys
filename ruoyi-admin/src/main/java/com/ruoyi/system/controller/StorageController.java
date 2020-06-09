package com.ruoyi.system.controller;

import java.util.List;

import com.ruoyi.system.domain.Storageinbill;
import com.ruoyi.system.domain.Storageoutbill;
import com.ruoyi.system.service.ISysUserService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.Storage;
import com.ruoyi.system.service.IStorageService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 库存列表Controller
 * 
 * @author ruoyi
 * @date 2020-06-03
 */
@Controller
@RequestMapping("/system/storage")
public class StorageController extends BaseController
{



    private String prefix = "system/storage";

    @Autowired
    private IStorageService storageService;
    @Autowired
    private ISysUserService iSysUserService;

    @RequiresPermissions("system:storage:view")
    @GetMapping()
    public String storage()
    {
        return prefix + "/storage";
    }

    /**
     * 查询库存列表列表
     */
    @RequiresPermissions("system:storage:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Storage storage)
    {
        startPage();
        List<Storage> list = storageService.selectStorageList(storage);
        return getDataTable(list);
    }

    /**
     * 导出库存列表列表
     */
    @RequiresPermissions("system:storage:export")
    @Log(title = "库存列表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Storage storage)
    {
        List<Storage> list = storageService.selectStorageList(storage);
        ExcelUtil<Storage> util = new ExcelUtil<Storage>(Storage.class);
        return util.exportExcel(list, "storage");
    }

    /**
     * 新增库存列表
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
        mmap.put("userList",iSysUserService.findList());
        return prefix + "/add";
    }

    /**
     * 新增保存库存列表
     */
   /* @RequiresPermissions("system:storage:add")*/
    @Log(title = "入库", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(String StorageinbillList,Storageinbill Storageinbill)
    {

        return toAjax(storageService.insertStorage(Storageinbill,StorageinbillList));
    }

    /**
     * 修改库存列表
     */
    @GetMapping("/edit")
    public String edit(ModelMap mmap)
    {
        mmap.put("userList",iSysUserService.findList());
        return prefix + "/edit";
    }

    /**
     * 修改保存库存列表
     */
    @RequiresPermissions("system:storage:edit")
    @Log(title = "出库", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(String StorageoutbillList, Storageoutbill storageoutbill)
    {

        JSONArray productArray = JSONArray.fromObject(StorageoutbillList);

        for (int i = 0; i < productArray.size(); i++) {
            JSONObject jsonObject = productArray.getJSONObject(i);
            Long stocks = storageService.selectStorageById(Integer.valueOf(jsonObject.getString("id"))).getStocks();
            if(stocks<Long.valueOf(jsonObject.getString("counts"))){
                return AjaxResult.warn("库存不足！");
            }
        }


        return toAjax(storageService.updateStorage(storageoutbill,StorageoutbillList));
    }

    /**
     * 删除库存列表
     */
    @RequiresPermissions("system:storage:remove")
    @Log(title = "库存列表", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(storageService.deleteStorageByIds(ids));
    }
}
