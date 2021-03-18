package com.ruoyi.system.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.Bomdetail;
import com.ruoyi.system.domain.Storage;
import com.ruoyi.system.service.IBomdetailService;
import com.ruoyi.system.service.IStorageService;
import com.ruoyi.system.util.POIUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * bom详细清单Controller
 * 
 * @author ruoyi
 * @date 2020-07-24
 */
@Controller
@RequestMapping("/system/bomdetail")
public class BomdetailController extends BaseController
{
    private String prefix = "system/bomdetail";

    @Autowired
    private IBomdetailService bomdetailService;
    @Autowired
    private IStorageService storageService;

    List<Map<String,Object>> bomDetailList=new ArrayList<>();

    @RequiresPermissions("system:bomdetail:view")
    @GetMapping()
    public String bomdetail()
    {
        return prefix + "/bomdetail";
    }


    @GetMapping("/clearbomDetailList")
    @ResponseBody
    public Map clearbomDetailList(){
        Map<String,Object> ret=new HashMap<>();
        bomDetailList.clear();
        ret.put("msg","success");
        return  ret;
    }


    @PostMapping("/selectBomBySupplierAndPrice")
    @ResponseBody
    public Object  selectBomBySupplierAndPrice(@RequestParam("id") Long id){
        return storageService.selectStorageById(id);
    }

    @PostMapping("/importData")
    @Log(title = "bom产品列表", businessType = BusinessType.IMPORT)
    @RequiresPermissions("system:detail:import")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport, HttpServletRequest request) throws Exception
    {
        try {
            bomDetailList.clear();
            POIUtils.checkFile(file);
            Workbook wb =POIUtils.getWorkBook(file);
            Sheet sheet = wb.getSheetAt(0);
            int excelRealRow = POIUtils.getExcelRealRow(sheet);
            for (int i=5;i<excelRealRow;i++){
                Row row = sheet.getRow(i);
                if(StringUtils.isBlank(POIUtils.getCellValue(row.getCell(0)))){
                         break;
                }
                Map<String,Object> map=new HashMap<>();
                map.put("no",POIUtils.getCellValue(row.getCell(0)));
                String code = row.getCell(1).toString().replaceAll(" ", "");
                map.put("code",code);
                if(code!=null){
                    Storage storage = storageService.selectStorageListBymaterialcode(code);
                    if(storage!=null){
                        map.put("price",storage.getPrice());
                    }
                }
                map.put("link",POIUtils.getCellValue(row.getCell(2)));
                map.put("comment",POIUtils.getCellValue(row.getCell(3)));
                map.put("footprint",POIUtils.getCellValue(row.getCell(4)));
                map.put("description",POIUtils.getCellValue(row.getCell(5)));
                map.put("parttype",POIUtils.getCellValue(row.getCell(6)));
                map.put("designator",POIUtils.getCellValue(row.getCell(7)));
                map.put("quantity",POIUtils.getCellValue(row.getCell(8)));
                bomDetailList.add(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("操作失败!");
        }
        return AjaxResult.success("操作成功!");
    }

    @PostMapping("/listimport")
    @ResponseBody
    public TableDataInfo listimport()
    {
        startPage();
        return getDataTable(bomDetailList);
    }

    /**
     * 查询bom详细清单列表
     */
    @RequiresPermissions("system:bomdetail:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Bomdetail bomdetail)
    {
        startPage();
        List<Bomdetail> list = bomdetailService.selectBomdetailList(bomdetail);
        return getDataTable(list);
    }

    /**
     * 导出bom详细清单列表
     */
    @RequiresPermissions("system:bomdetail:export")
    @Log(title = "bom详细清单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Bomdetail bomdetail)
    {
        List<Bomdetail> list = bomdetailService.selectBomdetailList(bomdetail);
        ExcelUtil<Bomdetail> util = new ExcelUtil<Bomdetail>(Bomdetail.class);
        return util.exportExcel(list, "bomdetail");
    }

    /**
     * 新增bom详细清单
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存bom详细清单
     */
    @RequiresPermissions("system:bomdetail:add")
    @Log(title = "bom详细清单", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Bomdetail bomdetail)
    {
        return toAjax(bomdetailService.insertBomdetail(bomdetail));
    }

    /**
     * 修改bom详细清单
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
        Bomdetail bomdetail = bomdetailService.selectBomdetailById(id);
        mmap.put("bomdetail", bomdetail);
        return prefix + "/edit";
    }



    /**
     * 修改保存bom详细清单
     */
    @RequiresPermissions("system:bomdetail:edit")
    @Log(title = "bom详细清单", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Bomdetail bomdetail)
    {
        return toAjax(bomdetailService.updateBomdetail(bomdetail));
    }

    /**
     * 删除bom详细清单
     */
    @RequiresPermissions("system:bomdetail:remove")
    @Log(title = "bom详细清单", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(bomdetailService.deleteBomdetailByIds(ids));
    }
}
