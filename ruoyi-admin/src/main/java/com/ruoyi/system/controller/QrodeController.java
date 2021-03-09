package com.ruoyi.system.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.Qrode;
import com.ruoyi.system.service.IQrodeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 二维码列表Controller
 * 
 * @author ruoyi
 * @date 2020-07-22
 */
@Controller
@RequestMapping("/system/qrode")
public class QrodeController extends BaseController
{
    private String prefix = "system/qrode";

    @Autowired
    private IQrodeService qrodeService;

    @RequiresPermissions("system:qrode:view")
    @GetMapping()
    public String qrode()
    {
        return prefix + "/qrode";
    }



    @PostMapping("/importData")
    @RequiresPermissions("system:qrode:import")
    @Log(title = "二维码列表", businessType = BusinessType.IMPORT)
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport, HttpServletRequest request) throws Exception
    {

        ExcelUtil<Qrode> util = new ExcelUtil<Qrode>(Qrode.class);
        List<Qrode> qrodes  = util.importExcel(file.getInputStream());
        String message = qrodeService.importQrode(qrodes);
        return AjaxResult.success(message);
    }

    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<Qrode> util = new ExcelUtil<Qrode>(Qrode.class);
        return util.importTemplateExcel("二维码数据");
    }


    /**
     * 查询二维码列表列表
     */
    @RequiresPermissions("system:qrode:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Qrode qrode)
    {
        startPage();
        List<Qrode> list = qrodeService.selectQrodeList(qrode);
        return getDataTable(list);
    }

    /**
     * 导出二维码列表列表
     */
    @RequiresPermissions("system:qrode:export")
    @Log(title = "二维码列表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Qrode qrode)
    {
        List<Qrode> list = qrodeService.selectQrodeList(qrode);
        ExcelUtil<Qrode> util = new ExcelUtil<Qrode>(Qrode.class);
        return util.exportExcel(list, "qrode");
    }

    /**
     * 新增二维码列表
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存二维码列表
     */
    @RequiresPermissions("system:qrode:add")
    @Log(title = "二维码列表", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Qrode qrode)
    {
        return toAjax(qrodeService.insertQrode(qrode));
    }

    /**
     * 修改二维码列表
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Qrode qrode = qrodeService.selectQrodeById(id);
        mmap.put("qrode", qrode);
        return prefix + "/edit";
    }

    /**
     * 修改二维码列表
     */
    @GetMapping("/info/{serialnumber}")
    public String info(@PathVariable("serialnumber") String serialnumber, ModelMap mmap)
    {
        Qrode qrode = qrodeService.selectQrodeBySerialnumber(serialnumber);
        mmap.put("qrode", qrode);
        return prefix + "/info";
    }

    /**
     * 修改二维码列表
     */
    @GetMapping("/infouser/{serialnumber}")
    public String infouser(@PathVariable("serialnumber") String serialnumber, ModelMap mmap)
    {
        Qrode qrode = qrodeService.selectQrodeBySerialnumber(serialnumber);
        mmap.put("qrode", qrode);
        return prefix + "/infouser";
    }

    /**
     * 修改二维码列表
     */
    @GetMapping("/infoadmin/{serialnumber}")
    public String infoadmin(@PathVariable("serialnumber") String serialnumber, ModelMap mmap)
    {
        Qrode qrode = qrodeService.selectQrodeBySerialnumber(serialnumber);
        mmap.put("qrode", qrode);
        return prefix + "/infoadmin";
    }

    /**
     * 修改保存二维码列表
     */
    @RequiresPermissions("system:qrode:edit")
    @Log(title = "二维码列表", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Qrode qrode)
    {

        return toAjax(qrodeService.updateQrode(qrode));
    }

    /**
     * 删除二维码列表
     */
    @RequiresPermissions("system:qrode:remove")
    @Log(title = "二维码列表", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(qrodeService.deleteQrodeByIds(ids));
    }
}
