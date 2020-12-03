package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.OfferMapper;
import com.ruoyi.system.domain.Offer;
import com.ruoyi.system.service.IOfferService;
import com.ruoyi.common.core.text.Convert;

/**
 * 报价单Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-09-07
 */
@Service
public class OfferServiceImpl implements IOfferService 
{
    @Autowired
    private OfferMapper offerMapper;

    /**
     * 查询报价单
     * 
     * @param offerId 报价单ID
     * @return 报价单
     */
    @Override
    public Offer selectOfferById(Long offerId)
    {
        return offerMapper.selectOfferById(offerId);
    }

    @Override
    public List<Offer> selectOfferListByofferNumber(String offernumber) {
        return offerMapper.selectOfferListByofferNumber(offernumber);
    }

    /**
     * 查询报价单列表
     * 
     * @param offer 报价单
     * @return 报价单
     */
    @Override
    public List<Offer> selectOfferList(Offer offer)
    {
        return offerMapper.selectOfferList(offer);
    }

    /**
     * 新增报价单
     * 
     * @param offer 报价单
     * @return 结果
     */
    @Override
    public int insertOffer(Offer offer)
    {
        if (StringUtils.isBlank(offer.getOfferNumber())) {
            if (StringUtils.isNotBlank(offerMapper.selectOfferMaxNumber())) {

                Integer number = Integer.valueOf(offerMapper.selectOfferMaxNumber().substring(offerMapper.selectOfferMaxNumber().lastIndexOf("-") + 1)) + 1;
                if (number < 10) {
                    offer.setOfferNumber("TKSYSBJD-00" + number);
                } else if (number < 100) {
                    offer.setOfferNumber("TKSYSBJD-0" + number);
                } else {
                    offer.setOfferNumber("TKSYSBJD-" + number);
                }
            } else {
                offer.setOfferNumber("TKSYSBJD-001");
            }
        }

        return offerMapper.insertOffer(offer);
    }

    /**
     * 修改报价单
     * 
     * @param offer 报价单
     * @return 结果
     */
    @Override
    public int updateOffer(Offer offer)
    {
        return offerMapper.updateOffer(offer);
    }

    /**
     * 删除报价单对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteOfferByIds(String ids)
    {
        return offerMapper.deleteOfferByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除报价单信息
     * 
     * @param offerId 报价单ID
     * @return 结果
     */
    @Override
    public int deleteOfferById(Long offerId)
    {
        return offerMapper.deleteOfferById(offerId);
    }

    @Override
    public int approveOffer(Long id) {
        return offerMapper.updateOfferbyApproveStatus(id);
    }
}
