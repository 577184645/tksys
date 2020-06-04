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
import com.ruoyi.system.domain.Storagetype;
import com.ruoyi.system.service.IStoragetypeService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.domain.Ztree;

/**
 * 库存类别列表Controller
 * 
 * @author ruoyi
 * @date 2020-06-03
 */
@Controller
@RequestMapping("/system/storagetype")
public class StoragetypeController extends BaseController
{
    private String prefix = "system/storagetype";

    @Autowired
    private IStoragetypeService storagetypeService;

    @RequiresPermissions("system:storagetype:view")
    @GetMapping()
    public String storagetype()
    {
        return prefix + "/storagetype";
    }

    /**
     * 查询库存类别列表树列表
     */
    @RequiresPermissions("system:storagetype:list")
    @PostMapping("/list")
    @ResponseBody
    public List<Storagetype> list(Storagetype storagetype)
    {
        List<Storagetype> list = storagetypeService.selectStoragetypeList(storagetype);
        return list;
    }

    /**
     * 导出库存类别列表列表
     */
    @RequiresPermissions("system:storagetype:export")
    @Log(title = "库存类别列表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Storagetype storagetype)
    {
        List<Storagetype> list = storagetypeService.selectStoragetypeList(storagetype);
        ExcelUtil<Storagetype> util = new ExcelUtil<Storagetype>(Storagetype.class);
        return util.exportExcel(list, "storagetype");
    }

    /**
     * 新增库存类别列表
     */
    @GetMapping(value = { "/add/{deptId}", "/add/" })
    public String add(@PathVariable(value = "deptId", required = false) Long deptId, ModelMap mmap)
    {
        if (StringUtils.isNotNull(deptId))
        {
            mmap.put("storagetype", storagetypeService.selectStoragetypeById(deptId));
        }
        return prefix + "/add";
    }

    /**
     * 新增保存库存类别列表
     */
    @RequiresPermissions("system:storagetype:add")
    @Log(title = "库存类别列表", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Storagetype storagetype)
    {
        return toAjax(storagetypeService.insertStoragetype(storagetype));
    }

    /**
     * 修改库存类别列表
     */
    @GetMapping("/edit/{deptId}")
    public String edit(@PathVariable("deptId") Long deptId, ModelMap mmap)
    {
        Storagetype storagetype = storagetypeService.selectStoragetypeById(deptId);
        mmap.put("storagetype", storagetype);
        return prefix + "/edit";
    }

    /**
     * 修改保存库存类别列表
     */
    @RequiresPermissions("system:storagetype:edit")
    @Log(title = "库存类别列表", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Storagetype storagetype)
    {
        return toAjax(storagetypeService.updateStoragetype(storagetype));
    }

    /**
     * 删除
     */
    @RequiresPermissions("system:storagetype:remove")
    @Log(title = "库存类别列表", businessType = BusinessType.DELETE)
    @GetMapping("/remove/{deptId}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("deptId") Long deptId)
    {
        if(deptId==100){
            return AjaxResult.warn("顶级父类不能删除");
        }
        if (storagetypeService.selectMaterialtypeCount(deptId) > 0)
        {
            return AjaxResult.warn("存在下级类别,不允许删除");
        }
        if (storagetypeService.checkDeptExistUser(deptId))
        {
            return AjaxResult.warn("库存类别存在库存,不允许删除");
        }
        return toAjax(storagetypeService.deleteStoragetypeById(deptId));
    }

    /**
     * 选择库存类别列表树
     */
    @GetMapping(value = { "/selectStoragetypeTree/{deptId}", "/selectStoragetypeTree/" })
    public String selectStoragetypeTree(@PathVariable(value = "deptId", required = false) Long deptId, ModelMap mmap)
    {
        if (StringUtils.isNotNull(deptId))
        {
            mmap.put("storagetype", storagetypeService.selectStoragetypeById(deptId));
        }
        return prefix + "/tree";
    }

    /**
     * 加载库存类别列表树列表
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Ztree> treeData()
    {
        List<Ztree> ztrees = storagetypeService.selectStoragetypeTree();
        return ztrees;
    }
}
