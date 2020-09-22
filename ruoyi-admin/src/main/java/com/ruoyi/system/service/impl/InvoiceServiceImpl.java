package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.InvoiceMapper;
import com.ruoyi.system.domain.Invoice;
import com.ruoyi.system.service.IInvoiceService;
import com.ruoyi.common.core.text.Convert;

/**
 * 发票Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-09-21
 */
@Service
public class InvoiceServiceImpl implements IInvoiceService 
{
    @Autowired
    private InvoiceMapper invoiceMapper;

    /**
     * 查询发票
     * 
     * @param invoiceId 发票ID
     * @return 发票
     */
    @Override
    public Invoice selectInvoiceById(Long invoiceId)
    {
        return invoiceMapper.selectInvoiceById(invoiceId);
    }

    /**
     * 查询发票列表
     * 
     * @param invoice 发票
     * @return 发票
     */
    @Override
    public List<Invoice> selectInvoiceList(Invoice invoice)
    {
        return invoiceMapper.selectInvoiceList(invoice);
    }

    /**
     * 新增发票
     * 
     * @param invoice 发票
     * @return 结果
     */
    @Override
    public int insertInvoice(Invoice invoice)
    {
        return invoiceMapper.insertInvoice(invoice);
    }

    /**
     * 修改发票
     * 
     * @param invoice 发票
     * @return 结果
     */
    @Override
    public int updateInvoice(Invoice invoice)
    {
        return invoiceMapper.updateInvoice(invoice);
    }

    /**
     * 删除发票对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteInvoiceByIds(String ids)
    {
        return invoiceMapper.deleteInvoiceByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除发票信息
     * 
     * @param invoiceId 发票ID
     * @return 结果
     */
    @Override
    public int deleteInvoiceById(Long invoiceId)
    {
        return invoiceMapper.deleteInvoiceById(invoiceId);
    }
}
