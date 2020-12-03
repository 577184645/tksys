package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.Bom;
import com.ruoyi.system.domain.Bomdetail;
import com.ruoyi.system.mapper.BomMapper;
import com.ruoyi.system.mapper.BomdetailMapper;
import com.ruoyi.system.mapper.StorageMapper;
import com.ruoyi.system.service.IBomService;
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
public class BomServiceImpl implements IBomService 
{
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
    public Bom selectBomById(Long id)
    {
        return bomMapper.selectBomById(id);
    }

    /**
     * 查询bom列表列表
     * 
     * @param bom bom列表
     * @return bom列表
     */
    @Override
    public List<Bom> selectBomList(Bom bom)
    {
        return bomMapper.selectBomList(bom);
    }

    /**
     * 新增bom列表
     * 
     * @param bom bom列表
     * @return 结果
     */
    @Override
    public int insertBom(Bom bom)
    {
        return bomMapper.insertBom(bom);
    }

    @Override
    @Transactional
    public int addBom(String bomList, Bom bom) {
        if (bomMapper.insertBom(bom) > 0) {

            JSONArray productArray = JSONArray.fromObject(bomList);

            for (int i = 0; i < productArray.size(); i++) {
                Bomdetail bomdetail = new Bomdetail();
                JSONObject jsonObject = productArray.getJSONObject(i);
                if(jsonObject.has("mmaterialcodes")&&StringUtils.isNotBlank(jsonObject.getString("mmaterialcodes"))&&!jsonObject.getString("mmaterialcodes").equals("null")){
                    bomdetail.setMsid( jsonObject.getString("mmaterialcodes"));
                }
                if(jsonObject.has("smaterialcodes")&&StringUtils.isNotBlank(jsonObject.getString("smaterialcodes"))&&!jsonObject.getString("smaterialcodes").equals("null")){

                    bomdetail.setSsid(jsonObject.getString("smaterialcodes"));
                }
                if(jsonObject.has("mmaterialcode")&&StringUtils.isNotBlank(jsonObject.getString("mmaterialcode"))&&!jsonObject.getString("mmaterialcode").equals("null")){


                    bomdetail.setMsid(storageMapper.selectStorageById(jsonObject.getLong("mmaterialcode")).getMaterialcode());                }

                if(jsonObject.has("smaterialcode")&&StringUtils.isNotBlank(jsonObject.getString("smaterialcode"))&&!jsonObject.getString("smaterialcode").equals("null")) {
                    JSONArray materialcode = jsonObject.getJSONArray("smaterialcode");
                    if (materialcode.size()>0){
                        String sid = "";
                        for (int i1 = 0; i1 < materialcode.size(); i1++) {
                            sid += materialcode.get(i1) + ",";
                        }
                        bomdetail.setSsid(sid.substring(0,sid.lastIndexOf(",")));
                    }

                }
                bomdetail.setBomid(bom.getId());
                bomdetail.setSupplier(jsonObject.getString("supplier"));
                bomdetail.setPrice(jsonObject.getDouble("price"));
                bomdetail.setComment(jsonObject.getString("comment"));
                bomdetail.setFootprint(jsonObject.getString("footprint"));
                bomdetail.setDescription(jsonObject.getString("description"));
                bomdetail.setDesignator(jsonObject.getString("designator"));
                bomdetail.setQuantity(jsonObject.getInt("quantity"));
                bomdetailMapper.insertBomdetail(bomdetail);
            }
        }
            return 1;
        }

    @Override
    @Transactional
    public int editBom(String bomList, Bom bom) {


        Bomdetail bomdetaildel=new Bomdetail();
        bomdetaildel.setBomid(bom.getId());
        List<Bomdetail> bomdetails = bomdetailMapper.selectBomdetailList(bomdetaildel);
        if(bomdetails.size()>0){
            for (Bomdetail bomdetaileach:bomdetails
            ) {
                bomdetailMapper.deleteBomdetailById(bomdetaileach.getId());
            }
        }

        JSONArray productArray = JSONArray.fromObject(bomList);
        for (int i = 0; i < productArray.size(); i++) {

            JSONObject jsonObject = productArray.getJSONObject(i);

            Bomdetail bomdetail = new Bomdetail();
            bomdetail.setBomid(bom.getId());

            if(jsonObject.has("msid")&&StringUtils.isNotBlank(jsonObject.getString("msid"))&&!jsonObject.getString("msid").equals("null")){

                bomdetail.setMsid(jsonObject.getString("msid"));
            }
            if(jsonObject.has("ssid")&&StringUtils.isNotBlank(jsonObject.getString("ssid"))&&!jsonObject.getString("ssid").equals("null")){

                bomdetail.setSsid(jsonObject.getString("ssid"));
            }


            bomdetail.setComment(jsonObject.getString("comment"));
            bomdetail.setFootprint(jsonObject.getString("footprint"));
            bomdetail.setDescription(jsonObject.getString("description"));
            bomdetail.setDesignator(jsonObject.getString("designator"));
            bomdetail.setQuantity(jsonObject.getInt("quantity"));
            bomdetail.setCount(jsonObject.getInt("count"));
            bomdetail.setSumcount(jsonObject.getInt("sumcount"));
            bomdetailMapper.insertBomdetail(bomdetail);
        }
        return bomMapper.updateBom(bom);
    }

    /**
     * 修改bom列表
     * 
     * @param bom bom列表
     * @return 结果
     */
    @Override
    public int updateBom(Bom bom)
    {
        return bomMapper.updateBom(bom);
    }

    /**
     * 删除bom列表对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteBomByIds(String ids)
    {

        return bomMapper.deleteBomByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除bom列表信息
     * 
     * @param id bom列表ID
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteBomById(Long id)
    {
        Bomdetail bomdetail=new Bomdetail();
        bomdetail.setBomid(id);
        List<Bomdetail> bomdetails = bomdetailMapper.selectBomdetailList(bomdetail);
       if(bomdetails.size()>0){
           for (Bomdetail bomdetaileach:bomdetails
                 ) {
               bomdetailMapper.deleteBomdetailById(bomdetaileach.getId());
           }
       }
        return bomMapper.deleteBomById(id);
    }
}
