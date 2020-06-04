package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Supplier;

/**
 * 供应商列表Service接口
 * 
 * @author ruoyi
 * @date 2020-06-01
 */
public interface ISupplierService 
{

    /**
     * 查询供应商列表列表
     *
     * @param
     * @return 供应商列表集合
     */
    public List<Supplier> findList();


    /**
     * 查询供应商列表
     *
     * @param
     * @return 供应商列表类型为供应商的集合
     */
    public List<Supplier> findListSupplier();


    /**
     * 查询供应商列表
     * 
     * @param id 供应商列表ID
     * @return 供应商列表
     */
    public Supplier selectSupplierById(Long id);

    /**
     * 查询供应商列表列表
     * 
     * @param supplier 供应商列表
     * @return 供应商列表集合
     */
    public List<Supplier> selectSupplierList(Supplier supplier);

    /**
     * 新增供应商列表
     * 
     * @param supplier 供应商列表
     * @return 结果
     */
    public int insertSupplier(Supplier supplier);

    /**
     * 修改供应商列表
     * 
     * @param supplier 供应商列表
     * @return 结果
     */
    public int updateSupplier(Supplier supplier);

    /**
     * 批量删除供应商列表
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSupplierByIds(String ids);

    /**
     * 删除供应商列表信息
     * 
     * @param id 供应商列表ID
     * @return 结果
     */
    public int deleteSupplierById(Long id);
}
