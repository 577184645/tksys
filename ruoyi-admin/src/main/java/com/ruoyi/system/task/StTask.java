package com.ruoyi.system.task;

import com.ruoyi.system.service.IStorageService;
import com.ruoyi.system.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

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


    public void  thisMonthInventoryEmail(){
        try {
            service.fillExcelStorage(DateUtil.dateToString(new Date(),"yyyy-MM-dd"));
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

}
