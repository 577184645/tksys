package com.ruoyi.system.task;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.Bom;
import com.ruoyi.system.mapper.StorageMapper;
import com.ruoyi.system.service.IBomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("stTask")
public class StTask {


    public void  deleteZerostorage(){
       /* Bom bom=new Bom();

            List<Bom> list = bomService.selectBomList(bom);
            ExcelUtil<Bom> util = new ExcelUtil<Bom>(Bom.class);
             util.exportExcel(list, "bom");*/

        System.out.println("不启动删除程序");
       /* storageMapper.deleteStorageByStocksZero();*/
    }
}
