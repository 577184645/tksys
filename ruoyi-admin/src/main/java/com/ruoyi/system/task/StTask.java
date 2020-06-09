package com.ruoyi.system.task;

import com.ruoyi.system.mapper.StorageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component("stTask")
public class StTask {
    @Autowired
    private StorageMapper storageMapper;
    public void  deleteZerostorage(){
        storageMapper.deleteStorageByStocksZero();
    }
}
