package com.ruoyi.system.controller;

import com.ruoyi.common.core.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: qincan
 * @create: 2021-04-02 9:47
 * @description:
 * @version: 1.0
 */

@Controller
@RequestMapping("/system/backup")
public class BackupController extends BaseController {
    private String prefix = "system/backup";

    @GetMapping()
    public String bom() {
        return prefix + "/backup";
    }


}