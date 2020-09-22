package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.SalesContract;

/**
 * 销售合同Service接口
 * 
 * @author ruoyi
 * @date 2020-09-18
 */
public interface ISalesContractService 
{
    /**
     * 查询销售合同
     * 
     * @param contractId 销售合同ID
     * @return 销售合同
     */
    public SalesContract selectSalesContractById(Long contractId);

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
     * 批量删除销售合同
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSalesContractByIds(String ids);

    /**
     * 删除销售合同信息
     * 
     * @param contractId 销售合同ID
     * @return 结果
     */
    public int deleteSalesContractById(Long contractId);
}
