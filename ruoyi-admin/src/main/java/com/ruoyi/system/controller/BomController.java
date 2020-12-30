package com.ruoyi.system.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.Bom;
import com.ruoyi.system.service.IBomService;
import com.ruoyi.system.service.IProjectService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * bom列表Controller
 * 
 * @author ruoyi
 * @date 2020-07-24
 */
@Controller
@RequestMapping("/system/bom")
public class BomController extends BaseController
{
    private String prefix = "system/bom";

    @Autowired
    private IBomService bomService;
    @Autowired
    private IProjectService iProjectService;






    @RequiresPermissions("system:bom:view")
    @GetMapping()
    public String bom()
    {

        return prefix + "/bom";
    }

    /**
     * 查询bom列表列表
     */
    @RequiresPermissions("system:bom:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Bom bom)
    {
        startPage();
        List<Bom> list = bomService.selectBomList(bom);
        return getDataTable(list);
    }

    /**
     * 导出bom列表列表
     */
    @RequiresPermissions("system:bom:export")
    @Log(title = "bom列表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Bom bom)
    {

        List<Bom> list = bomService.selectBomList(bom);
        ExcelUtil<Bom> util = new ExcelUtil<Bom>(Bom.class);
        return util.exportExcel(list, "bom");

    }



    @GetMapping("/upgrade/{id}")
    public String upgrade(ModelMap mmap, @PathVariable("id") Long id)
    {
        Bom bom = bomService.selectBomById(id);
        mmap.put("bom",bom);
        return prefix + "/upgrade";
    }

    /**
     * 新增bom列表
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
        mmap.put("projectList",iProjectService.selectProjectList(null));
        return prefix + "/add";
    }

    /**
     * 新增保存bom列表
     */
    @RequiresPermissions("system:bom:add")
    @Log(title = "bom列表", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(String bomList,Bom bom)
    {

        return toAjax(bomService.addBom(bomList,bom));
    }

    /**
     * 修改bom列表
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Bom bom = bomService.selectBomById(id);
        mmap.put("projectList",iProjectService.selectProjectList(null));
        mmap.put("bom", bom);
        return prefix + "/edit";
    }

    @RequiresPermissions("system:bom:info")
    @GetMapping("/info/{id}")
    public String info(@PathVariable("id") Long id, ModelMap mmap)
    {
        Bom bom = bomService.selectBomById(id);
        mmap.put("projectList",iProjectService.selectProjectList(null));
        mmap.put("bom", bom);
        return prefix + "/info";
    }


    @GetMapping("/userinfo/{id}")
    public String userinfo(@PathVariable("id") Long id, ModelMap mmap)
    {
        Bom bom = bomService.selectBomById(id);
        mmap.put("projectList",iProjectService.selectProjectList(null));
        mmap.put("bom", bom);
        return prefix + "/userinfo";
    }

    /**
     * 修改保存bom列表
     */
    @RequiresPermissions("system:bom:edit")
    @Log(title = "bom列表", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(String bomList,Bom bom)
    {
        return toAjax(bomService.editBom(bomList,bom));
    }

    /**
     * 删除bom列表
     */
    @RequiresPermissions("system:bom:remove")
    @Log(title = "bom列表", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(bomService.deleteBomById(Long.valueOf(ids )));
    }
}
