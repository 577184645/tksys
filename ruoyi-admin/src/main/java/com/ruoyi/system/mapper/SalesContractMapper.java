package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.SalesContract;

import java.util.List;

/**
 * 销售合同Mapper接口
 * 
 * @author ruoyi
 * @date 2020-09-18
 */
public interface SalesContractMapper 
{

    public  int updatecontractStatus();

    public  int rollbackupdatecontractStatus();


    /**
     * 年销售总额
     *
     * @param yyyy 年份
     * @return 年销售总额
     */
    public Double yearsummoney(String yyyy);


    /**
     * 查询销售合同
     * 
     * @param contractId 销售合同ID
     * @return 销售合同
     */
    public SalesContract selectSalesContractById(Long contractId);


    public String selectSalesContractbyMaxNumber(String yyyy);

    /**
     * 查询销售合同列表
     * 
     * @param salesContract 销售合同
     * @return 销售合同集合
     */
    public List<SalesContract> selectSalesContractList(SalesContract salesContract);

    /**
     * 新增销售合同
     * 
     * @param salesContract 销售合同
     * @return 结果
     */
    public int insertSalesContract(SalesContract salesContract);

    /**
     * 修改销售合同
     * 
     * @param salesContract 销售合同
     * @return 结果
     */
    public int updateSalesContract(SalesContract salesContract);

    /**
     * 删除销售合同
     * 
     * @param contractId 销售合同ID
     * @return 结果
     */
    public int deleteSalesContractById(Long contractId);

    /**
     * 批量删除销售合同
     * 
     * @param contractIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteSalesContractByIds(String[] contractIds);
}
