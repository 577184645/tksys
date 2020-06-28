package com.ruoyi.system.controller;

import java.util.List;
import java.util.Map;

import com.ruoyi.system.domain.Storageindetail;
import com.ruoyi.system.domain.Testss;
import com.ruoyi.system.service.IStorageindetailService;
import io.swagger.models.auth.In;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.Storageinbill;
import com.ruoyi.system.service.IStorageinbillService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

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


    @GetMapping("/bycheck")
    public String storageinbillcheck()
    {
        return prefix + "/storageinbillcheck";
    }

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



    @Log(title = "入库单红冲", businessType = BusinessType.DELETE)
    @PostMapping("/reddashed")
    @ResponseBody
    public AjaxResult reddashed(Long id,String list){
       if(storageinbillService.selectStorageinbillById(id).getDelStatus().equals("2")){
           return AjaxResult.warn("操作失败！该入库单已红冲");
       }

        if(storageinbillService.reddashed(id)>0){
            return AjaxResult.warn("操作成功");
        }
        return    AjaxResult.warn("操作失败,请联系管理员")   ;
    }

    @Log(title = "入库单批准", businessType = BusinessType.UPDATE)
    @PostMapping("/ratify")
    @ResponseBody
    public AjaxResult ratify(@RequestParam("id") Long id){
        if(storageinbillService.updateStorageinbillFatify(id)>0){
            return AjaxResult.warn("操作成功");
        }
        return    AjaxResult.warn("操作失败,请联系管理员")   ;
    }

    @Log(title = "入库单驳回", businessType = BusinessType.UPDATE)
    @PostMapping("/turn")
    @ResponseBody
    public AjaxResult turn(@RequestParam("id") Long id){
        if(storageinbillService.updateStorageinbillTurn(id)>0){
            return AjaxResult.warn("操作成功");
        }
        return    AjaxResult.warn("操作失败,请联系管理员")   ;
    }

    @Log(title = "入库单申请", businessType = BusinessType.UPDATE)
    @PostMapping("/apply")
    @ResponseBody
    public AjaxResult apply(@RequestParam("id") Long id){
        if(storageinbillService.updateStorageinbillApply(id)>0){
                return AjaxResult.warn("操作成功");
        }
        return    AjaxResult.warn("操作失败,请联系管理员")   ;
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
     * 打印入库产品列表
     */
    @GetMapping("/print1/{id}")
    public String print1(@PathVariable("id") Long id, ModelMap mmap)
    {
        Storageinbill storageinbill = storageinbillService.selectStorageinbillById(id);
        mmap.put("storageinbill", storageinbill);
        List<Storageindetail> storageindetails = iStorageindetailService.selectStorageindetailByStorageinbillId(storageinbill.getStockinid());
        mmap.put("storageindetails", storageindetails);
        return prefix + "/print1";
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
