package com.ruoyi.system.controller;

import java.util.List;
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
import com.ruoyi.system.domain.Materialtype;
import com.ruoyi.system.service.IMaterialtypeService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.domain.Ztree;

/**
 * 物料类型列表Controller
 * 
 * @author ruoyi
 * @date 2020-06-01
 */
@Controller
@RequestMapping("/system/materialtype")
public class MaterialtypeController extends BaseController
{
    private String prefix = "system/materialtype";

    @Autowired
    private IMaterialtypeService materialtypeService;

    @RequiresPermissions("system:materialtype:view")
    @GetMapping()
    public String materialtype()
    {
        return prefix + "/materialtype";
    }

    /**
     * 查询物料类型列表树列表
     */
    @RequiresPermissions("system:materialtype:list")
    @PostMapping("/list")
    @ResponseBody
    public List<Materialtype> list(Materialtype materialtype)
    {
        List<Materialtype> list = materialtypeService.selectMaterialtypeList(materialtype);
        return list;
    }

    /**
     * 导出物料类型列表列表
     */
    @RequiresPermissions("system:materialtype:export")
    @Log(title = "物料类型列表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Materialtype materialtype)
    {
        List<Materialtype> list = materialtypeService.selectMaterialtypeList(materialtype);
        ExcelUtil<Materialtype> util = new ExcelUtil<Materialtype>(Materialtype.class);
        return util.exportExcel(list, "materialtype");
    }

    /**
     * 新增物料类型列表
     */
    @GetMapping(value = { "/add/{deptId}", "/add/" })
    public String add(@PathVariable(value = "deptId", required = false) Long deptId, ModelMap mmap)
    {
        if (StringUtils.isNotNull(deptId))
        {
            mmap.put("materialtype", materialtypeService.selectMaterialtypeById(deptId));
        }
        return prefix + "/add";
    }

    /**
     * 新增保存物料类型列表
     */
    @RequiresPermissions("system:materialtype:add")
    @Log(title = "物料类型列表", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Materialtype materialtype)
    {
        return toAjax(materialtypeService.insertMaterialtype(materialtype));
    }

    /**
     * 修改物料类型列表
     */
    @GetMapping("/edit/{deptId}")
    public String edit(@PathVariable("deptId") Long deptId, ModelMap mmap)
    {
        Materialtype materialtype = materialtypeService.selectMaterialtypeById(deptId);
        mmap.put("materialtype", materialtype);
        return prefix + "/edit";
    }

    /**
     * 修改保存物料类型列表
     */
    @RequiresPermissions("system:materialtype:edit")
    @Log(title = "物料类型列表", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Materialtype materialtype)
    {
        return toAjax(materialtypeService.updateMaterialtype(materialtype));
    }

    /**
     * 删除
     */
    @RequiresPermissions("system:materialtype:remove")
    @Log(title = "物料类型列表", businessType = BusinessType.DELETE)
    @GetMapping("/remove/{deptId}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("deptId") Long deptId)
    {
        if(deptId==100){
            return AjaxResult.warn("顶级父类不能删除");
        }
        if (materialtypeService.selectMaterialtypeCount(deptId) > 0)
        {
            return AjaxResult.warn("存在下级类别,不允许删除");
        }
        if (materialtypeService.checkDeptExistUser(deptId))
        {
            return AjaxResult.warn("物料类别存在物料,不允许删除");
        }
        return toAjax(materialtypeService.deleteMaterialtypeById(deptId));
    }

    /**
     * 选择物料类型列表树
     */
    @GetMapping(value = { "/selectMaterialtypeTree/{deptId}", "/selectMaterialtypeTree/" })
    public String selectMaterialtypeTree(@PathVariable(value = "deptId", required = false) Long deptId, ModelMap mmap)
    {
        if (StringUtils.isNotNull(deptId))
        {
            mmap.put("materialtype", materialtypeService.selectMaterialtypeById(deptId));
        }
        return prefix + "/tree";
    }

    /**
     * 加载物料类型列表树列表
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Ztree> treeData()
    {
        List<Ztree> ztrees = materialtypeService.selectMaterialtypeTree();
        return ztrees;
    }
}
