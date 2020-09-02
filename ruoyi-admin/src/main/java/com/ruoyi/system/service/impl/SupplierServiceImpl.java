package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SupplierMapper;
import com.ruoyi.system.domain.Supplier;
import com.ruoyi.system.service.ISupplierService;
import com.ruoyi.common.core.text.Convert;

/**
 * 供应商列表Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-06-01
 */
@Service
public class SupplierServiceImpl implements ISupplierService 
{
    @Autowired
    private SupplierMapper supplierMapper;

    @Override
    public List<Supplier> findList() {
        return supplierMapper.findList();
    }

    @Override
    public List<Supplier> findListCustomer() {
        return supplierMapper.findListCustomer();
    }

    @Override
    public List<Supplier> findListSupplier() {
        return supplierMapper.findListSupplier();
    }

    /**
     * 查询供应商列表
     * 
     * @param id 供应商列表ID
     * @return 供应商列表
     */
    @Override
    public Supplier selectSupplierById(Long id)
    {
        return supplierMapper.selectSupplierById(id);
    }

    /**
     * 查询供应商列表列表
     * 
     * @param supplier 供应商列表
     * @return 供应商列表
     */
    @Override
    public List<Supplier> selectSupplierList(Supplier supplier)
    {
        return supplierMapper.selectSupplierList(supplier);
    }

    /**
     * 新增供应商列表
     * 
     * @param supplier 供应商列表
     * @return 结果
     */
    @Override
    public int insertSupplier(Supplier supplier)
    {
        return supplierMapper.insertSupplier(supplier);
    }

    /**
     * 修改供应商列表
     * 
     * @param supplier 供应商列表
     * @return 结果
     */
    @Override
    public int updateSupplier(Supplier supplier)
    {
        return supplierMapper.updateSupplier(supplier);
    }

    /**
     * 删除供应商列表对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSupplierByIds(String ids)
    {
        return supplierMapper.deleteSupplierByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除供应商列表信息
     * 
     * @param id 供应商列表ID
     * @return 结果
     */
    @Override
    public int deleteSupplierById(Long id)
    {
        return supplierMapper.deleteSupplierById(id);
    }
}
