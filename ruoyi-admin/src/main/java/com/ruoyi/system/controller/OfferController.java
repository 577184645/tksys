package com.ruoyi.system.controller;

import java.io.IOException;
import java.util.List;

import com.ruoyi.common.config.Global;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.system.domain.SysFileInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.Offer;
import com.ruoyi.system.service.IOfferService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 报价单Controller
 * 
 * @author ruoyi
 * @date 2020-09-07
 */
@Controller
@RequestMapping("/system/offer")
public class OfferController extends BaseController
{
    private String prefix = "system/offer";

    @Autowired
    private IOfferService offerService;

    @RequiresPermissions("system:offer:view")
    @GetMapping()
    public String offer()
    {
        return prefix + "/offer";
    }

    /**
     * 查询报价单列表
     */
    @RequiresPermissions("system:offer:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Offer offer)
    {
        startPage();
        List<Offer> list = offerService.selectOfferList(offer);
        return getDataTable(list);
    }

    /**
     * 导出报价单列表
     */
    @RequiresPermissions("system:offer:export")
    @Log(title = "报价单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Offer offer)
    {
        List<Offer> list = offerService.selectOfferList(offer);
        ExcelUtil<Offer> util = new ExcelUtil<Offer>(Offer.class);
        return util.exportExcel(list, "offer");
    }

    /**
     * 新增报价单
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }





    /**
     * 新增保存报价单
     */
    @RequiresPermissions("system:offer:add")
    @Log(title = "报价单", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
        public AjaxResult addSave(@RequestParam("file") MultipartFile file, Offer offer) throws IOException
    {
        // 上传文件路径
        String filePath = Global.getUploadPath();
        // 上传并返回新文件名称
        String fileName = FileUploadUtils.upload(filePath, file);
        offer.setAccessory(fileName);
        return toAjax(offerService.insertOffer(offer));
    }

    /**
     * 修改报价单
     */
    @GetMapping("/edit/{offerId}")
    public String edit(@PathVariable("offerId") Long offerId, ModelMap mmap)
    {
        Offer offer = offerService.selectOfferById(offerId);
        mmap.put("offer", offer);
        return prefix + "/edit";
    }

    /**
     * 修改保存报价单
     */
    @RequiresPermissions("system:offer:edit")
    @Log(title = "报价单", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Offer offer)
    {
        return toAjax(offerService.updateOffer(offer));
    }

    /**
     * 删除报价单
     */
    @RequiresPermissions("system:offer:remove")
    @Log(title = "报价单", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(offerService.deleteOfferByIds(ids));
    }
}
