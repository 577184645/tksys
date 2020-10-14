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
import com.ruoyi.system.domain.Returned;
import com.ruoyi.system.service.IReturnedService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 回款Controller
 * 
 * @author ruoyi
 * @date 2020-09-18
 */
@Controller
@RequestMapping("/system/returned")
public class ReturnedController extends BaseController
{
    private String prefix = "system/returned";

    @Autowired
    private IReturnedService returnedService;


    @GetMapping("/{contractNumber}")
    public String returned(ModelMap map, @PathVariable("contractNumber") Long contractNumber)
    {
        map.put("contractNumber",contractNumber);
        return prefix + "/returned";
    }

    /**
     * 查询回款列表
     */

    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Returned returned)
    {
        startPage();
        List<Returned> list = returnedService.selectReturnedList(returned);
        return getDataTable(list);
    }

    /**
     * 导出回款列表
     */

    @Log(title = "回款", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Returned returned)
    {
        List<Returned> list = returnedService.selectReturnedList(returned);
        ExcelUtil<Returned> util = new ExcelUtil<Returned>(Returned.class);
        return util.exportExcel(list, "returned");
    }

    /**
     * 新增回款
     */
    @GetMapping("/add/{contractId}")
    public String add(@PathVariable("contractId") Long contractId,ModelMap map)
    {
        map.put("contractId",contractId);
        return prefix + "/add";
    }



    @PostMapping("/getyearbacksummoney")
    @ResponseBody
    public Double getyearbacksummoney(String yyyy)
    {
        return  returnedService.yearsummoney(yyyy)!=null?returnedService.yearsummoney(yyyy):0;

    }

    /**
     * 新增保存回款
     */

    @Log(title = "回款", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Returned returned)
    {
        return toAjax(returnedService.insertReturned(returned));
    }

    /**
     * 修改回款
     */
    @GetMapping("/edit/{returnedId}")
    public String edit(@PathVariable("returnedId") Long returnedId, ModelMap mmap)
    {
        Returned returned = returnedService.selectReturnedById(returnedId);
        mmap.put("returned", returned);
        return prefix + "/edit";
    }

    /**
     * 修改保存回款
     */

    @Log(title = "回款", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Returned returned)
    {
        return toAjax(returnedService.updateReturned(returned));
    }

    /**
     * 删除回款
     */

    @Log(title = "回款", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(returnedService.deleteReturnedByIds(ids));
    }
}
