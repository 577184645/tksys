package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Offer;

/**
 * 报价单Service接口
 * 
 * @author ruoyi
 * @date 2020-09-07
 */
public interface IOfferService 
{
    /**
     * 查询报价单
     * 
     * @param offerId 报价单ID
     * @return 报价单
     */
    public Offer selectOfferById(Long offerId);

    /**
     * 查询报价单列表
     * 
     * @param offer 报价单
     * @return 报价单集合
     */
    public List<Offer> selectOfferList(Offer offer);

    /**
     * 新增报价单
     * 
     * @param offer 报价单
     * @return 结果
     */
    public int insertOffer(Offer offer);

    /**
     * 修改报价单
     * 
     * @param offer 报价单
     * @return 结果
     */
    public int updateOffer(Offer offer);

    /**
     * 批量删除报价单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteOfferByIds(String ids);

    /**
     * 删除报价单信息
     * 
     * @param offerId 报价单ID
     * @return 结果
     */
    public int deleteOfferById(Long offerId);
}
