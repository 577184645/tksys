package com.ruoyi.system.controller;

import java.util.List;

import com.ruoyi.system.domain.Storageindetail;
import com.ruoyi.system.service.IStorageindetailService;
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
import com.ruoyi.system.domain.Storageinbill;
import com.ruoyi.system.service.IStorageinbillService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 入库单列表Controller
 * 
 * @author ruoyi
 * @date 2020-06-04
 */
@Controller
@RequestMapping("/system/storageinbill")
public class StorageinbillController extends BaseController
{
    private String prefix = "system/storageinbill";

    @Autowired
    private IStorageinbillService storageinbillService;

    @Autowired
    private IStorageindetailService iStorageindetailService;


    @GetMapping()
    public String storageinbill()
    {
        return prefix + "/storageinbill";
    }


    @PostMapping("/getstockinid")
    @ResponseBody
    public Integer getstockinid(Storageinbill storageinbill){
      return       storageinbillService.selectStorageinbillList(storageinbill).size()+1;
    }

    /**
     * 查询入库单列表列表
     */

    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Storageinbill storageinbill)
    {
        startPage();
        List<Storageinbill> list = storageinbillService.selectStorageinbillList(storageinbill);
        return getDataTable(list);
    }

    /**
     * 导出入库单列表列表
     */

    @Log(title = "入库单列表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Storageinbill storageinbill)
    {
        List<Storageinbill> list = storageinbillService.selectStorageinbillList(storageinbill);
        ExcelUtil<Storageinbill> util = new ExcelUtil<Storageinbill>(Storageinbill.class);
        return util.exportExcel(list, "storageinbill");
    }

    /**
     * 新增入库单列表
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存入库单列表
     */
    @RequiresPermissions("system:storageinbill:add")
    @Log(title = "入库单列表", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Storageinbill storageinbill)
    {
        return toAjax(storageinbillService.insertStorageinbill(storageinbill));
    }

    /**
     * 修改入库单列表
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Storageinbill storageinbill = storageinbillService.selectStorageinbillById(id);
        mmap.put("storageinbill", storageinbill);
        System.out.println(storageinbill);
        return prefix + "/edit";
    }


    /**
     * 打印入库产品列表
     */
    @GetMapping("/print/{id}")
    public String print(@PathVariable("id") Long id, ModelMap mmap)
    {
        Storageinbill storageinbill = storageinbillService.selectStorageinbillById(id);
        mmap.put("storageinbill", storageinbill);
        List<Storageindetail> storageindetails = iStorageindetailService.selectStorageindetailByStorageinbillId(storageinbill.getStockinid());
        mmap.put("storageindetails", storageindetails);
        return prefix + "/print";
    }

    /**
     * 修改保存入库单列表
     */
    @RequiresPermissions("system:storageinbill:edit")
    @Log(title = "入库单列表", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Storageinbill storageinbill)
    {
        return toAjax(storageinbillService.updateStorageinbill(storageinbill));
    }

    /**
     * 删除入库单列表
     */
    @RequiresPermissions("system:storageinbill:remove")
    @Log(title = "入库单列表", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(storageinbillService.deleteStorageinbillByIds(ids));
    }
}
