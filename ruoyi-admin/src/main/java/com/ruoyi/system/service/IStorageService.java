package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.Storage;
import com.ruoyi.system.domain.Storageinbill;
import com.ruoyi.system.domain.Storageoutbill;
import com.ruoyi.system.domain.Storagequitbill;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 库存列表Service接口
 * 
 * @author ruoyi
 * @date 2020-06-03
 */
public interface IStorageService 
{

    public void exportStorage(List<Storage> list, HttpServletResponse response) ;




    public Storage selectStorageListBymaterialcode(String materialcode);

    /**
     * 查询库存列表
     * 
     * @param id 库存列表ID
     * @return 库存列表
     */
    public Storage selectStorageById(Long id);

    /**
     * 查询库存列表列表
     * 
     * @param storage 库存列表
     * @return 库存列表集合
     */
    public List<Storage> selectStorageList(Storage storage);

    /**
     * 入库
     * 
     * @param storageinbill  productList库存列表
     * @return 结果
     */
    public void insertStorage(Storageinbill storageinbill, String productList);

    /**
     * 出库
     * 
     * @param storageoutbill productList 库存列表
     * @return 结果
     */
    public void updateStorage(Storageoutbill storageoutbill, String productList);



    /**
     * 退库
     *
     * @param storagequitbill  productList库存列表
     * @return 结果
     */
    public void quitStorage(Storagequitbill storagequitbill, String productList);



    public List<Map<String, Object>> fillExcelStorage(String begindate,String enddate);


    /**
     * 删除项目列表信息
     *
     * @param id 项目列表ID
     * @return 结果
     */
    public AjaxResult deleteProjectById(Long id);


    int settingstock(Storage storage);
}
