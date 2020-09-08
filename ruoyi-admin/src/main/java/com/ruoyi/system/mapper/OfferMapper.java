package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Offer;

/**
 * 报价单Mapper接口
 * 
 * @author ruoyi
 * @date 2020-09-07
 */
public interface OfferMapper 
{
    /**
     * 查询报价单
     * 
     * @param offerId 报价单ID
     * @return 报价单
     */
    public Offer selectOfferById(Long offerId);


    /**
     * 查询最大报价单
     *
     */
    public String selectOfferMaxNumber();

    /**
     * 查询报价单列表
     * 
     * @param offer 报价单
     * @return 报价单集合
     */
    public List<Offer> selectOfferList(Offer offer);



    public List<Offer> selectOfferListByofferNumber(String offernumber);


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
     * 删除报价单
     * 
     * @param offerId 报价单ID
     * @return 结果
     */
    public int deleteOfferById(Long offerId);

    /**
     * 批量删除报价单
     * 
     * @param offerIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteOfferByIds(String[] offerIds);
}
