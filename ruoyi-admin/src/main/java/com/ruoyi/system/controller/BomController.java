package com.ruoyi.system.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.Bom;
import com.ruoyi.system.domain.Bomdetail;
import com.ruoyi.system.service.IBomService;
import com.ruoyi.system.service.IBomdetailService;
import com.ruoyi.system.service.IProjectService;
import org.apache.poi.ss.usermodel.*;
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
 * bom列表Controller
 *
 * @author ruoyi
 * @date 2020-07-24
 */
@Controller
@RequestMapping("/system/bom")
public class BomController extends BaseController {
    private String prefix = "system/bom";

    @Autowired
    private IBomService bomService;
    @Autowired
    private IProjectService iProjectService;
    @Autowired
    private IBomdetailService bomdetailService;


    @RequiresPermissions("system:bom:view")
    @GetMapping()
    public String bom() {

        return prefix + "/bom";
    }

    /**
     * 查询bom列表列表
     */
    @RequiresPermissions("system:bom:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Bom bom) {
        startPage();
        List<Bom> list = bomService.selectBomList(bom);
        return getDataTable(list);
    }

    /**
     * 导出bom列表列表
     */
    @RequiresPermissions("system:bom:export")
    @Log(title = "bom列表", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    @ResponseBody
    public AjaxResult export(Long id, String date, String version, String name, HttpServletResponse response) {
        // 本地资源路径
        try {
            Bomdetail bomdetail = new Bomdetail();
            bomdetail.setBomid(id);
            List<Bomdetail> bomdetails = bomdetailService.selectBomdetailList(bomdetail);
            String file = Global.getProfile() + "/template/BOM_exportTemplate.xlsx";
            XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(new File(file)));
            XSSFSheet sheetAt = workbook.getSheetAt(0);
            sheetAt.getRow(0).getCell(4).setCellValue(name);
            sheetAt.getRow(1).getCell(4).setCellValue(version);
            sheetAt.getRow(2).getCell(4).setCellValue(date);
            int index = 5;
            Font font = workbook.createFont();
            font.setFontName("黑体");
            font.setFontHeightInPoints((short) 24);//设置字体大小
            Font font1 = workbook.createFont();
            font.setFontHeightInPoints((short) 16);
            CellStyle cellStyle2 = workbook.createCellStyle();
            cellStyle2.setAlignment(HorizontalAlignment.CENTER);
            cellStyle2.setVerticalAlignment(VerticalAlignment.CENTER);
            cellStyle2.setFont(font1);
            cellStyle2.setWrapText(true);
            //获得当前sheet的开始行
            int firstRowNum = sheetAt.getFirstRowNum();
            //获得当前sheet的结束行
            int lastRowNum = sheetAt.getLastRowNum();
            System.out.println(firstRowNum);
            System.out.println(lastRowNum);
            for (int i = 0; i < bomdetails.size(); i++) {
                XSSFRow row = sheetAt.createRow(index++);
                row.createCell(0).setCellValue(i + 1);
                row.createCell(1).setCellValue(bomdetails.get(i).getCode() != null ? bomdetails.get(i).getCode().toString() : "");
                row.createCell(2).setCellValue(bomdetails.get(i).getLink() != null ? bomdetails.get(i).getLink().toString() : "");
                row.createCell(3).setCellValue(bomdetails.get(i).getComment() != null ? bomdetails.get(i).getComment().toString() : "");
                row.createCell(4).setCellValue(bomdetails.get(i).getFootprint() != null ? bomdetails.get(i).getFootprint().toString() : "");
                row.createCell(5).setCellValue(bomdetails.get(i).getDescription() != null ? bomdetails.get(i).getDescription().toString() : "");
                row.createCell(6).setCellValue(bomdetails.get(i).getParttype() != null ? bomdetails.get(i).getParttype().toString() : "");
                row.createCell(7).setCellValue(bomdetails.get(i).getDesignator() != null ? bomdetails.get(i).getDesignator().toString() : "");
                row.createCell(8).setCellValue(bomdetails.get(i).getQuantity() != null ? bomdetails.get(i).getQuantity().toString() : "");
                for (Cell cell : row) {
                    cell.setCellStyle(cellStyle2);
                }
            }
            ServletOutputStream outputStream = response.getOutputStream();
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("content-Disposition", "attachment;filename=BOM_" + name + ".xlsx");
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }


    @GetMapping("/upgrade/{id}")
    public String upgrade(ModelMap mmap, @PathVariable("id") Long id) {
        Bom bom = bomService.selectBomById(id);
        mmap.put("bom", bom);
        return prefix + "/upgrade";
    }

    /**
     * 新增bom列表
     */
    @GetMapping("/add")
    public String add(ModelMap mmap) {
        mmap.put("projectList", iProjectService.selectProjectList(null));
        return prefix + "/add";
    }

    /**
     * 新增保存bom列表
     */
    @RequiresPermissions("system:bom:add")
    @Log(title = "bom列表", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(String bomList, Bom bom) {

        return toAjax(bomService.addBom(bomList, bom));
    }

    /**
     * 修改bom列表
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        Bom bom = bomService.selectBomById(id);
        mmap.put("projectList", iProjectService.selectProjectList(null));
        mmap.put("bom", bom);
        return prefix + "/edit";
    }

    @RequiresPermissions("system:bom:info")
    @GetMapping("/info/{id}")
    public String info(@PathVariable("id") Long id, ModelMap mmap) {
        Bom bom = bomService.selectBomById(id);
        mmap.put("projectList", iProjectService.selectProjectList(null));
        mmap.put("bom", bom);
        return prefix + "/info";
    }


    @GetMapping("/userinfo/{id}")
    public String userinfo(@PathVariable("id") Long id, ModelMap mmap) {
        Bom bom = bomService.selectBomById(id);
        mmap.put("projectList", iProjectService.selectProjectList(null));
        mmap.put("bom", bom);
        return prefix + "/userinfo";
    }

    /**
     * 删除bom列表
     */
    @RequiresPermissions("system:bom:remove")
    @Log(title = "bom列表", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(bomService.deleteBomById(Long.valueOf(ids)));
    }


    @PostMapping("/refresh")
    @ResponseBody
    @Log(title = "bom刷新", businessType = BusinessType.UPDATE)
    public AjaxResult refresh(String ids) {
        return toAjax(bomService.refresh(ids));
    }
}