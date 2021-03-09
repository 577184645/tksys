package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.Bom;
import com.ruoyi.system.domain.Bomdetail;
import com.ruoyi.system.domain.Storage;
import com.ruoyi.system.mapper.BomMapper;
import com.ruoyi.system.mapper.BomdetailMapper;
import com.ruoyi.system.mapper.StorageMapper;
import com.ruoyi.system.service.IBomService;
import com.ruoyi.system.util.jsonlistUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * bom列表Service业务层处理
 *
 * @author ruoyi
 * @date 2020-07-24
 */
@Service
@Transactional
public class BomServiceImpl implements IBomService {
    @Autowired
    private BomMapper bomMapper;
    @Autowired
    private BomdetailMapper bomdetailMapper;
    @Autowired
    private StorageMapper storageMapper;

    /**
     * 查询bom列表
     *
     * @param id bom列表ID
     * @return bom列表
     */
    @Override
    public Bom selectBomById(Long id) {
        return bomMapper.selectBomById(id);
    }

    /**
     * 查询bom列表列表
     *
     * @param bom bom列表
     * @return bom列表
     */
    @Override
    public List<Bom> selectBomList(Bom bom) {
        return bomMapper.selectBomList(bom);
    }


    @Override
    public int addBom(String bomList, Bom bom) {
        int length = bomMapper.insertBom(bom);
        Double sum=0.0;
        List<String[]> jsonList = jsonlistUtil.getJsonList(bomList, new String[]{"code", "link", "price", "comment", "footprint", "description", "designator", "parttype", "quantity"});
        for (int i = 0; i < jsonList.size(); i++) {
            String[] strings = jsonList.get(i);
            Bomdetail bomdetail = new Bomdetail();
            bomdetail.setBomid(bom.getId());
            bomdetail.setCode(strings[0]);
            bomdetail.setLink(strings[1]);
            bomdetail.setPrice(strings[2]!=null?Double.parseDouble(strings[2]):0.0);
            bomdetail.setComment(strings[3]);
            bomdetail.setFootprint(strings[4]);
            bomdetail.setDescription(strings[5]);
            bomdetail.setDesignator(strings[6]);
            bomdetail.setParttype(strings[7]);
            bomdetail.setQuantity(strings[8]!=null?Long.parseLong(strings[8]):0);
            sum+=bomdetail.getPrice()*bomdetail.getQuantity();
            bomdetailMapper.insertBomdetail(bomdetail);
        }
        Bom editbom=new Bom();
        editbom.setId(bom.getId());
        editbom.setPrice(sum);
        bomMapper.updateBom(editbom);
        return length;
    }


    /**
     * 删除bom列表对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteBomByIds(String ids) {

        return bomMapper.deleteBomByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除bom列表信息
     *
     * @param id bom列表ID
     * @return 结果
     */
    @Override
    public int deleteBomById(Long id) {

        List<Bomdetail> bomdetails = bomdetailMapper.selectBomBybomId(id);
        if (bomdetails.size() > 0) {
            for (Bomdetail bomdetaileach : bomdetails) {
                bomdetailMapper.deleteBomdetailById(bomdetaileach.getId());
            }
        }
        return bomMapper.deleteBomById(id);
    }

    @Override
    public boolean refresh(String ids) {
        String[] id = ids.split(",");
        for (String s : id) {
            Double sum=0.0;
            List<Bomdetail> bomdetails = bomdetailMapper.selectBomBybomId(Long.valueOf(s));
            for (Bomdetail bomdetail : bomdetails) {
                Storage storage = storageMapper.selectStorageByMaterialcode(bomdetail.getCode());
                if(storage!=null){
                    Bomdetail bomdetail1=new Bomdetail();
                    bomdetail1.setId(bomdetail.getId());
                    bomdetail1.setPrice(storage.getPrice());
                    bomdetailMapper.updateBomdetail(bomdetail);
                    sum+= (bomdetail1.getPrice()!=null?bomdetail1.getPrice():0)*(bomdetail.getQuantity()!=null?bomdetail.getQuantity():0);
                 }
            }
            Bom bom=new Bom();
            bom.setId(Long.valueOf(s));
            bom.setPrice(sum);
            bomMapper.updateBom(bom);
        }
        return true;
    }
}
