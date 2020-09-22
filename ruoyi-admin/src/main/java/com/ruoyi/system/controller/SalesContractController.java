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
import com.ruoyi.system.domain.SalesContract;
import com.ruoyi.system.service.ISalesContractService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 销售合同Controller
 * 
 * @author ruoyi
 * @date 2020-09-18
 */
@Controller
@RequestMapping("/system/contract")
public class SalesContractController extends BaseController
{
    private String prefix = "system/contract";

    @Autowired
    private ISalesContractService salesContractService;


    @GetMapping()
    public String contract()
    {
        return prefix + "/contract";
    }

    /**
     * 查询销售合同列表
     */

    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SalesContract salesContract)
    {
        startPage();
        List<SalesContract> list = salesContractService.selectSalesContractList(salesContract);
        return getDataTable(list);
    }

    /**
     * 导出销售合同列表
     */

    @Log(title = "销售合同", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SalesContract salesContract)
    {
        List<SalesContract> list = salesContractService.selectSalesContractList(salesContract);
        ExcelUtil<SalesContract> util = new ExcelUtil<SalesContract>(SalesContract.class);
        return util.exportExcel(list, "contract");
    }

    /**
     * 新增销售合同
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存销售合同
     */

    @Log(title = "销售合同", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SalesContract salesContract)
    {
        return toAjax(salesContractService.insertSalesContract(salesContract));
    }

    /**
     * 修改销售合同
     */
    @GetMapping("/edit/{contractId}")
    public String edit(@PathVariable("contractId") Long contractId, ModelMap mmap)
    {
        SalesContract salesContract = salesContractService.selectSalesContractById(contractId);
        mmap.put("salesContract", salesContract);
        return prefix + "/edit";
    }

    /**
     * 修改保存销售合同
     */

    @Log(title = "销售合同", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SalesContract salesContract)
    {
        return toAjax(salesContractService.updateSalesContract(salesContract));
    }

    /**
     * 删除销售合同
     */

    @Log(title = "销售合同", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(salesContractService.deleteSalesContractByIds(ids));
    }
}
