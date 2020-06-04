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
import com.ruoyi.system.domain.Testss;
import com.ruoyi.system.service.ITestssService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 测试Controller
 * 
 * @author ruoyi
 * @date 2020-06-01
 */
@Controller
@RequestMapping("/system/testss")
public class TestssController extends BaseController
{
    private String prefix = "system/testss";

    @Autowired
    private ITestssService testssService;

    @RequiresPermissions("system:testss:view")
    @GetMapping()
    public String testss()
    {
        return prefix + "/testss";
    }

    /**
     * 查询测试列表
     */
    @RequiresPermissions("system:testss:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Testss testss)
    {
        startPage();
        List<Testss> list = testssService.selectTestssList(testss);
        return getDataTable(list);
    }


    @PostMapping("/importData")
    @RequiresPermissions("system:testss:import")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<Testss> util = new ExcelUtil<Testss>(Testss.class);
        List<Testss> testsses  = util.importExcel(file.getInputStream());
        String message = testssService.importUser(testsses);
        return AjaxResult.success(message);
    }


    @RequiresPermissions("system:testss:view")
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<Testss> util = new ExcelUtil<Testss>(Testss.class);
        return util.importTemplateExcel("销售商品数据");
    }

    /**
     * 导出测试列表
     */
    @RequiresPermissions("system:testss:export")
    @Log(title = "测试", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Testss testss)
    {
        List<Testss> list = testssService.selectTestssList(testss);
        ExcelUtil<Testss> util = new ExcelUtil<Testss>(Testss.class);
        return util.exportExcel(list, "testss");
    }

    /**
     * 新增测试
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存测试
     */
    @RequiresPermissions("system:testss:add")
    @Log(title = "测试", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Testss testss)
    {
        return toAjax(testssService.insertTestss(testss));
    }

    /**
     * 修改测试
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Testss testss = testssService.selectTestssById(id);
        mmap.put("testss", testss);
        return prefix + "/edit";
    }

    /**
     * 修改保存测试
     */
    @RequiresPermissions("system:testss:edit")
    @Log(title = "测试", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Testss testss)
    {
        return toAjax(testssService.updateTestss(testss));
    }

    /**
     * 删除测试
     */
    @RequiresPermissions("system:testss:remove")
    @Log(title = "测试", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(testssService.deleteTestssByIds(ids));
    }
}
