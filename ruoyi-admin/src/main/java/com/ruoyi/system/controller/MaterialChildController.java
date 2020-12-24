package com.ruoyi.system.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.MaterialChild;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.IMaterialChildService;
import com.ruoyi.system.service.ISysUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 物料Controller
 *
 * @author ruoyi
 * @date 2020-12-14
 */
@Controller
@RequestMapping("/system/materialChild")
public class MaterialChildController extends BaseController
{
    private String prefix = "system/materialChild";

    @Autowired
    private IMaterialChildService materialChildService;
    @Autowired
    private ISysUserService iSysUserService;

    @RequiresPermissions("system:materialChild:view")
    @GetMapping()
    public String child()
    {
        return prefix + "/materialChild";
    }

    /**
     * 查询物料列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(MaterialChild materialChild)
    {
        startPage();
        List<MaterialChild> list = materialChildService.selectMaterialChildList(materialChild);
        return getDataTable(list);
    }

    /**
     * 导出物料列表
     */
    @Log(title = "物料", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(MaterialChild materialChild)
    {
        List<MaterialChild> list = materialChildService.selectMaterialChildList(materialChild);
        ExcelUtil<MaterialChild> util = new ExcelUtil<MaterialChild>(MaterialChild.class);
        return util.exportExcel(list, "materialChild");
    }

    /**
     * 新增物料
     */
    @GetMapping("/add/{id}")
    public String add(@PathVariable("id") Long id,ModelMap mmap)
    {
        SysUser user = ShiroUtils.getSysUser();
        mmap.put("materialId",id);
        mmap.put("userName", user.getUserName());
        mmap.put("userList",iSysUserService.findList());
        return prefix + "/add";
    }

    /**
     * 新增保存物料
     */
    @Log(title = "物料", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(MaterialChild materialChild)
    {
        return toAjax(materialChildService.insertMaterialChild(materialChild));
    }

    /**
     * 修改物料
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
        SysUser user = ShiroUtils.getSysUser();
        mmap.put("userName", user.getUserName());
        mmap.put("userList",iSysUserService.findList());
        MaterialChild materialChild = materialChildService.selectMaterialChildById(id);
        mmap.put("materialChild", materialChild);
        return prefix + "/edit";
    }

    /**
     * 修改保存物料
     */
    @Log(title = "物料", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(MaterialChild materialChild)
    {
        return toAjax(materialChildService.updateMaterialChild(materialChild));
    }

    /**
     * 删除物料
     */

    @Log(title = "物料", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(materialChildService.deleteMaterialChildByIds(ids));
    }
}
