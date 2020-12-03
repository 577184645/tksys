package com.ruoyi.system.service.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import com.ruoyi.system.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SalesContractMapper;
import com.ruoyi.system.domain.SalesContract;
import com.ruoyi.system.service.ISalesContractService;
import com.ruoyi.common.core.text.Convert;

/**
 * 销售合同Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-09-18
 */
@Service
public class SalesContractServiceImpl implements ISalesContractService 
{
    @Autowired
    private SalesContractMapper salesContractMapper;



    /**
     * 查询销售合同
     * 
     * @param contractId 销售合同ID
     * @return 销售合同
     */
    @Override
    public SalesContract selectSalesContractById(Long contractId)
    {
        return salesContractMapper.selectSalesContractById(contractId);
    }

    @Override
    public Double yearsummoney(String yyyy) {
        return salesContractMapper.yearsummoney(yyyy);
    }

    /**
     * 查询销售合同列表
     * 
     * @param salesContract 销售合同
     * @return 销售合同
     */
    @Override
    public List<SalesContract> selectSalesContractList(SalesContract salesContract)
    {
        return salesContractMapper.selectSalesContractList(salesContract);
    }

    /**
     * 新增销售合同
     * 
     * @param salesContract 销售合同
     * @return 结果
     */
    @Override
    public int insertSalesContract(SalesContract salesContract)
    {


        if(salesContractMapper.selectSalesContractbyMaxNumber(salesContract.getYear())!=null){
            Integer maxnum = Integer.valueOf(salesContractMapper.selectSalesContractbyMaxNumber(salesContract.getYear()).substring(salesContractMapper.selectSalesContractbyMaxNumber(salesContract.getYear()).lastIndexOf("-") + 1));
            maxnum++;
            if(maxnum<10){
                salesContract.setContractNumber("XS-"+salesContract.getYear()+"-00"+maxnum);
            }else if(maxnum<100){
                salesContract.setContractNumber("XS-"+salesContract.getYear()+"-0"+maxnum);
            }else{
                salesContract.setContractNumber("XS-"+salesContract.getYear()+"-"+maxnum);
            }
        }else{
            salesContract.setContractNumber("XS-"+salesContract.getYear()+"-001");
        }


        return salesContractMapper.insertSalesContract(salesContract);
    }

    /**
     * 修改销售合同
     * 
     * @param salesContract 销售合同
     * @return 结果
     */
    @Override
    public int updateSalesContract(SalesContract salesContract)
    {
        return salesContractMapper.updateSalesContract(salesContract);
    }

    /**
     * 删除销售合同对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSalesContractByIds(String ids)
    {
        return salesContractMapper.deleteSalesContractByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除销售合同信息
     * 
     * @param contractId 销售合同ID
     * @return 结果
     */
    @Override
    public int deleteSalesContractById(Long contractId)
    {
        return salesContractMapper.deleteSalesContractById(contractId);
    }
}
