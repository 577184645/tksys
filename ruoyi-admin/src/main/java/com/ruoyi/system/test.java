package com.ruoyi.system;

import com.ruoyi.system.mapper.WarehouseRecordMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author QC
 * @create 2020-09-10 11:18
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class test {
    @Autowired
    private WarehouseRecordMapper warehouseRecordMapper;


    @Test
    public void show(){
        System.out.println(1);
        System.out.println(        warehouseRecordMapper.selectWarehouseRecordList(null).size());
    }
}
