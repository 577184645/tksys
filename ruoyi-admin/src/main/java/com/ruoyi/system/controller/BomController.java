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
import com.ruoyi.system.service.IStorageService;
import com.ruoyi.system.util.POIUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

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
    @Autowired
    private IStorageService iStorageService;


    private List<Map<String,Object>> mapList=new ArrayList<>();

    @RequiresPermissions("system:bom:view")
    @GetMapping()
    public String bom() {

        return prefix + "/bom";
    }

    @RequiresPermissions("system:bom:merge")
    @GetMapping("/merge")
    public String merge() {

        return prefix + "/merge";
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
    public AjaxResult export(Bom bom, HttpServletResponse response) {
        // 本地资源路径
        try {
            Bomdetail bomdetail = new Bomdetail();
            bomdetail.setBomid(bom.getId());
            List<Bomdetail> bomdetails = bomdetailService.selectBomdetailList(bomdetail);
            String file = Global.getProfile() + "/template/BOM_exportTemplate.xlsx";
            XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(file));
            XSSFSheet sheetAt = workbook.getSheetAt(0);
            CellStyle cellStyle = workbook.createCellStyle();
            CreationHelper createHelper = workbook.getCreationHelper();
            cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-MM-dd"));
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
            cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            cellStyle.setWrapText(true);
            sheetAt.getRow(0).getCell(4).setCellValue(bom.getName());
            sheetAt.getRow(1).getCell(4).setCellValue(bom.getVersion());
            sheetAt.getRow(2).getCell(4).setCellValue(bom.getTimeofmaking());
            sheetAt.getRow(2).getCell(4).setCellStyle(cellStyle);
            sheetAt.getRow(0).getCell(7).setCellValue(bom.getNumber());
            sheetAt.getRow(1).getCell(7).setCellValue(bom.getPartnumber());
            sheetAt.getRow(2).getCell(7).setCellValue(bom.getRemark());
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
                row.createCell(9).setCellValue(bomdetails.get(i).getPrice() != null ? bomdetails.get(i).getPrice().toString() : "");

                for (Cell cell : row) {
                    cell.setCellStyle(cellStyle2);
                }
            }
            ServletOutputStream outputStream = response.getOutputStream();
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("content-Disposition", "attachment;filename=BOM_" + new String(bom.getPartnumber().getBytes("utf-8"), "iso8859-1") + ".xlsx");
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

    @PostMapping("/scrap")
    @ResponseBody
    @Log(title = "bom报废", businessType = BusinessType.UPDATE)
    public AjaxResult scrap(String ids) {
        return toAjax(bomService.scrap(ids));
    }

    @PostMapping("/importData")
    @Log(title = "bom合并", businessType = BusinessType.IMPORT)
    @ResponseBody
    public boolean merge(MultipartFile []  file, HttpServletResponse response, HttpServletRequest request) throws Exception {
        mapList.clear();
        try {

            for (MultipartFile multipartFile : file) {

                Workbook wb = POIUtils.getWorkBook(multipartFile);
                Sheet sheet = wb.getSheetAt(0);
               int count= (int)sheet. getRow(1).getCell(9).getNumericCellValue();
                int excelRealRow = POIUtils.getExcelRealRow(sheet);
                for (int i = 5; i <= excelRealRow; i++) {
                    Row row = sheet.getRow(i);
                    if (StringUtils.isBlank(POIUtils.getCellValue(row.getCell(0)))) {
                        break;
                    }
                    if (StringUtils.isBlank(POIUtils.getCellValue(row.getCell(1)))) {
                        return false;
                    }
                    Map<String,Object> map=new HashMap<>();
                    String code = POIUtils.getCellValue(row.getCell(1)).replaceAll(" ", "");
                    map.put("code",code);
                    map.put("link",POIUtils.getCellValue(row.getCell(2)));
                    map.put("comment",POIUtils.getCellValue(row.getCell(3)));
                    map.put("footprint",POIUtils.getCellValue(row.getCell(4)));
                    map.put("description",POIUtils.getCellValue(row.getCell(5)));
                    map.put("parttype",POIUtils.getCellValue(row.getCell(6)));
                    map.put("designator",POIUtils.getCellValue(row.getCell(7)));
                    map.put("quantity",POIUtils.getCellValue(row.getCell(8)));
                    map.put("price",POIUtils.getCellValue(row.getCell(9)));
                    map.put("total",Integer.valueOf(map.get("quantity").toString())*count);
                    mapList.add(map);
                }
              
            }

            HashMap<String, Map<String, Object>> tempMap = new HashMap<String, Map<String, Object>>();
            for (Map<String, Object> map : mapList) {
                String code = map.get("code").toString();
                if(tempMap.containsKey(code)){
                  int tatal= (int)tempMap.get(code).get("total")+(int)map.get("total");
                    map.put("total",tatal);
                    tempMap.put(code,map);
                }else{
                    tempMap.put(code,map);
                }
            }
            mapList.clear();
            for (String s : tempMap.keySet()) {
                mapList.add(tempMap.get(s));
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @GetMapping("download")
    public void download(HttpServletResponse response){
        try {
            String filetemp = Global.getProfile() + "/template/BOM_merge.xlsx";
            XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(filetemp));
            XSSFSheet sheetAt = workbook.getSheetAt(0);
            CellStyle cellStyle = workbook.createCellStyle();
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
            cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            cellStyle.setWrapText(true);
            int index=0;
            for (Map<String, Object> map : mapList) {
                XSSFRow row = sheetAt.createRow(++index);
                row.createCell(0).setCellValue(index);
                row.createCell(1).setCellValue(map.get("code") != null ?map.get("code").toString() : "");
                row.createCell(2).setCellValue(map.get("link") != null ? map.get("link").toString() : "");
                row.createCell(3).setCellValue(map.get("comment")!= null ? map.get("comment").toString() : "");
                row.createCell(4).setCellValue(map.get("footprint")!= null ? map.get("footprint").toString() : "");
                row.createCell(5).setCellValue(map.get("description") != null ? map.get("description").toString() : "");
                row.createCell(6).setCellValue(map.get("parttype") != null ? map.get("parttype").toString() : "");
                row.createCell(7).setCellValue(map.get("designator") != null ? map.get("designator").toString() : "");
                row.createCell(8).setCellValue(map.get("total") != null ? map.get("total").toString() : "");
                row.createCell(9).setCellValue(map.get("price") != null ? map.get("price").toString() : "");
                for (Cell cell : row) {
                    cell.setCellStyle(cellStyle);
                }
            }
            mapList.clear();
            ServletOutputStream outputStream = response.getOutputStream();
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("content-Disposition", "attachment;filename="+System.currentTimeMillis()+"BOM_merge.xlsx");
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
            workbook.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}