package com.ruoyi.system.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.service.IProjectService;
import com.ruoyi.system.service.IStorageService;
import com.ruoyi.system.service.ISupplierService;
import com.ruoyi.system.service.ISysUserService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
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
import java.util.List;

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
    @GetMapping("/export")
    public void export(Storage storage, HttpServletResponse response)
    {
        try {
            List<Storage> list = storageService.selectStorageList(storage);
            String file = Global.getProfile()+"/template/storage.xlsx";
            XSSFWorkbook workbook=new XSSFWorkbook(new FileInputStream(new File(file)));
            XSSFSheet sheet = workbook.getSheetAt(0);
            CellStyle cellStyle2=workbook.createCellStyle();
            cellStyle2.setAlignment(HorizontalAlignment.CENTER);
            cellStyle2.setVerticalAlignment(VerticalAlignment.CENTER);
            cellStyle2.setWrapText(true);
            CreationHelper createHelper = workbook.getCreationHelper();
            CellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-MM-dd"));
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
            cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            cellStyle.setWrapText(true);
            int index=0;
            for (int i1 = 0; i1 < list.size(); i1++) {
                index++;
                Row row1 = sheet.createRow(index);
                row1.createCell(0).setCellValue(list.get(i1).getMaterialcode());
                row1.createCell(6).setCellValue(list.get(i1).getStocks());
                row1.createCell(10).setCellValue(list.get(i1).getUTime());
                row1.getCell(10).setCellStyle(cellStyle);
                row1.createCell(8).setCellValue(list.get(i1).getOTime());
                row1.getCell(8).setCellStyle(cellStyle);
                row1.createCell(9).setCellValue(list.get(i1).getQTime());
                row1.getCell(9).setCellStyle(cellStyle);
                row1.createCell(7).setCellValue(list.get(i1).getPrice()!=null?list.get(i1).getPrice():0);
                List<MaterialChild> materialChildList = list.get(i1).getMaterialChildList();
                row1.createCell(1).setCellValue(materialChildList.get(0).getName());
                row1.createCell(2).setCellValue(materialChildList.get(0).getPartnumber());
                row1.createCell(3).setCellValue(materialChildList.get(0).getManufacture());
                row1.createCell(4).setCellValue(materialChildList.get(0).getFootprint());
                row1.createCell(5).setCellValue(materialChildList.get(0).getDescription());
                for (int i = 0; i < row1.getLastCellNum(); i++) {
                    if(i<8){
                        row1.getCell(i).setCellStyle(cellStyle2);
                    }else{
                        row1.getCell(i).setCellStyle(cellStyle);
                    }

                }

                if(materialChildList.size()>1){
                    for (int i = 1; i < materialChildList.size(); i++) {
                        index++;
                        Row row2 = sheet.createRow(index);
                        row2.createCell(1).setCellValue(materialChildList.get(i).getName());
                        row2.createCell(2).setCellValue(materialChildList.get(i).getPartnumber());
                        for (Cell cell : row2) {
                            cell.setCellStyle(cellStyle2);
                        }
                    }

                        sheet.addMergedRegion(new CellRangeAddress(
                                index-materialChildList.size()+1,   //起始行
                                index,   //结束行
                                0,   //起始列
                                0    //结束列
                        ));
                        sheet.addMergedRegion(new CellRangeAddress(
                                index-materialChildList.size()+1,   //起始行
                                index,   //结束行
                                6,   //起始列
                                6    //结束列
                        ));
                        sheet.addMergedRegion(new CellRangeAddress(
                                index-materialChildList.size()+1,   //起始行
                                index,   //结束行
                                7,   //起始列
                                7    //结束列
                        ));
                        sheet.addMergedRegion(new CellRangeAddress(
                                index-materialChildList.size()+1,   //起始行
                                index,   //结束行
                                8,   //起始列
                                8    //结束列
                        ));
                        sheet.addMergedRegion(new CellRangeAddress(
                                index-materialChildList.size()+1,   //起始行
                                index,   //结束行
                                9,   //起始列
                                9    //结束列
                        ));
                        sheet.addMergedRegion(new CellRangeAddress(
                                index-materialChildList.size()+1,   //起始行
                                index,   //结束行
                                10,   //起始列
                                10    //结束列
                        ));
                    }

            }
            String filename=System.currentTimeMillis()+"库存列表.xlsx";
            ServletOutputStream outputStream=response.getOutputStream();
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("content-Disposition", "attachment;filename="+new String(filename.getBytes("utf-8"),"iso-8859-1"));
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
        }



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
            storageService.insertStorage(Storageinbill,StorageinbillList);
            return    toAjax(true);
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
        storageService.updateStorage(storageoutbill,StorageoutbillList);
        return toAjax(true);
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

        storageService.quitStorage(storagequitbill,StoragequitbillList);
        return toAjax(true);
    }

    /**
     * 删除项目列表
     */
    @RequiresPermissions("system:storage:remove")
    @Log(title = "项目列表", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return storageService.deleteProjectById(Long.valueOf(ids));

    }
}
