package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Storage;
import com.ruoyi.system.domain.Storageinbill;
import com.ruoyi.system.domain.Storageoutbill;
import com.ruoyi.system.domain.Storagequitbill;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * 库存列表Service接口
 * 
 * @author ruoyi
 * @date 2020-06-03
 */
public interface IStorageService 
{



    public List<Storage> selectByBom(String comments,String footprint);

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
    public int insertStorage(Storageinbill storageinbill, String productList);

    /**
     * 出库
     * 
     * @param storageoutbill productList 库存列表
     * @return 结果
     */
    public int updateStorage(Storageoutbill storageoutbill, String productList);



    /**
     * 退库
     *
     * @param storagequitbill  productList库存列表
     * @return 结果
     */
    public int quitStorage(Storagequitbill storagequitbill, String productList);
    /**
     * 批量删除库存列表
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteStorageByIds(String ids);

    /**
     * 删除库存列表信息
     * 
     * @param id 库存列表ID
     * @return 结果
     */
    public int deleteStorageById(Integer id);


    public void fillExcelStorage(String date)
            throws Exception;
}
