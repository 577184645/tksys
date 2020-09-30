package com.ruoyi.system.controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.vo.BomdetailVo;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.Bomdetail;
import com.ruoyi.system.service.IBomdetailService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

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

    List<BomdetailVo> bomDetailList=new ArrayList<>();

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
        ret.put("msg","sucess");
        return  ret;
    }


    @PostMapping("/importData")
    @Log(title = "bom产品列表", businessType = BusinessType.IMPORT)
    @RequiresPermissions("system:detail:import")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport, HttpServletRequest request) throws Exception
    {
        bomDetailList.clear();
        InputStream in= new ByteArrayInputStream(file.getBytes());
        POIFSFileSystem fs = new POIFSFileSystem(in);
        Workbook wb = new HSSFWorkbook(fs);
        Sheet sheet = wb.getSheetAt(0);
        int excelRealRow = com.ruoyi.system.util.ExcelUtil.getExcelRealRow(sheet);
        for (int i=4;i<excelRealRow;i++){
            Row row = sheet.getRow(i);
            if(row.getCell(0).equals("Approved")||(int)row.getCell(0).getNumericCellValue()==0){
                continue;
            }
            BomdetailVo bomdetailVo=new BomdetailVo();
            bomdetailVo.setNo((int)row.getCell(0).getNumericCellValue());
            bomdetailVo.setComment(row.getCell(1).getStringCellValue());
            bomdetailVo.setFootprint(row.getCell(2).getStringCellValue());
            bomdetailVo.setDescription(row.getCell(3).getStringCellValue());
            bomdetailVo.setDesignator(row.getCell(4).getStringCellValue());
            bomdetailVo.setQuantity((int)row.getCell(5).getNumericCellValue());
            if(row.getCell(6)!=null){
                bomdetailVo.setCount((int)row.getCell(6).getNumericCellValue());

            }
            if(row.getCell(7)!=null){
                bomdetailVo.setSumcount((int)row.getCell(7).getNumericCellValue());

            }

            bomDetailList.add(bomdetailVo);
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
