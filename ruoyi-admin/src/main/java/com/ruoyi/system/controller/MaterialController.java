package com.ruoyi.system.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.Material;
import com.ruoyi.system.domain.MaterialChild;
import com.ruoyi.system.domain.Materialtype;
import com.ruoyi.system.service.IMaterialService;
import com.ruoyi.system.service.IMaterialdeptService;
import com.ruoyi.system.service.IMaterialtypeService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 物料列表Controller
 * 
 * @author ruoyi
 * @date 2020-06-01
 */
@Controller
@RequestMapping("/system/material")
public class MaterialController extends BaseController
{
    private String prefix = "system/material";

    @Autowired
    private IMaterialService materialService;

    @Autowired
    private IMaterialtypeService iMaterialtypeService;
    @Autowired
    private IMaterialdeptService imaterialdeptService;





    @RequestMapping("/find")
    @ResponseBody
    public Map<String ,Object> find(Material material)
{
    Map<String,Object> map=new HashMap<>();
    List<Material> list = materialService.selectMaterialList(material);
    map.put("rows",list);
    map.put("total",list.size());
    return map;

}


    @PostMapping("/importData")
    @RequiresPermissions("system:material:import")
    @Log(title = "物料列表", businessType = BusinessType.IMPORT)
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {

        ExcelUtil<Material> util = new ExcelUtil<Material>(Material.class);
        List<Material> material  = util.importExcel(file.getInputStream());
        String message = materialService.importMaterial(material);
        return AjaxResult.success(message);
    }




    @RequiresPermissions("system:material:view")
    @GetMapping()
    public String material(ModelMap mmap)
    {
        mmap.put("materialdeptList",imaterialdeptService.selectMaterialdeptList(null));
        mmap.put("materialtypeList",iMaterialtypeService.selectMaterialtypeList(null));

        return prefix + "/material";
    }




    /**
     * 查询物料列表列表
     */

    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Material material)
    {
        startPage();
        List<Material> list = materialService.selectMaterialList(material);
        return getDataTable(list);
    }

    /**
     * 查询物料列表列表
     */
    @GetMapping("/getMaterialcode")
    @ResponseBody
    public Map<String,Object> getMaterialcode(@RequestParam(name = "typeId",required = false,defaultValue = "") String typeId,@RequestParam(name = "deptId",required = false,defaultValue = "") String deptId){
      Map<String,Object> ret=new HashMap<>();
        if(StringUtils.isNotBlank(deptId)&&StringUtils.isNotBlank(typeId)){
            ret.put("materialcode", materialService.getMaterialcode(Long.valueOf(typeId),Long.valueOf(deptId)));
      }
        return ret;
    }





    /**
     * 新增物料列表
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
        List<Materialtype> materialtypes = iMaterialtypeService.selectMaterialtypeList(null);
        //删除父节点
        materialtypes.remove(0);
        mmap.put("materialtypeList",materialtypes);
        mmap.put("materialdeptList",imaterialdeptService.selectMaterialdeptList(null));
        return prefix + "/add";
    }

    /**
     * 新增保存物料列表
     */
    @RequiresPermissions("system:material:add")
    @Log(title = "物料列表", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Material material)
    {
        return toAjax(materialService.insertMaterial(material));
    }













    /**
     * 删除物料列表
     */
    @RequiresPermissions("system:material:remove")
    @Log(title = "物料列表", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(Long id)
    {
        try {
            return materialService.deleteMaterial(id);
        }catch (Exception e){
            e.printStackTrace();
            return  AjaxResult.error("删除失败!");
        }

    }


    /**
     * 废弃物料
     */
    @RequiresPermissions("system:material:abandoned")
    @Log(title = "物料列表", businessType = BusinessType.UPDATE)
    @PostMapping( "/abandoned")
    @ResponseBody
    public AjaxResult abandoned(String ids)
    {
        try {
            return materialService.abandoned(ids);
        }catch (Exception e){
            e.printStackTrace();
            return  AjaxResult.error("操作失败!");
        }

    }

    /**
     * 恢复物料
     */
    @RequiresPermissions("system:material:recovery")
    @Log(title = "物料列表", businessType = BusinessType.UPDATE)
    @PostMapping( "/recovery")
    @ResponseBody
    public AjaxResult recovery(String ids)
    {
        try {
            return materialService.recovery(ids);
        }catch (Exception e){
            e.printStackTrace();
            return  AjaxResult.error("操作失败!");
        }

    }


    /**
     * 导出库存列表列表
     */
    @RequiresPermissions("system:material:export")
    @Log(title = "物料列表", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public void export(Material material, HttpServletResponse response)
    {
        try {
            List<Material> materials = materialService.selectMaterialList(material);
            XSSFWorkbook workbook=new XSSFWorkbook();
            Sheet sheet=workbook.createSheet("物料列表");
            Row row = sheet.createRow(0);
            String [] titleName={"物料编码","名称","规格型号","描述"};
            CellStyle cellStyle2=workbook.createCellStyle();
            cellStyle2.setAlignment(HorizontalAlignment.CENTER);
            cellStyle2.setVerticalAlignment(VerticalAlignment.CENTER);
            cellStyle2.setWrapText(true);
            sheet.setColumnWidth(0, 4000);
            sheet.setColumnWidth(1, 4000);
            sheet.setColumnWidth(2, 6000);
            sheet.setColumnWidth(3, 6000);
            for (int i = 0; i < titleName.length; i++) {
                row.createCell(i).setCellValue(titleName[i]);

                row.getCell(i).setCellStyle(cellStyle2);
            }
            int index=0;
            for (int i = 0; i < materials.size(); i++) {

                Row row1 = sheet.createRow(++index);
                row1.createCell(0).setCellValue(materials.get(i).getMaterialcode());
                List<MaterialChild> materialChildList = materials.get(i).getMaterialChildList();
                if(materialChildList.size()>0){
                    row1.createCell(1).setCellValue(materialChildList.get(0).getName());
                    row1.createCell(2).setCellValue(materialChildList.get(0).getPartnumber());
                    row1.createCell(3).setCellValue(materialChildList.get(0).getDescription());
                    for (Cell cell : row1) {
                        cell.setCellStyle(cellStyle2);
                    }
                }
                if(materialChildList.size()>1){
                    for (int i1 = 1; i1 < materialChildList.size(); i1++) {
                        index++;
                        Row row2 = sheet.createRow(index);
                        row2.createCell(1).setCellValue(materialChildList.get(i1).getName());
                        row2.createCell(2).setCellValue(materialChildList.get(i1).getPartnumber());
                        row2.createCell(3).setCellValue(materialChildList.get(i1).getDescription());
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
                }

            }
            String filename="物料列表";
            ServletOutputStream outputStream=response.getOutputStream();
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("content-Disposition", "attachment;filename="+new String(filename.getBytes("utf-8"),"iso-8859-1")+".xlsx");
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
