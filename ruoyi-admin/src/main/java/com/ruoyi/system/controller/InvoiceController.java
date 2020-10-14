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
import com.ruoyi.system.domain.Invoice;
import com.ruoyi.system.service.IInvoiceService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 发票Controller
 * 
 * @author ruoyi
 * @date 2020-09-21
 */
@Controller
@RequestMapping("/system/invoice")
public class InvoiceController extends BaseController
{
    private String prefix = "system/invoice";

    @Autowired
    private IInvoiceService invoiceService;


    @GetMapping()
    public String invoice()
    {
        return prefix + "/invoice";
    }

    /**
     * 查询发票列表
     */

    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Invoice invoice)
    {
        startPage();
        List<Invoice> list = invoiceService.selectInvoiceList(invoice);
        return getDataTable(list);
    }


    @PostMapping("/getyearinvoicesummoney")
    @ResponseBody
    public Double getyearinvoicesummoney(String yyyy)
    {
        return  invoiceService.yearsummoney(yyyy)!=null?invoiceService.yearsummoney(yyyy):0;

    }

    /**
     * 导出发票列表
     */

    @Log(title = "发票", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Invoice invoice)
    {
        List<Invoice> list = invoiceService.selectInvoiceList(invoice);
        ExcelUtil<Invoice> util = new ExcelUtil<Invoice>(Invoice.class);
        return util.exportExcel(list, "invoice");
    }

    /**
     * 新增发票
     */
    @GetMapping("/add/{contractId}")
    public String add(@PathVariable("contractId") Long contractId,ModelMap map)
    {
        map.put("contractId",contractId);
        return prefix + "/add";
    }

    @GetMapping("/info/{contractId}")
    public String info(@PathVariable("contractId") Long contractId,ModelMap map)
    {
        map.put("contractId",contractId);
        return prefix + "/invoice";
    }


    /**
     * 新增保存发票
     */

    @Log(title = "发票", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Invoice invoice)
    {
        return toAjax(invoiceService.insertInvoice(invoice));
    }

    /**
     * 修改发票
     */
    @GetMapping("/edit/{invoiceId}")
    public String edit(@PathVariable("invoiceId") Long invoiceId, ModelMap mmap)
    {
        Invoice invoice = invoiceService.selectInvoiceById(invoiceId);
        mmap.put("invoice", invoice);
        return prefix + "/edit";
    }

    /**
     * 修改保存发票
     */

    @Log(title = "发票", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Invoice invoice)
    {
        return toAjax(invoiceService.updateInvoice(invoice));
    }

    /**
     * 删除发票
     */

    @Log(title = "发票", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(invoiceService.deleteInvoiceByIds(ids));
    }
}
