package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.Storageinbill;
import com.ruoyi.system.domain.Storageindetail;
import com.ruoyi.system.mapper.StorageinbillMapper;
import com.ruoyi.system.mapper.StorageindetailMapper;
import com.ruoyi.system.mapper.StoragetypeMapper;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.StorageMapper;
import com.ruoyi.system.domain.Storage;
import com.ruoyi.system.service.IStorageService;
import com.ruoyi.common.core.text.Convert;
import org.springframework.transaction.annotation.Transactional;

/**
 * 库存列表Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-06-03
 */
@Service
public class StorageServiceImpl implements IStorageService 
{
    @Autowired
    private StorageMapper storageMapper;
    @Autowired
    private StorageinbillMapper storageinbillMapper;
    @Autowired
    private StoragetypeMapper storagetypeMapper;
    @Autowired
    private StorageindetailMapper storageindetailMapper;


    /**
     * 查询库存列表
     * 
     * @param id 库存列表ID
     * @return 库存列表
     */
    @Override
    public Storage selectStorageById(Integer id)
    {
        return storageMapper.selectStorageById(id);
    }

    /**
     * 查询库存列表列表
     * 
     * @param storage 库存列表
     * @return 库存列表
     */

    @Override
    public List<Storage> selectStorageList(Storage storage)
    {
        return storageMapper.selectStorageList(storage);
    }

    @Override
    @Transactional
    public int insertStorage(Storageinbill storageinbill, String productList) {
        JSONArray productArray = JSONArray.fromObject(productList);
        for (int i = 0; i < productArray.size(); i++) {
            JSONObject jsonObject = productArray.getJSONObject(i);
            Storage storage=new Storage();

            Storageindetail storageindetail = new Storageindetail();
            if(!jsonObject.getString("counts").equals("")) {
                storageindetail.setCounts(Long.valueOf(jsonObject.getInt("counts")));
            }
            if(!jsonObject.getString("price").equals("")) {
                storageindetail.setPrice(Double.valueOf(jsonObject.getString("price")));
            }
            if(!jsonObject.getString("money").equals("")) {
                storageindetail.setMoney(Double.valueOf(jsonObject.getString("money")));
            }
            storageindetail.setFootprint(jsonObject.getString("footprint"));
            storageindetail.setMaterialcode(jsonObject.getString("materialcode"));
            storageindetail.setName(jsonObject.getString("name"));
            storageindetail.setPartnumber(jsonObject.getString("partnumber"));
            storageindetail.setRate(jsonObject.getString("rate"));
            if(!jsonObject.getString("taxamount").equals("")) {
                storageindetail.setTaxamount(Double.valueOf(jsonObject.getString("taxamount")));

            }

            storageindetail.setUnit(jsonObject.getString("unit"));
            storageindetail.setSupplier(jsonObject.getString("supplier"));
            storageindetail.setPurchaseid(jsonObject.getString("purchaseid"));
            storageindetail.setApplyid(jsonObject.getString("applyid"));
            storageindetail.setContractid(jsonObject.getString("contractid"));
            storageindetail.setInvoiceid(jsonObject.getString("invoiceid"));
            storageindetail.setExpressid(jsonObject.getString("expressid"));
            storageindetail.setInstoragecause(jsonObject.getString("instoragecause"));
            storageindetail.setProjectname(jsonObject.getString("projectname"));
            storageindetail.setProposer(jsonObject.getString("proposer"));
            storageindetail.setComments(jsonObject.getString("comments"));
            storageindetail.setManufacture(jsonObject.getString("manufacture"));
            storageindetail.setSerialNumber(jsonObject.getString("serialNumber"));
            storageindetail.setStorageinbillid(storageinbill.getStockinid());
            storageindetailMapper.insertStorageindetail(storageindetail);
            if(storageMapper.selectStorageByMaterialcodeAndTypeid(storageindetail.getMaterialcode(),storageinbill.getOutsourcewarehouseid(),storageindetail.getSerialNumber())>0){
                storage.setTypeId(storageinbill.getOutsourcewarehouseid());
                storage.setMaterialcode(storageindetail.getMaterialcode());
                storage.setMoney(Double.valueOf(jsonObject.getString("money")));
                storage.setStocks(Long.valueOf(jsonObject.getInt("counts")));
                storageMapper.updatestocks(storage);
            }else{
                storage.setName(storageindetail.getName());
                storage.setSupplier(storageindetail.getSupplier());
                storage.setMaterialcode(storageindetail.getMaterialcode());
                storage.setPartnumber(storageindetail.getPartnumber());
                storage.setFootprint(storageindetail.getFootprint());
                storage.setSerialNumber(storageindetail.getSerialNumber());
                storage.setManufacture(storageindetail.getManufacture());
                storage.setPrice(storageindetail.getPrice());
                storage.setTypeId(storageinbill.getOutsourcewarehouseid());
                storage.setUnit(storageindetail.getUnit());
                storage.setStocks(storageindetail.getCounts());
                storage.setMoney(storageindetail.getMoney());
                storageMapper.insertStorage(storage);
            }

            }
        String ancestors = storagetypeMapper.selectStoragetypeById(storageinbill.getOutsourcewarehouseid()).getAncestors();
        String deptName = storagetypeMapper.selectStoragetypeById(storageinbill.getOutsourcewarehouseid()).getDeptName();
        if(ancestors.contains(",")){
           String[] split = ancestors.split(",");
           String Outsourcewarehouse="";
           for (int i=0;i<split.length;i++){
                if(Long.valueOf(split[i])!=0) {
                    Outsourcewarehouse += storagetypeMapper.selectStoragetypeById(Long.valueOf(split[i])).getDeptName() + "-";
                }
           }
           storageinbill.setOutsourcewarehouse(Outsourcewarehouse+deptName);
       }else{
           storageinbill.setOutsourcewarehouse(deptName);
       }
        return storageinbillMapper.insertStorageinbill(storageinbill);
    }

    /**
     * 新增库存列表
     * 
     * @param storage 库存列表
     * @return 结果
     */


    /**
     * 修改库存列表
     * 
     * @param storage 库存列表
     * @return 结果
     */
    @Override
    public int updateStorage(Storage storage)
    {
        return storageMapper.updateStorage(storage);
    }

    /**
     * 删除库存列表对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteStorageByIds(String ids)
    {
        return storageMapper.deleteStorageByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除库存列表信息
     * 
     * @param id 库存列表ID
     * @return 结果
     */
    @Override
    public int deleteStorageById(Integer id)
    {
        return storageMapper.deleteStorageById(id);
    }
}
