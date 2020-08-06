package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.mapper.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
public class StorageServiceImpl implements IStorageService {
    @Autowired
    private StorageMapper storageMapper;
    @Autowired
    private StorageinbillMapper storageinbillMapper;
    @Autowired
    private StorageoutbillMapper storageoutbillMapper;
    @Autowired
    private StoragetypeMapper storagetypeMapper;
    @Autowired
    private StorageindetailMapper storageindetailMapper;
    @Autowired
    private StorageoutdetailMapper storageoutdetailMapper;
    @Autowired
    private StoragequitbillMapper storagequitbillMapper;
    @Autowired
    private StoragequitdetailMapper storagequitdetailMapper;
    @Autowired
    private WarehouseRecordMapper warehouseRecordMapper;

    @Override
    public Storage selectStorageListBymaterialcode(String materialcode) {
        return storageMapper.selectStorageListBymaterialcode(materialcode);
    }

    /**
     * 查询库存列表
     *
     * @param id 库存列表ID
     * @return 库存列表
     */
    @Override
    public Storage selectStorageById(Integer id) {
        return storageMapper.selectStorageById(id);
    }

    /**
     * 查询库存列表列表
     *
     * @param storage 库存列表
     * @return 库存列表
     */

    @Override
    public List<Storage> selectStorageList(Storage storage) {
        return storageMapper.selectStorageList(storage);
    }

    @Override
    @Transactional
    public int insertStorage(Storageinbill storageinbill, String productList) {
        //如果入库出现重复
        if (storageinbillMapper.selectStorageinbillByStockinid(storageinbill.getStockinid()) != null) {
            Storageinbill st = new Storageinbill();
            int i = storageinbill.getStockinid().lastIndexOf("-") + 1;
            int size = storageinbillMapper.selectStorageinbillList(st).size() + 1;
            String sizes = "";
            if (size < 10) {
                sizes = "00" + size;
            } else if (size < 100) {
                sizes = "0" + size;
            } else {
                sizes = String.valueOf(size);

            }

            storageinbill.setStockinid(storageinbill.getStockinid().substring(0, i) + sizes);
        }
        JSONArray productArray = JSONArray.fromObject(productList);
        for (int i = 0; i < productArray.size(); i++) {
            JSONObject jsonObject = productArray.getJSONObject(i);
            Storage storage = new Storage();
            WarehouseRecord warehouseRecord = new WarehouseRecord();
            Storageindetail storageindetail = new Storageindetail();
            if (!jsonObject.getString("counts").equals("")) {
                storageindetail.setCounts(Long.valueOf(jsonObject.getInt("counts")));
            }
            if (!jsonObject.getString("price").equals("")) {
                storageindetail.setPrice(Float.valueOf(jsonObject.getString("price")));
            }
            if (!jsonObject.getString("money").equals("")) {
                storageindetail.setMoney(Float.valueOf(jsonObject.getString("money")));
            }
            if (!jsonObject.getString("footprint").equals("null")) {
                storageindetail.setFootprint(jsonObject.getString("footprint"));
            }

            storageindetail.setMaterialcode(jsonObject.getString("materialcode"));
            if (!jsonObject.getString("name").equals("null")) {
                storageindetail.setName(jsonObject.getString("name"));
            }
            if (!jsonObject.getString("partnumber").equals("null")) {
                storageindetail.setPartnumber(jsonObject.getString("partnumber"));
            }
            storageindetail.setRate(jsonObject.getString("rate"));
            if (!jsonObject.getString("taxamount").equals("")) {
                storageindetail.setTaxamount(Double.valueOf(jsonObject.getString("taxamount")));

            }
            if (!jsonObject.getString("unit").equals("null")) {
                storageindetail.setUnit(jsonObject.getString("unit"));
            }
            if (!jsonObject.getString("manufacture").equals("null")) {
                storageindetail.setManufacture(jsonObject.getString("manufacture"));
            }

            storageindetail.setSupplier(storageinbill.getSupplier());
            storageindetail.setComments(jsonObject.getString("comments"));
            storageindetail.setSerialNumber(jsonObject.getString("serialNumber"));
            storageindetail.setStorageinbillid(storageinbill.getStockinid());
            storageindetailMapper.insertStorageindetail(storageindetail);

            storage.setTypeId(storageinbill.getOutsourcewarehouseid());
            storage.setMaterialcode(storageindetail.getMaterialcode());
            storage.setSupplier(storageindetail.getSupplier());
            storage.setSerialNumber(storageindetail.getSerialNumber());
            storage.setMoney(storageindetail.getMoney());
            storage.setStocks(storageindetail.getCounts());
            if (storageMapper.selectStorageByMaterialcodeAndTypeid(storage) > 0) {
                storageMapper.updatestocks(storage);
            } else {
                storage.setName(storageindetail.getName());
                storage.setPartnumber(storageindetail.getPartnumber());
                storage.setManufacture(storageindetail.getManufacture());
                storage.setPrice(storageindetail.getPrice());
                storage.setUnit(storageindetail.getUnit());
                storage.setFootprint(storageindetail.getFootprint());
                storageMapper.insertStorage(storage);
            }
            warehouseRecord.setType("1");
            warehouseRecord.setNumber(storageinbill.getStockinid());
            warehouseRecord.setMaterialcode(jsonObject.getString("materialcode"));
            warehouseRecord.setName(jsonObject.getString("name"));
            warehouseRecord.setCount(storageindetail.getCounts());
            warehouseRecord.setPrice(storageindetail.getPrice());
            warehouseRecord.setMoney(storageindetail.getMoney());
            warehouseRecord.setSerialNumber(jsonObject.getString("serialNumber"));
            warehouseRecord.setSupplier(storageindetail.getSupplier());
            warehouseRecordMapper.insertWarehouseRecord(warehouseRecord);


        }
        String ancestors = storagetypeMapper.selectStoragetypeById(storageinbill.getOutsourcewarehouseid()).getAncestors();
        String deptName = storagetypeMapper.selectStoragetypeById(storageinbill.getOutsourcewarehouseid()).getDeptName();
        if (ancestors.contains(",")) {
            String[] split = ancestors.split(",");
            String Outsourcewarehouse = "";
            for (int i = 0; i < split.length; i++) {
                if (Long.valueOf(split[i]) != 0) {
                    Outsourcewarehouse += storagetypeMapper.selectStoragetypeById(Long.valueOf(split[i])).getDeptName() + "-";
                }
            }
            storageinbill.setOutsourcewarehouse(Outsourcewarehouse + deptName);
        } else {
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
     * @param
     * @return 结果
     */
    @Override
    @Transactional
    public int updateStorage(Storageoutbill storageoutbill, String productList) {
        //如果出库单出现重复
        if (storageoutbillMapper.selectStorageoutbillByStorageoutId(storageoutbill.getStorageoutid()) != null) {
            Storageoutbill st = new Storageoutbill();
            int i = storageoutbill.getStorageoutid().lastIndexOf("-") + 1;
            int size = storageoutbillMapper.selectStorageoutbillList(st).size() + 1;

            String sizes = "";
            if (size < 10) {
                sizes = "00" + size;
            } else if (size < 100) {
                sizes = "0" + size;
            } else {
                sizes = String.valueOf(size);

            }

            storageoutbill.setStorageoutid(storageoutbill.getStorageoutid().substring(0, i) + sizes);
        }
        JSONArray productArray = JSONArray.fromObject(productList);


        for (int i = 0; i < productArray.size(); i++) {
            JSONObject jsonObject = productArray.getJSONObject(i);
            Storage storage = new Storage();
            WarehouseRecord warehouseRecord = new WarehouseRecord();
            Storageoutdetail storageoutdetail = new Storageoutdetail();


            Long stocks = storageMapper.selectStorageById(Integer.valueOf(jsonObject.getString("id"))).getStocks();
            if (stocks < Long.valueOf(jsonObject.getString("counts"))) {

                int error = 1 / 0;

            }


            if (!jsonObject.getString("counts").equals("")) {
                storageoutdetail.setCounts(Long.valueOf(jsonObject.getInt("counts")));
            }

            if (!"null".equals(jsonObject.getString("price"))) {
                storageoutdetail.setPrice(Float.valueOf(jsonObject.getString("price")));
            }
            if (!jsonObject.getString("money").equals("")) {
                storageoutdetail.setMoney(Float.valueOf(jsonObject.getString("money")));
            }
            if (!jsonObject.getString("footprint").equals("null")) {
                storageoutdetail.setFootprint(jsonObject.getString("footprint"));
            }

            storageoutdetail.setMaterialcode(jsonObject.getString("materialcode"));
            if (!jsonObject.getString("name").equals("null")) {
                storageoutdetail.setName(jsonObject.getString("name"));
            }
            if (!jsonObject.getString("partnumber").equals("null")) {
                storageoutdetail.setPartnumber(jsonObject.getString("partnumber"));
            }

            if (!jsonObject.getString("unit").equals("null")) {
                storageoutdetail.setUnit(jsonObject.getString("unit"));
            }
            if (!jsonObject.getString("manufacture").equals("null")) {
                storageoutdetail.setManufacture(jsonObject.getString("manufacture"));
            }
            if (!jsonObject.getString("supplier").equals("null")) {
                storageoutdetail.setSupplier(jsonObject.getString("supplier"));
            }
            if (!jsonObject.getString("serialNumber").equals("null")) {
                storageoutdetail.setSerialNumber(jsonObject.getString("serialNumber"));
            }
            storageoutdetail.setComments(jsonObject.getString("comments"));
            storageoutdetail.setStorageoutbillid(storageoutbill.getStorageoutid());
            storageoutdetailMapper.insertStorageoutdetail(storageoutdetail);
            storage.setId(Integer.valueOf(jsonObject.getString("id")));
            storage.setMoney(storageoutdetail.getMoney());
            storage.setStocks(storageoutdetail.getCounts());
            storageMapper.removestocks(storage);
            warehouseRecord.setType("2");
            warehouseRecord.setNumber(storageoutbill.getStorageoutid());
            warehouseRecord.setMaterialcode(jsonObject.getString("materialcode"));
            warehouseRecord.setName(jsonObject.getString("name"));
            warehouseRecord.setCount(storageoutdetail.getCounts());
            warehouseRecord.setPrice(storageoutdetail.getPrice());
            warehouseRecord.setMoney(storageoutdetail.getMoney());
            warehouseRecord.setSerialNumber(jsonObject.getString("serialNumber"));
            warehouseRecord.setSupplier(storageoutdetail.getSupplier());
            warehouseRecordMapper.insertWarehouseRecord(warehouseRecord);
        }

        String ancestors = storagetypeMapper.selectStoragetypeById(storageoutbill.getOutsourcewarehouseid()).getAncestors();
        String deptName = storagetypeMapper.selectStoragetypeById(storageoutbill.getOutsourcewarehouseid()).getDeptName();
        if (ancestors.contains(",")) {
            String[] split = ancestors.split(",");
            String Outsourcewarehouse = "";
            for (int i = 0; i < split.length; i++) {
                if (Long.valueOf(split[i]) != 0) {
                    Outsourcewarehouse += storagetypeMapper.selectStoragetypeById(Long.valueOf(split[i])).getDeptName() + "-";
                }
            }
            storageoutbill.setOutsourcewarehouse(Outsourcewarehouse + deptName);
        } else {
            storageoutbill.setOutsourcewarehouse(deptName);
        }

        return storageoutbillMapper.insertStorageoutbill(storageoutbill);

    }

    @Override
    public int quitStorage(Storagequitbill storagequitbill, String productList) {
        //如果出库单出现重复
        if (storagequitbillMapper.selectStoragequitbillByStoragequitbillId(storagequitbill.getStoragequitbillid()) != null) {
            Storagequitbill st = new Storagequitbill();
            int i = storagequitbill.getStoragequitbillid().lastIndexOf("-") + 1;
            int size = storagequitbillMapper.selectStoragequitbillList(st).size() + 1;

            String sizes = "";
            if (size < 10) {
                sizes = "00" + size;
            } else if (size < 100) {
                sizes = "0" + size;
            } else {
                sizes = String.valueOf(size);

            }

            storagequitbill.setStoragequitbillid(storagequitbill.getStoragequitbillid().substring(0, i) + sizes);
        }
        JSONArray productArray = JSONArray.fromObject(productList);


        for (int i = 0; i < productArray.size(); i++) {
            JSONObject jsonObject = productArray.getJSONObject(i);
            Storage storage = new Storage();
            WarehouseRecord warehouseRecord = new WarehouseRecord();
            Storagequitdetail storagequitdetail = new Storagequitdetail();
            if (!jsonObject.getString("counts").equals("")) {
                storagequitdetail.setCounts(Long.valueOf(jsonObject.getInt("counts")));
            }
            if (!jsonObject.getString("price").equals("")) {
                storagequitdetail.setPrice(Float.valueOf(jsonObject.getString("price")));
            }
            if (!jsonObject.getString("money").equals("")) {
                storagequitdetail.setMoney(Float.valueOf(jsonObject.getString("money")));
            }
            if (!jsonObject.getString("footprint").equals("null")) {
                storagequitdetail.setFootprint(jsonObject.getString("footprint"));
            }

            storagequitdetail.setMaterialcode(jsonObject.getString("materialcode"));
            if (!jsonObject.getString("name").equals("null")) {
                storagequitdetail.setName(jsonObject.getString("name"));
            }
            if (!jsonObject.getString("partnumber").equals("null")) {
                storagequitdetail.setPartnumber(jsonObject.getString("partnumber"));
            }

            if (!jsonObject.getString("unit").equals("null")) {
                storagequitdetail.setUnit(jsonObject.getString("unit"));
            }
            if (!jsonObject.getString("manufacture").equals("null")) {
                storagequitdetail.setManufacture(jsonObject.getString("manufacture"));
            }
            if (!jsonObject.getString("supplier").equals("null")) {
                storagequitdetail.setSupplier(jsonObject.getString("supplier"));
            }
            if (!jsonObject.getString("serialNumber").equals("null")) {
                storagequitdetail.setSerialNumber(jsonObject.getString("serialNumber"));
            }
            storagequitdetail.setComments(jsonObject.getString("comments"));
            storagequitdetail.setStoragequitbillid(storagequitbill.getStoragequitbillid());
            storagequitdetailMapper.insertStoragequitdetail(storagequitdetail);
            storage.setId(Integer.valueOf(jsonObject.getString("id")));
            storage.setMoney(storagequitdetail.getMoney());
            storage.setStocks(storagequitdetail.getCounts());
            storageMapper.quitstocks(storage);
            warehouseRecord.setType("3");
            warehouseRecord.setNumber(storagequitdetail.getStoragequitbillid());
            warehouseRecord.setMaterialcode(jsonObject.getString("materialcode"));
            warehouseRecord.setName(jsonObject.getString("name"));
            warehouseRecord.setCount(storagequitdetail.getCounts());
            warehouseRecord.setPrice(storagequitdetail.getPrice());
            warehouseRecord.setMoney(storagequitdetail.getMoney());
            warehouseRecord.setSerialNumber(jsonObject.getString("serialNumber"));
            warehouseRecord.setSupplier(storagequitdetail.getSupplier());
            warehouseRecordMapper.insertWarehouseRecord(warehouseRecord);
        }

        String ancestors = storagetypeMapper.selectStoragetypeById(storagequitbill.getOutsourcewarehouseid()).getAncestors();
        String deptName = storagetypeMapper.selectStoragetypeById(storagequitbill.getOutsourcewarehouseid()).getDeptName();
        if (ancestors.contains(",")) {
            String[] split = ancestors.split(",");
            String Outsourcewarehouse = "";
            for (int i = 0; i < split.length; i++) {
                if (Long.valueOf(split[i]) != 0) {
                    Outsourcewarehouse += storagetypeMapper.selectStoragetypeById(Long.valueOf(split[i])).getDeptName() + "-";
                }
            }
            storagequitbill.setOutsourcewarehouse(Outsourcewarehouse + deptName);
        } else {
            storagequitbill.setOutsourcewarehouse(deptName);
        }

        return storagequitbillMapper.insertStoragequitbill(storagequitbill);
    }

    /**
     * 删除库存列表对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteStorageByIds(String ids) {
        return storageMapper.deleteStorageByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除库存列表信息
     *
     * @param id 库存列表ID
     * @return 结果
     */
    @Override
    public int deleteStorageById(Integer id) {
        return storageMapper.deleteStorageById(id);
    }
}
