package com.ruoyi.system.controller;

import java.util.List;

import com.ruoyi.common.config.Global;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.service.IProjectService;
import com.ruoyi.system.service.ISupplierService;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.system.util.WebUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
import com.ruoyi.system.service.IStorageService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    @Autowired
    private IProjectService iProjectService;
    @Autowired
    private ISupplierService iSupplierService;

    @RequiresPermissions("system:storage:view")
    @GetMapping()
    public String storage()
    {
        return prefix + "/storage";
    }



    @GetMapping("/download")
    public void resourceDownload(String resource, HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        String date = request.getParameter("date");
        try {
            Workbook wb = new XSSFWorkbook();
            wb =storageService.fillExcelStorage(date);
            WebUtil.downloadExcel(response, wb, date.substring(0,4)+"年"+(date.substring(date.indexOf("-")+1,date.indexOf("-")+3)+"月")+"仓库出入库台账.xlsx");
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
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





    @GetMapping("/findlist/{materialcode}")
    @ResponseBody
    public Storage findlist(@PathVariable("materialcode") String materialcode)
    {
         return  storageService.selectStorageListBymaterialcode(materialcode);
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
        SysUser user = ShiroUtils.getSysUser();
        mmap.put("userName", user.getUserName());
        mmap.put("userList",iSysUserService.findList());
        mmap.put("projectList",iProjectService.selectProjectList(null));
        mmap.put("supplierList",iSupplierService.findListSupplier());

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
        SysUser user = ShiroUtils.getSysUser();
        mmap.put("userName", user.getUserName());
        mmap.put("userList",iSysUserService.findList());
        mmap.put("projectList",iProjectService.selectProjectList(null));
        mmap.put("supplierList",iSupplierService.findListCustomer());
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
            Long stocks = storageService.selectStorageById(jsonObject.getLong("id")).getStocks();
            if(stocks<Long.valueOf(jsonObject.getString("counts"))){

                return AjaxResult.warn("库存不足！");

            }
        }


        return toAjax(storageService.updateStorage(storageoutbill,StorageoutbillList));
    }



    /**
     * 跳转退料页面
     */
    @GetMapping("/quit")
    public String quit(ModelMap mmap)
    {
        SysUser user = ShiroUtils.getSysUser();
        mmap.put("userName", user.getUserName());
        mmap.put("userList",iSysUserService.findList());
        return prefix + "/quit";
    }

    @RequiresPermissions("system:storage:quit")
    @Log(title = "退料", businessType = BusinessType.INSERT)
    @PostMapping("/quit")
    @ResponseBody
    public AjaxResult quit(String StoragequitbillList, Storagequitbill storagequitbill)
    {


        return toAjax(storageService.quitStorage(storagequitbill,StoragequitbillList));
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
