package com.ruoyi.system.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.common.Const;
import com.ruoyi.system.domain.MaterialChild;
import com.ruoyi.system.domain.Storageoutbill;
import com.ruoyi.system.domain.Storageoutdetail;
import com.ruoyi.system.service.IProjectService;
import com.ruoyi.system.service.IStorageoutbillService;
import com.ruoyi.system.service.IStorageoutdetailService;
import com.ruoyi.system.service.ISysUserService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * 出库单列表Controller
 * 
 * @author ruoyi
 * @date 2020-06-09
 */
@Controller
@RequestMapping("/system/storageoutbill")
public class StorageoutbillController extends BaseController
{
    private String prefix = "system/storageoutbill";

    @Autowired
    private IStorageoutbillService storageoutbillService;
    @Autowired
    private IStorageoutdetailService storageoutdetailService;
    @Autowired
    private ISysUserService iSysUserService;
    @Autowired
    private IProjectService iProjectService;

    @PostMapping("/getstorageoutid")
    @ResponseBody
    public Integer getstorageoutid(Storageoutbill storageoutbill){
        return       storageoutbillService.getstorageoutid();
    }



    @GetMapping()
    public String storageoutbill()
    {
        return prefix + "/storageoutbill";
    }

    /**
     * 查询出库单列表列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Storageoutbill storageoutbill)
    {
        startPage();
        List<Storageoutbill> list = storageoutbillService.selectStorageoutbillList(storageoutbill);
        return getDataTable(list);
    }

    /**
     * 导出出库单列表列表
     */

    @Log(title = "出库单列表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Storageoutbill storageoutbill)
    {
        List<Storageoutbill> list = storageoutbillService.selectStorageoutbillList(storageoutbill);
        ExcelUtil<Storageoutbill> util = new ExcelUtil<Storageoutbill>(Storageoutbill.class);
        return util.exportExcel(list, "storageoutbill");
    }

    /**
     * 新增出库单列表
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存出库单列表
     */
    @RequiresPermissions("system:storageoutbill:add")
    @Log(title = "出库单列表", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Storageoutbill storageoutbill)
    {
        return toAjax(storageoutbillService.insertStorageoutbill(storageoutbill));
    }

    /**
     * 修改出库单列表
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Storageoutbill storageoutbill = storageoutbillService.selectStorageoutbillById(id);
        mmap.put("storageoutbill", storageoutbill);
        mmap.put("userList",iSysUserService.findList());
        mmap.put("projectList",iProjectService.selectProjectList(null));
        return prefix + "/edit";
    }

    /**
     * 修改保存出库单列表
     */
    @RequiresPermissions("system:storageoutbill:edit")
    @Log(title = "出库单列表", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Storageoutbill storageoutbill)
    {
        return toAjax(storageoutbillService.updateStorageoutbill(storageoutbill));
    }

    /**
     * 删除出库单列表
     */
    @RequiresPermissions("system:storageoutbill:remove")
    @Log(title = "出库单列表", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(storageoutbillService.deleteStorageoutbillByIds(ids));
    }


    /**
     * 打印入库产品列表
     */
    @GetMapping("/print/{id}")
    public String print(@PathVariable("id") Long id, ModelMap mmap)
    {
        Storageoutbill storageoutbill = storageoutbillService.selectStorageoutbillById(id);
        mmap.put("storageoutbill", storageoutbill);
        return prefix + "/print";
    }

    /**
     * 导出bom列表列表
     */
    @RequiresPermissions("system:storageoutbill:export")
    @Log(title = "bom列表", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    @ResponseBody
    public AjaxResult export(Long id,HttpServletResponse response)
    {
        try {
            List<Storageoutdetail> storageoutdetails = storageoutdetailService.selectStorageindetailByStorageoutdetailId(id);
            Storageoutbill storageoutbill = storageoutbillService.selectStorageoutbillById(id);
            String file = Global.getProfile()+"/template/storageoutbill.xlsx";
            XSSFWorkbook workbook=new XSSFWorkbook(new FileInputStream(new File(file)));
            XSSFSheet sheetAt = workbook.getSheetAt(0);
            sheetAt.getRow(1).getCell(1).setCellValue(storageoutbill.getCustomer());
            sheetAt.getRow(1).getCell(6).setCellValue(storageoutbill.getStorageoutid());
            CellStyle cellStyle2=workbook.createCellStyle();
            cellStyle2.setAlignment(HorizontalAlignment.CENTER);
            cellStyle2.setVerticalAlignment(VerticalAlignment.CENTER);
            cellStyle2.setWrapText(true);
            int index=3;
            for (int i = 0; i < storageoutdetails.size(); i++) {
                Storageoutdetail storageoutdetail = storageoutdetails.get(i);
                index++;
                XSSFRow row = sheetAt.createRow(index);
                row.createCell(0).setCellValue(i+1);
                row.createCell(1).setCellValue(storageoutdetail.getMaterialcode());
                row.createCell(7).setCellValue(storageoutdetail.getCounts());
                List<MaterialChild> materialChildList = storageoutdetail.getMaterialChildList();
                row.createCell(2).setCellValue(materialChildList.get(0).getName());
                row.createCell(3).setCellValue(materialChildList.get(0).getDescription());
                row.createCell(4).setCellValue(materialChildList.get(0).getFootprint());
                row.createCell(5).setCellValue(materialChildList.get(0).getManufacture());
                row.createCell(6).setCellValue(materialChildList.get(0).getUnit());
                for (Cell cell : row) {
                    cell.setCellStyle(cellStyle2);
                }
                if(materialChildList.size()>1){
                    for (int i1 = 1; i1 < materialChildList.size(); i1++) {
                        index++;
                        Row row2 = sheetAt.createRow(index);
                        row2.createCell(2).setCellValue(materialChildList.get(i).getName());
                        row2.createCell(3).setCellValue(materialChildList.get(i).getDescription());
                        row2.createCell(4).setCellValue(materialChildList.get(i).getFootprint());
                        row2.createCell(5).setCellValue(materialChildList.get(i).getManufacture());
                        row2.createCell(6).setCellValue(materialChildList.get(i).getUnit());
                        for (Cell cell : row2) {
                            cell.setCellStyle(cellStyle2);
                        }
                        boolean flag=true;
                        if(flag) {
                            sheetAt.addMergedRegion(new CellRangeAddress(
                                    index-materialChildList.size()+1,   //起始行
                                    index,   //结束行
                                    0,   //起始列
                                    0    //结束列
                            ));
                            sheetAt.addMergedRegion(new CellRangeAddress(
                                    index-materialChildList.size()+1,   //起始行
                                    index,   //结束行
                                    1,   //起始列
                                    1    //结束列
                            ));
                            sheetAt.addMergedRegion(new CellRangeAddress(
                                    index-materialChildList.size()+1,   //起始行
                                    index,   //结束行
                                    7,   //起始列
                                    7    //结束列
                            ));

                        }
                        flag = false;
                    }
                    }



            }
            String filename=System.currentTimeMillis()+"出库单.xlsx";
            ServletOutputStream outputStream=response.getOutputStream();
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("content-Disposition", "attachment;filename="+new String(filename.getBytes("utf-8"),"iso-8859-1"));
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Log(title = "出库单红冲", businessType = BusinessType.DELETE)
    @PostMapping("/reddashed")
    @ResponseBody
    public AjaxResult reddashed(@RequestParam("id") Long id){
        if(storageoutbillService.selectStorageoutbillById(id).getDelStatus()== Const.Storageinbilldelstatus.HONGCHONG.getCode()){
            return AjaxResult.warn("操作失败！该出库单已红冲");
        }
        if(storageoutbillService.reddashed(id)>0){
            return AjaxResult.warn("操作成功");
        }
        return    AjaxResult.warn("操作失败,请联系管理员")   ;
    }



}
