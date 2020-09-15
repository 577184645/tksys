package com.ruoyi.system.task;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.Bom;
import com.ruoyi.system.mapper.StorageMapper;
import com.ruoyi.system.service.IBomService;
import com.ruoyi.system.service.IStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

@Component("stTask")
public class StTask {

  @Autowired
  private IStorageService service;

    public void  deleteZerostorage(){
       /* Bom bom=new Bom();

            List<Bom> list = bomService.selectBomList(bom);
            ExcelUtil<Bom> util = new ExcelUtil<Bom>(Bom.class);
             util.exportExcel(list, "bom");*/

        System.out.println("不启动删除程序");
       /* storageMapper.deleteStorageByStocksZero();*/
    }


    SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd");
    public void  thisMonthInventoryEmail(){

        try {
            service.fillExcelStorage(sd.format(new Date()));
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

}
