package com.ruoyi.system.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.common.Const;
import com.ruoyi.system.domain.MaterialChild;
import com.ruoyi.system.domain.Storageinbill;
import com.ruoyi.system.domain.Storageindetail;
import com.ruoyi.system.service.IProjectService;
import com.ruoyi.system.service.IStorageinbillService;
import com.ruoyi.system.service.IStorageindetailService;
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


    @Autowired
    private ISysUserService iSysUserService;
    @Autowired
    private IProjectService iProjectService;


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
    public int getstockinid(){
      return      storageinbillService.getstockinid();
    }

    @Log(title = "入库单红冲", businessType = BusinessType.DELETE)
    @PostMapping("/reddashed")
    @ResponseBody
    public AjaxResult reddashed(Long id){
       if(storageinbillService.selectStorageinbillById(id).getDelStatus()==Const.Storageinbilldelstatus.HONGCHONG.getCode()){
           return AjaxResult.warn("操作失败！该入库单已红冲");
       }
        if(storageinbillService.reddashed(id)>0){
            return AjaxResult.warn("操作成功");
        }
        return    AjaxResult.warn("操作失败,请联系管理员")   ;
    }

    @Log(title = "入库单批准", businessType = BusinessType.UPDATE)
    @PostMapping("/examine")
    @ResponseBody
    public AjaxResult examine(@RequestParam("value") Integer value,@RequestParam("id") Long id){
        if(storageinbillService.updateStorageinbillExamine(value,id)>0){
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
    /**
     * 导出bom列表列表
     */
    @RequiresPermissions("system:storageinbill:export")
    @Log(title = "入库单", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    @ResponseBody
    public AjaxResult export(Long id, HttpServletResponse response)
    {
        try {
            List<Storageindetail> storageindetails = iStorageindetailService.selectStorageindetailByStorageindetailId(id);
            Storageinbill storageinbill = storageinbillService.selectStorageinbillById(id);
            String file = Global.getProfile()+"/template/storageinbill.xlsx";
            XSSFWorkbook workbook=new XSSFWorkbook(new FileInputStream(new File(file)));
            XSSFSheet sheetAt = workbook.getSheetAt(0);
            sheetAt.getRow(1).getCell(1).setCellValue(storageinbill.getSupplier());
            sheetAt.getRow(1).getCell(6).setCellValue(storageinbill.getStockinid());
            CellStyle cellStyle2=workbook.createCellStyle();
            cellStyle2.setAlignment(HorizontalAlignment.CENTER);
            cellStyle2.setVerticalAlignment(VerticalAlignment.CENTER);
            cellStyle2.setWrapText(true);
            int index=3;
            for (int i = 0; i < storageindetails.size(); i++) {
                Storageindetail Storageindetail = storageindetails.get(i);
                index++;
                XSSFRow row = sheetAt.createRow(index);
                row.createCell(0).setCellValue(i+1);
                row.createCell(1).setCellValue(Storageindetail.getMaterialcode());
                row.createCell(7).setCellValue(Storageindetail.getCounts());
                List<MaterialChild> materialChildList = Storageindetail.getMaterialChildList();
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
            String filename=System.currentTimeMillis()+"入库单.xlsx";
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
        mmap.put("userList",iSysUserService.findList());
        mmap.put("projectList",iProjectService.selectProjectList(null));
        return prefix + "/edit";
    }


    /**
     * 打印入库产品列表
     */
    @GetMapping("/print/{id}")
    public String print(@PathVariable("id") Long id, ModelMap mmap)
    {
        Storageinbill storageinbill = storageinbillService.selectStorageinbillById(id);
        mmap.put("storageinbill",storageinbill);
        return prefix + "/print";
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
