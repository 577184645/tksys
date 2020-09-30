package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.system.domain.Bomdetail;
import com.ruoyi.system.mapper.BomdetailMapper;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.BomMapper;
import com.ruoyi.system.domain.Bom;
import com.ruoyi.system.service.IBomService;
import com.ruoyi.common.core.text.Convert;
import org.springframework.transaction.annotation.Transactional;

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
                JSONObject jsonObject = productArray.getJSONObject(i);
                JSONArray materialcode = jsonObject.getJSONArray("smaterialcode");
                String sid="";
                for (int i1 = 0; i1 < materialcode.size(); i1++) {
                    sid+=materialcode.get(i1)+",";
                }

                Bomdetail bomdetail = new Bomdetail();
                bomdetail.setBomid(bom.getId());
                bomdetail.setSsid(sid.substring(0,sid.lastIndexOf(",")));
                bomdetail.setMsid(jsonObject.getInt("mmaterialcode"));
                bomdetail.setComment(jsonObject.getString("comment"));
                bomdetail.setFootprint(jsonObject.getString("footprint"));
                bomdetail.setDescription(jsonObject.getString("description"));
                bomdetail.setDesignator(jsonObject.getString("designator"));
                bomdetail.setQuantity(jsonObject.getInt("quantity"));
                bomdetail.setCount(jsonObject.getInt("count"));
                bomdetail.setSumcount(jsonObject.getInt("sumcount"));
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
         /*   bomdetail.setName(jsonObject.getString("name"));
            bomdetail.setMaterialcode(jsonObject.getString("materialcode"));
            bomdetail.setPartnumber(jsonObject.getString("partnumber"));
            bomdetail.setUnit(jsonObject.getString("unit"));
            bomdetail.setPrice(jsonObject.getDouble("price"));
            bomdetail.setManufacture(jsonObject.getString("manufacture"));
            bomdetail.setSupplier(jsonObject.getString("supplier"));
            bomdetail.setCount(jsonObject.getInt("count"));

            if(jsonObject.getString("leadtime")!=null||!jsonObject.getString("leadtime").equals("")) {
                bomdetail.setLeadtime(jsonObject.getInt("leadtime"));
            }
            bomdetail.setFootprint(jsonObject.getString("footprint"));
            bomdetail.setComments(jsonObject.getString("comments"));*/
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
