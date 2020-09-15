package com.ruoyi.system.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.common.Const;
import com.ruoyi.system.domain.SysFileInfo;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.util.DateUtil;
import com.ruoyi.system.util.FileUtil;
import com.ruoyi.vo.OfferDataVo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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



    @GetMapping("infoview/{offerNumber}")
    public String infoview(@PathVariable("offerNumber") String offerNumber,ModelMap mmap)
    {
        mmap.put("offerNumber",offerNumber);
        return prefix + "/offerinfo";
    }

    /**
     * 查询报价单列表
     */
    @RequiresPermissions("system:offer:list")
    @PostMapping("/info")
    @ResponseBody
    public TableDataInfo info(String offerNumber)
    {
        startPage();
        List<Offer> list = offerService.selectOfferListByofferNumber(offerNumber);

        return getDataTable(list);
    }


    @PostMapping("/temporaryStorage")
    @ResponseBody
    public AjaxResult temporaryStorage(Offer offer)
    {
        SysUser user = ShiroUtils.getSysUser();
     /*
        JSONArray productArray = JSONArray.fromObject(data);
        JSONObject jsonObject = productArray.getJSONObject(0);
        Offer offer=new Offer();
        offer.setOfferUsername(jsonObject.getString("offerUsername"));
        offer.setOfferSalesman(jsonObject.getString("offerSalesman"));
        offer.setOfferSalesmancontactway(jsonObject.getString("offerSalesmancontactway"));
        if(StringUtils.isNotBlank(jsonObject.getString("offerMoney"))){
            offer.setOfferMoney(jsonObject.getDouble("offerMoney"));
        }
        offer.setOfferProject(jsonObject.getString("offerProject"));
       // offer.setOfferTime(DateUtils.jsonObject.getString("offerTime"));
        offer.setContext(jsonObject.getString("context"));
      */
        Const.OfferData.map.put(user.getUserName(),offer);
            return toAjax(true);
    }



    /**
     * 新增报价单
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
        SysUser user = ShiroUtils.getSysUser();
        Offer offer = Const.OfferData.map.get(user.getUserName());
        mmap.put("offer",offer);
        return prefix + "/add";
    }

    @GetMapping("/add/{offerNumber}")
    public String addofferNumber(@PathVariable("offerNumber") String offerNumber,ModelMap map)
    {
        map.put("offerNumber",offerNumber);
        return prefix + "/newadd";
    }

    /**
     * 新增保存报价单
     */
    @RequiresPermissions("system:offer:add")
    @Log(title = "报价单", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Offer offer)
    {
        return toAjax(offerService.insertOffer(offer));
    }




    /**
     * 新增保存报价单
     */
    @RequiresPermissions("system:offer:add")
    @Log(title = "报价单", businessType = BusinessType.INSERT)
    @PostMapping("/addfile")
    @ResponseBody
        public AjaxResult addfileSave(@RequestParam("file") MultipartFile [] file, Offer offer) throws IOException
    {
        // 上传文件路径
        String filePath = Global.getUploadPath();
        // 上传并返回新文件名称
        String fileName="";
        for (int i=0;i<file.length;i++){
            fileName += FileUploadUtils.upload(filePath, file[i])+",";
        }
        offer.setAccessory(fileName.substring(0,fileName.lastIndexOf(",")));
        return toAjax(offerService.insertOffer(offer));
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
     * 修改报价单
     */
    @GetMapping("/infodetail/{offerId}")
    public String infodetail(@PathVariable("offerId") Long offerId, ModelMap mmap)
    {
        Offer offer = offerService.selectOfferById(offerId);
        mmap.put("offer", offer);
        return prefix + "/infodetail";
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
    @PostMapping("/editfile")
    @ResponseBody
    public AjaxResult editfileSave(@RequestParam("file") MultipartFile [] file, Offer offer) throws IOException
    {
        // 上传文件路径
        String filePath = Global.getUploadPath();
        String accessory = offerService.selectOfferById(offer.getOfferId()).getAccessory();
        // 上传并返回新文件名称
        String fileName="";
        for (int i=0;i<file.length;i++){
            fileName += FileUploadUtils.upload(filePath, file[i])+",";
        }
        offer.setAccessory(fileName.substring(0,fileName.lastIndexOf(",")));
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
