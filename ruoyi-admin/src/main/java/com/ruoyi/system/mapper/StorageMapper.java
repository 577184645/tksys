package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.Storage;

import java.util.List;
import java.util.Map;

/**
 * 库存列表Mapper接口
 * 
 * @author ruoyi
 * @date 2020-06-03
 */
public interface StorageMapper 
{



    public Storage   selectStorageByMaterialcode(String materialcode);






    public int updateStorageByMaterialcodeAndTypeId(Storage storage);


    public int updateStorageById(Storage storage);




    /**
     * 查询库存列表
     * 
     * @param id 库存列表ID
     * @return 库存列表
     */
    public Storage selectStorageById(Long id);






    /**
     * 查询当前本月的数据(出入库台账)
     *
     * @param date 库存列表
     * @return
     */

    public List<Map<String,Object>>  selectStorageByDate(String date);



    /**
     * 查询库存列表列表
     *
     * @param storage 库存列表
     * @return 库存列表集合
     */
    public List<Storage> selectStorageList(Storage storage);





    public Storage selectStorageListBymaterialcode(String materialcode);


    /**
     * 新增库存列表
     * 
     * @param storage 库存列表
     * @return 结果
     */
    public int insertStorage(Storage storage);



    /**
     * 删除库存列表
     * 
     * @param id 库存列表ID
     * @return 结果
     */
    public int deleteStorageByMaterialId(Long id);


    Storage selectStorageByMaterialId(Long id);


    /**
     * 删除项目列表信息
     *
     * @param id 项目列表ID
     * @return 结果
     */
    public int deleteStorageById(Long id);


}
