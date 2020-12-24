package com.ruoyi.system.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.Material;
import com.ruoyi.system.domain.Materialtype;
import com.ruoyi.system.service.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
        if(deptId!=""&&typeId!=""){
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
    public AjaxResult remove(String ids)
    {
        return materialService.deleteMaterialByIds(ids);
    }
}
