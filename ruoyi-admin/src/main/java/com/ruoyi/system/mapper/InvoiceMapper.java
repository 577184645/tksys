package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Invoice;

/**
 * 发票Mapper接口
 * 
 * @author ruoyi
 * @date 2020-09-21
 */
public interface InvoiceMapper 
{
    /**
     * 查询发票
     * 
     * @param invoiceId 发票ID
     * @return 发票
     */
    public Invoice selectInvoiceById(Long invoiceId);


    /**
     * 年发票总额
     *
     * @param yyyy 年份
     * @return 年销售总额
     */
    public Double yearsummoney(String yyyy);

    /**
     * 查询发票列表
     * 
     * @param invoice 发票
     * @return 发票集合
     */
    public List<Invoice> selectInvoiceList(Invoice invoice);

    /**
     * 新增发票
     * 
     * @param invoice 发票
     * @return 结果
     */
    public int insertInvoice(Invoice invoice);

    /**
     * 修改发票
     * 
     * @param invoice 发票
     * @return 结果
     */
    public int updateInvoice(Invoice invoice);

    /**
     * 删除发票
     * 
     * @param invoiceId 发票ID
     * @return 结果
     */
    public int deleteInvoiceById(Long invoiceId);

    /**
     * 批量删除发票
     * 
     * @param invoiceIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteInvoiceByIds(String[] invoiceIds);
}
