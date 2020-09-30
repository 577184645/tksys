package com.ruoyi.system.mapper;

import java.util.Date;
import java.util.List;
import com.ruoyi.system.domain.Storage;
import com.ruoyi.vo.WarehouseBillVo;
import org.apache.ibatis.annotations.Param;

/**
 * 库存列表Mapper接口
 * 
 * @author ruoyi
 * @date 2020-06-03
 */
public interface StorageMapper 
{
    /**
     * 查询库存下是否有产品
     *
     * @param storage
     * @return 库存列表
     */
    public int selectStorageByMaterialcodeAndTypeid(Storage storage);


    public Storage   selectStorageByMaterialcode(String materialcode);


    public Storage   selectStorageInfoByMaterialcodeAndTypeid(Storage storage);

    public int updateMaterial(@Param("name") String name,@Param("materialcode") String materialcode,@Param("partnumber") String partnumber,@Param("footprint") String footprint,@Param("unit") String unit,@Param("manufacture") String manufacture,@Param("oldmaterialcode") String oldmaterialcode);

    /**
     * 入库
     * 添加库存数量
     *
     * @param  storage
     * @return 库存列表
     */
    public int updatestocks(Storage storage);


    /**
     *
     * 减少库存数量
     *
     * @param  storage
     * @return 库存列表
     */
    public int updatereducestocks(Storage storage);


    /**
     *
     * 增加库存数量
     *
     * @param  storage
     * @return 库存列表
     */
    public int updateaddstocks(Storage storage);

    /**
     * 退库
     *
     * @param  storage
     * @return 库存列表
     */
    public int quitstocks(Storage storage);



    /**
     * 出库
     * 减少库存数量
     *
     * @param  storage
     * @return 库存列表
     */
    public int removestocks(Storage storage);




    /**
     * 查询库存列表
     * 
     * @param id 库存列表ID
     * @return 库存列表
     */
    public Storage selectStorageById(Long id);



    public List<Storage> selectByBom(@Param("comments") String comments,@Param("footprint") String footprint);

    /**
     * 查询当前本月的数据
     *
     * @param date 库存列表
     * @return
     */

    public List<WarehouseBillVo> selectStoragebyDate(Date date);


    public List<WarehouseBillVo> selectStoragebyDatedashed(Date date);


    public List<WarehouseBillVo> selectoutStoragebyDate(Date date);


    public List<WarehouseBillVo> selectoutStoragebyDatedashed(Date date);


    /**
     * 查询库存列表列表
     *
     * @param storage 库存列表
     * @return 库存列表集合
     */
    public List<Storage> selectStorageList(Storage storage);


    public List<Storage> StoragefindList();


    public Storage selectStorageListBymaterialcode(String materialcode);


    /**
     * 新增库存列表
     * 
     * @param storage 库存列表
     * @return 结果
     */
    public int insertStorage(Storage storage);

    /**
     * 修改库存列表
     * 
     * @param storage 库存列表
     * @return 结果
     */
    public int updateStorage(Storage storage);

    /**
     * 删除库存列表
     * 
     * @param id 库存列表ID
     * @return 结果
     */
    public int deleteStorageById(Integer id);

    /**
     * 删除库存为0的产品
     * @return
     */
    public int deleteStorageByStocksZero( );


    /**
     * 批量删除库存列表
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteStorageByIds(String[] ids);
}
