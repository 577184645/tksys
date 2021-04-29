package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.common.Const;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.mapper.*;
import com.ruoyi.system.service.IStorageService;
import com.ruoyi.system.util.BigDecimalUtil;
import com.ruoyi.system.util.jsonlistUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 库存列表Service业务层处理
 *
 * @author ruoyi
 * @date 2020-06-03
 */
@Service
@Transactional
public  class StorageServiceImpl implements IStorageService {
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
  /*  @Autowired
    private JavaMailSender javaMailSender;


    @Value("${spring.mail.username}")
    private String form;
    @Value("${spring.mail.recipient}")
    private String recipient;


    public void SimpleMailMessage(String path) {
        //复杂邮件
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        //邮件发送助手
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(mimeMessage, true);
            //邮件标题
            helper.setSubject("出入库台账");
            helper.setText("<b style='color:red'>excel附件在下面!!!</b>",true);
            //发送者：必填
            helper.setFrom(form);
            //接收者：必填
            helper.setTo(recipient);
            helper.addAttachment("出入库台账.xls",new File(path));
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }


    }*/





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
    public Storage selectStorageById(Long id) {
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

        List<Storage> storages = storageMapper.selectStorageList(storage);
        /**
         * 查看该产品具体位置
         */
        for (Storage storage1 : storages) {

            if (storage1.getAncestors().contains(",")) {
                String[] split = storage1.getAncestors().split(",");
                String Outsourcewarehouse = "";
                for (int i = 0; i < split.length; i++) {
                    if (Long.valueOf(split[i]) != 0) {

                        Outsourcewarehouse += storagetypeMapper.selectStoragetypeById(Long.valueOf(split[i])).getDeptName() + "-";
                    }
                }
                storage1.setAncestors(Outsourcewarehouse+storage1.getDeptName());
            }
        }


        return storages;
    }



    //得到入库的具体位置
    public String getOutsourcewarehouse(Long id) {
        String ancestors = storagetypeMapper.selectStoragetypeById(id).getAncestors();
        String deptName = storagetypeMapper.selectStoragetypeById(id).getDeptName();
        if (ancestors.contains(",")) {
            String[] split = ancestors.split(",");
            String Outsourcewarehouse = "";
            for (int i = 0; i < split.length; i++) {
                if (Long.valueOf(split[i]) != 0) {
                    Outsourcewarehouse += storagetypeMapper.selectStoragetypeById(Long.valueOf(split[i])).getDeptName() + "-";
                }
            }
            return Outsourcewarehouse + deptName;
        }
        return  deptName;
    }

    @Override
    public void insertStorage(Storageinbill storageinbill, String productList) {

        //得到入库的具体位置
        storageinbill.setOutsourcewarehouse(getOutsourcewarehouse(storageinbill.getOutsourcewarehouseid()));
        //判断是否为预入库
        if(StringUtils.isEmpty(storageinbill.getInvoiceid())){
            storageinbill.setStorageStatus(Const.Storagestatus.BEFOREHAND.getCode());
        }else{
            storageinbill.setStorageStatus(Const.Storagestatus.OFFICIAL.getCode());
        }
        Double summoney =0.0;
        storageinbillMapper.insertStorageinbill(storageinbill);
        List<String[]> jsonList = jsonlistUtil.getJsonList(productList, new String[]{"id","materialcode", "counts", "price", "money", "rate", "taxamount", "comments","serialNumber"});
        for (int i = 0; i < jsonList.size(); i++) {
            String[] strings = jsonList.get(i);
            Storage storage = new Storage();
            Storageindetail storageindetail = new Storageindetail(Long.valueOf(strings[0]),strings[1],Integer.valueOf(strings[2]),Double.valueOf(strings[3]),Double.valueOf(strings[4]),strings[5],strings[6]==null?null:Double.valueOf(strings[6]),strings[7],strings[8],storageinbill.getSupplier(),storageinbill.getId());
            summoney+=storageindetail.getMoney();
            storage.setTypeId(storageinbill.getOutsourcewarehouseid());
            storage.setMaterialcode(storageindetail.getMaterialcode());
            storage.setMaterialId(storageindetail.getMaterialId());
            //如果库存存在
            Storage storageInfo = storageMapper.selectStorageByMaterialId(storageindetail.getMaterialId());

            if (storageInfo!=null) {
                if(!storageInfo.getTypeId().equals(storageinbill.getOutsourcewarehouseid())){
                    throw new RuntimeException("该入库单有物料在其他库中!");
                }
                storageindetail.setSid(storageInfo.getId());
                Integer oldstocks = storageInfo.getStocks();
                Double oldmoney = storageInfo.getMoney();
                storage.setStocks(oldstocks+storageindetail.getCounts());
                List<Storageindetail> storageindetails = storageindetailMapper.selectStorageindetailByStorageinbillSid(storageindetail.getSid());
                Double  sum=storageindetail.getPrice();
                for (Storageindetail storageindetail1 : storageindetails) {
                    sum+=storageindetail1.getPrice();
                }
                storage.setPrice(BigDecimalUtil.div(sum,storageindetails.size()+1,4).doubleValue());
                storage.setMoney(BigDecimalUtil.mul(storage.getPrice(),storage.getStocks()).doubleValue());
                storageMapper.updateStorageByMaterialcodeAndTypeId(storage);
                //如果该产品没有库存
            } else {
                storage.setStocks(storageindetail.getCounts());
                storage.setPrice(storageindetail.getPrice());
                storage.setMoney(storageindetail.getMoney());
                storageMapper.insertStorage(storage);
                storageindetail.setSid(storage.getId());
            }

            //添加入库单详细信息
            storageindetailMapper.insertStorageindetail(storageindetail);
            //添加库存记录
            warehouseRecordMapper.insertWarehouseRecord(new WarehouseRecord(Const.WarehouseRecordStatus.STORAGE_IN,storageinbill.getStockinid(),storageindetail.getMaterialcode(),storageindetail.getCounts(),storageindetail.getPrice(),storageindetail.getMoney(),storageindetail.getSupplier(),storageindetail.getSerialNumber(),storageindetail.getComments()));


        }
        //修改入库单价格
        storageinbillMapper.updateStorageinbill(new Storageinbill(storageinbill.getId(),summoney));



    }



    /**
     * 修改库存列表
     *
     * @param
     * @return 结果
     */
    @Override
    public void updateStorage(Storageoutbill storageoutbill, String productList) {
        Double summoney =0.0;
        List<String[]> jsonList = jsonlistUtil.getJsonList(productList, new String[]{"id","counts", "materialcode", "serialNumber", "comments","materialId"});
        //得到出库的具体位置
        storageoutbill.setOutsourcewarehouse(getOutsourcewarehouse(storageoutbill.getOutsourcewarehouseid()));
        storageoutbillMapper.insertStorageoutbill(storageoutbill);
        for (int i = 0; i < jsonList.size(); i++) {

            String[] strings =jsonList.get(i);
            Storageoutdetail storageoutdetail = new Storageoutdetail(Long.valueOf(strings[0]),storageoutbill.getId(),strings[2],strings[3],Integer.valueOf(strings[1]),strings[4],Long.valueOf(strings[5]));
            Storage oldstorage = storageMapper.selectStorageById(Long.valueOf(strings[0]));
            summoney+=oldstorage.getPrice()*storageoutdetail.getCounts();
            if (oldstorage.getStocks() < storageoutdetail.getCounts()) {
               throw new RuntimeException("库存不足!");
            }
            //添加入库单详细信息
            storageoutdetailMapper.insertStorageoutdetail(storageoutdetail);
            Storage storage = new Storage();
            storage.setId(storageoutdetail.getSid());
            //得到原有库存相减得到更新库存价格
            storage.setMoney( BigDecimalUtil.mul(oldstorage.getPrice(), oldstorage.getStocks() - storageoutdetail.getCounts()).doubleValue());
            storage.setStocks(oldstorage.getStocks() - storageoutdetail.getCounts());
            storage.setOTime(new Date());
            storageMapper.updateStorageById(storage);

            storageoutbillMapper.updateStorageoutbill(new Storageoutbill(storageoutbill.getId(),summoney));
            warehouseRecordMapper.insertWarehouseRecord(new WarehouseRecord(Const.WarehouseRecordStatus.STORAGE_OUT,storageoutbill.getStorageoutid(),storageoutdetail.getMaterialcode(),storageoutdetail.getCounts(),null,null,null,storageoutdetail.getSerialNumber(),storageoutdetail.getComments()));

        }
    }

    @Override
    public void quitStorage(Storagequitbill storagequitbill, String productList) {
        List<String[]> jsonList = jsonlistUtil.getJsonList(productList, new String[]{"id","counts", "materialcode", "serialNumber", "comments","materialId"});
        Double summoney =0.0;
        //得到出库的具体位置
        storagequitbill.setOutsourcewarehouse(getOutsourcewarehouse(storagequitbill.getOutsourcewarehouseid()));
        storagequitbillMapper.insertStoragequitbill(storagequitbill);
        for (int i = 0; i < jsonList.size(); i++) {
            String[] strings =jsonList.get(i);
            Storage storage = new Storage();
            Storagequitdetail storagequitdetail = new Storagequitdetail(Long.valueOf(strings[0]),storagequitbill.getId(),strings[2],strings[3],Integer.valueOf(strings[1]),strings[4],Long.valueOf(strings[5]));
            storagequitdetailMapper.insertStoragequitdetail(storagequitdetail);
            storage.setId(storagequitdetail.getSid());
            //得到原有库存相减得到更新库存价格
            Storage oldstorage = storageMapper.selectStorageById(storage.getId());
            summoney+=oldstorage.getPrice()*storagequitdetail.getCounts();
            storage.setMoney( BigDecimalUtil.mul(oldstorage.getPrice(), oldstorage.getStocks() + storagequitdetail.getCounts()).doubleValue());
            storage.setStocks(oldstorage.getStocks() + storagequitdetail.getCounts());
            storage.setQTime(new Date());
            storageMapper.updateStorageById(storage);
            warehouseRecordMapper.insertWarehouseRecord(new WarehouseRecord(Const.WarehouseRecordStatus.STORAGE_QUIT,storagequitbill.getStoragequitbillid(),storagequitdetail.getMaterialcode(),storagequitdetail.getCounts(),null,null,null,storagequitdetail.getSerialNumber(),storagequitdetail.getComments()));
        }

        storagequitbillMapper.updateStoragequitbill(new Storagequitbill(storagequitbill.getId(),summoney));

    }






    @Override
    public void exportStorage(List<Storage> list, HttpServletResponse response) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("仓库出入库台账");

    }

    public  List<Map<String, Object>> fillExcelStorage(String begindate,String enddate) {

        return    storageMapper.selectStorageByDate(begindate,enddate);
    }

    @Override
    public AjaxResult deleteProjectById(Long id) {
        Storage storage = storageMapper.selectStorageById(id);
        if(storage!=null&&storage.getStocks()>0) {
            return  AjaxResult.error("该库存数量大于0无法删除!");
        }
         storageMapper.deleteStorageById(id);
       return AjaxResult.success("操作成功!");
    }

    @Override
    public int settingstock(Storage storage) {
        return storageMapper.settingStock(storage);
    }

}
