package com.ruoyi.system.service.impl;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.common.Const;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.mapper.*;
import com.ruoyi.system.util.BigDecimalUtil;
import com.ruoyi.vo.WarehouseBillVo;
import java.io.File;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.ruoyi.system.service.IStorageService;
import com.ruoyi.common.core.text.Convert;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * 库存列表Service业务层处理
 *
 * @author ruoyi
 * @date 2020-06-03
 */
@Service
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
    @Autowired
    private JavaMailSender javaMailSender;


    @Value("${spring.mail.username}")
    private String form;
    @Value("${spring.mail.recipient}")
    private String recipient;


    public void SimpleMailMessage(String path) {
        /*Workbook workbook*/
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

            helper.addAttachment("出入库台账.xlsx",new File(path));

            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }







    }


    @Override
    public List<Storage> selectByBom(String comments, String footprint) {
        return storageMapper.selectByBom(comments,footprint);
    }

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
            storageindetail.setName(jsonObject.getString("name"));
            storageindetail.setMaterialcode(jsonObject.getString("materialcode"));
            storageindetail.setCounts(jsonObject.getLong("counts"));
            storageindetail.setPrice(jsonObject.getDouble("price"));
            storageindetail.setMoney(jsonObject.getDouble("money"));
            if(StringUtils.isNotBlank(jsonObject.getString("footprint"))&&!jsonObject.getString("footprint").equals("null")) {
                storageindetail.setFootprint(jsonObject.getString("footprint"));
            }
            if(StringUtils.isNotBlank(jsonObject.getString("partnumber"))&&!jsonObject.getString("partnumber").equals("null")) {
                storageindetail.setPartnumber(jsonObject.getString("partnumber"));
            }
            if(StringUtils.isNotBlank(jsonObject.getString("rate"))&&!jsonObject.getString("rate").equals("null")) {
                storageindetail.setRate(jsonObject.getString("rate"));
            }
            if(StringUtils.isNotBlank(jsonObject.getString("taxamount"))&&!jsonObject.getString("taxamount").equals("null")) {
                storageindetail.setTaxamount(Double.valueOf(jsonObject.getString("taxamount")));
            }
            if(StringUtils.isNotBlank(jsonObject.getString("unit"))&&!jsonObject.getString("unit").equals("null")) {
                storageindetail.setUnit(jsonObject.getString("unit"));
            }
            if(StringUtils.isNotBlank(jsonObject.getString("manufacture"))&&!jsonObject.getString("manufacture").equals("null")) {
                storageindetail.setManufacture(jsonObject.getString("manufacture"));
            }
            if(StringUtils.isNotBlank(jsonObject.getString("comments"))&&!jsonObject.getString("comments").equals("null")) {
                storageindetail.setComments(jsonObject.getString("comments"));

            }
            if(StringUtils.isNotBlank(jsonObject.getString("serialNumber"))&&!jsonObject.getString("serialNumber").equals("null")) {
                storageindetail.setSerialNumber(jsonObject.getString("serialNumber"));
            }else{
                storageindetail.setSerialNumber("");
            }

            storageindetail.setSupplier(storageinbill.getSupplier());

            storageindetail.setStorageinbillid(storageinbill.getStockinid());
            storage.setTypeId(storageinbill.getOutsourcewarehouseid());
            storage.setMaterialcode(storageindetail.getMaterialcode());
            storage.setSupplier(storageindetail.getSupplier());
            storage.setSerialNumber(storageindetail.getSerialNumber());
            storage.setMoney(new BigDecimal(storageindetail.getMoney()).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
            storage.setStocks(storageindetail.getCounts());
            if (storageMapper.selectStorageByMaterialcodeAndTypeid(storage) > 0) {
                storageindetail.setSid(storageMapper.selectStorageInfoByMaterialcodeAndTypeid(storage).getId());
                Double oldprice = storageMapper.selectStorageInfoByMaterialcodeAndTypeid(storage).getPrice();
                Double newprice=storageindetail.getPrice();
                storage.setPrice(BigDecimalUtil.avg(oldprice,newprice).doubleValue());
                storageMapper.updatestocks(storage);
            } else {
                storage.setName(storageindetail.getName());
                storage.setPartnumber(storageindetail.getPartnumber());
                storage.setManufacture(storageindetail.getManufacture());
                storage.setPrice(new BigDecimal(storageindetail.getPrice()).setScale(4,BigDecimal.ROUND_HALF_UP).doubleValue());
                storage.setUnit(storageindetail.getUnit());
                storage.setFootprint(storageindetail.getFootprint());
                storageMapper.insertStorage(storage);
                storageindetail.setSid(storage.getId());

            }
            storageindetailMapper.insertStorageindetail(storageindetail);
            warehouseRecord.setType(Const.WarehouseRecordStatus.STORAGE_IN);
            warehouseRecord.setNumber(storageinbill.getStockinid());
            warehouseRecord.setMaterialcode(storageindetail.getMaterialcode());
            warehouseRecord.setName(storageindetail.getName());
            warehouseRecord.setCount(storageindetail.getCounts());
            warehouseRecord.setPrice(storageindetail.getPrice());
            warehouseRecord.setMoney(storageindetail.getMoney());
            warehouseRecord.setSerialNumber(storageindetail.getSerialNumber());
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
        }

        storageinbill.setStorageStatus(Const.Storagestatus.OFFICIAL.getCode());

        if(storageinbill.getOutsourcewarehouse().indexOf("生产库")!=-1&&StringUtils.isEmpty(storageinbill.getInvoiceid())){
            storageinbill.setStorageStatus(Const.Storagestatus.BEFOREHAND.getCode());
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


            Long stocks = storageMapper.selectStorageById(jsonObject.getLong("id")).getStocks();

            if (stocks < Long.valueOf(jsonObject.getString("counts"))) {
                //如果出现库存不足的异常会进行回滚
                int error = 1 / 0;
            }
            storageoutdetail.setSid(Long.valueOf(jsonObject.getString("id")));
            storageoutdetail.setCounts(Long.valueOf(jsonObject.getInt("counts")));
            storageoutdetail.setPrice(jsonObject.getDouble("price"));
            storageoutdetail.setMoney(jsonObject.getDouble("money"));
            storageoutdetail.setMaterialcode(jsonObject.getString("materialcode"));
            storageoutdetail.setStorageoutbillid(storageoutbill.getStorageoutid());
            if (StringUtils.isNotBlank(jsonObject.getString("footprint"))&&!jsonObject.getString("footprint").equals("null")) {
                storageoutdetail.setFootprint(jsonObject.getString("footprint"));
            }
            if (StringUtils.isNotBlank(jsonObject.getString("name"))&&!jsonObject.getString("name").equals("null")) {
                storageoutdetail.setName(jsonObject.getString("name"));
            }
            if (StringUtils.isNotBlank(jsonObject.getString("partnumber"))&&!jsonObject.getString("partnumber").equals("null")) {
                storageoutdetail.setPartnumber(jsonObject.getString("partnumber"));
            }

            if (StringUtils.isNotBlank(jsonObject.getString("unit"))&&!jsonObject.getString("unit").equals("null")) {
                storageoutdetail.setUnit(jsonObject.getString("unit"));
            }
            if (StringUtils.isNotBlank(jsonObject.getString("manufacture"))&&!jsonObject.getString("manufacture").equals("null")) {
                storageoutdetail.setManufacture(jsonObject.getString("manufacture"));
            }
            if (StringUtils.isNotBlank(jsonObject.getString("supplier"))&&!jsonObject.getString("supplier").equals("null")) {
                storageoutdetail.setSupplier(jsonObject.getString("supplier"));
            }
            if (StringUtils.isNotBlank(jsonObject.getString("serialNumber"))&&!jsonObject.getString("serialNumber").equals("null")) {
                storageoutdetail.setSerialNumber(jsonObject.getString("serialNumber"));
            }
            if (StringUtils.isNotBlank(jsonObject.getString("comments"))&&!jsonObject.getString("comments").equals("null")) {
                storageoutdetail.setComments(jsonObject.getString("comments"));
            }
            storageoutdetailMapper.insertStorageoutdetail(storageoutdetail);
            storage.setId(jsonObject.getLong("id"));
            storage.setMoney(new BigDecimal(storageoutdetail.getMoney()).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
            storage.setStocks(storageoutdetail.getCounts());
            storageMapper.removestocks(storage);
            if(storageMapper.selectStorageById(jsonObject.getLong("id")).getStocks()==0){
               Storage storage1=new Storage();
                storage1.setId(jsonObject.getLong("id"));
                storage1.setMoney(new BigDecimal(0).doubleValue());
                storageMapper.updateStorage(storage1);
            }
            warehouseRecord.setType(Const.WarehouseRecordStatus.STORAGE_OUT);
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
if(storageoutbill.getOutsourcewarehousecomments().isEmpty()){
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
}else{
    storageoutbill.setOutsourcewarehouse(storageoutbill.getOutsourcewarehousecomments());
}


        return storageoutbillMapper.insertStorageoutbill(storageoutbill);

    }

    @Override
    public int quitStorage(Storagequitbill storagequitbill, String productList) {
        //如果退库单出现重复
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
            storagequitdetail.setCounts(Long.valueOf(jsonObject.getInt("counts")));
            storagequitdetail.setSid(jsonObject.getLong("id"));
            storagequitdetail.setPrice(jsonObject.getDouble("price"));
            storagequitdetail.setMoney(jsonObject.getDouble("money"));
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
            storage.setId(jsonObject.getLong("id"));
            storage.setMoney(new BigDecimal(storagequitdetail.getMoney()).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue());
            storage.setStocks(storagequitdetail.getCounts());
            storageMapper.quitstocks(storage);
            warehouseRecord.setType(Const.WarehouseRecordStatus.STORAGE_QUIT);
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
        if(storagequitbill.getOutsourcewarehousecomments().isEmpty()) {
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
        }else{
            storagequitbill.setOutsourcewarehouse(storagequitbill.getOutsourcewarehousecomments());
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

    public  void fillExcelStorage(String date) throws  Exception
             {



        Workbook workbook=new XSSFWorkbook();
        Sheet sheet=workbook.createSheet("仓库出入库台账");
        Row row1=sheet.createRow(0);
        Cell cell=row1.createCell(0);
        Row row2=sheet.createRow(1);

        Row row3=sheet.createRow(2);
        String [] titleName={"序号","编码","品名","规格","描述","计量单位","厂家","上月结存","单价","金额","本月进货","单价","金额","本月消耗","单价","金额","期末库存","单价","金额"};


        Cell cell2=row2.createCell(0);
        Cell cell21=row2.createCell(13);
        Cell cell22=row2.createCell(14);
        Cell cell3=row2.createCell(2);
        cell.setCellValue("仓库出入库台账");
        cell2.setCellValue("单位");
        cell3.setCellValue("安徽天康智诚科技有限公司");
        cell21.setCellValue("月份");

        String month="";
        switch (date.substring(date.indexOf("-")+1,date.indexOf("-")+3)){
            case "01":
                month="一月";
                break;
            case "02":
                month="二月";
                break;
            case "03":
                month="三月";
                break;
            case "04":
                month="四月";
                break;
            case "05":
                month="五月";
                break;
            case "06":
                month="六月";
                break;
            case "07":
                month="七月";
                break;
            case "08":
                month="八月";
                break;
            case "09":
                month="九月";
                break;
            case "10":
                month="十月";
                break;
            case "11":
                month="十一月";
                break;
            case "12":
                month="十二月";
                break;

        }
        cell22.setCellValue(month);
        sheet.addMergedRegion(new CellRangeAddress(
                0,
                0,
                0,
                18

        ));
        sheet.addMergedRegion(new CellRangeAddress(
                1,
                1,
                14,
                15

        ));
        sheet.setColumnWidth(1, 4000);
        sheet.setColumnWidth(2, 4500);
        sheet.setColumnWidth(3, 6000);
        sheet.setColumnWidth(4, 6000);
        sheet.setColumnWidth(6, 5500);
        sheet.addMergedRegion(new CellRangeAddress(
                1,   //起始行
                1,   //结束行
                0,   //起始列
                1    //结束列

        ));
        sheet.addMergedRegion(new CellRangeAddress(
                1,   //起始行
                1,   //结束行
                2,   //起始列
                4    //结束列

        ));
        Font font =  workbook.createFont();
        font.setFontName("黑体");
        font.setFontHeightInPoints((short) 24);//设置字体大小
        Font font1 =  workbook.createFont();
        font.setFontHeightInPoints((short) 16);

        CellStyle cellStyle2=workbook.createCellStyle();
        cellStyle2.setAlignment(HorizontalAlignment.CENTER);
        cellStyle2.setVerticalAlignment(VerticalAlignment.CENTER);
       /* cellStyle2.setVerticalAlignment(HorizontalAlignment);*/
        cellStyle2.setFont(font1);
        cellStyle2.setWrapText(true);
        CellStyle cellStyle=workbook.createCellStyle();
        cellStyle.setAlignment(HorizontalAlignment.CENTER);

        cellStyle.setFont(font);

        cell.setCellStyle(cellStyle);
        cell2.setCellStyle(cellStyle2);
        cell3.setCellStyle(cellStyle2);
        cell21.setCellStyle(cellStyle2);
        cell22.setCellStyle(cellStyle2);
        for (int i = 0; i < titleName.length; i++) {
            row3.createCell(i).setCellValue(titleName[i]);
            row3.getCell(i).setCellStyle(cellStyle2);
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//注意月份是MM
        List<WarehouseBillVo> warehouseBillInVos= storageMapper.selectStoragebyDate(simpleDateFormat.parse(date));
        List<WarehouseBillVo> warehouseBillDInVos = storageMapper.selectStoragebyDatedashed(simpleDateFormat.parse(date));
       
        
        //对比入库红冲的产品

            for (WarehouseBillVo warehouseBillDInVo : warehouseBillDInVos) {
                for (WarehouseBillVo warehouseBillInVo : warehouseBillInVos) {
                    if (warehouseBillDInVo.getMaterialcode().equals(warehouseBillInVo.getMaterialcode()) && warehouseBillDInVo.getSupplier().equals(warehouseBillInVo.getSupplier())) {
                        warehouseBillInVo.setIncount((warehouseBillInVo.getIncount()!=null?warehouseBillInVo.getIncount():0) -(warehouseBillDInVo.getIncount()!=null?warehouseBillDInVo.getIncount():0));
                        warehouseBillInVo.setInmoney((warehouseBillInVo.getInmoney()!=null?warehouseBillInVo.getInmoney():0.00) - (warehouseBillDInVo.getInmoney()!=null?warehouseBillDInVo.getInmoney():0.00));
                        warehouseBillInVo.setInprice(BigDecimalUtil.divint(warehouseBillInVo.getInmoney(), warehouseBillInVo.getIncount()));
                        break;
                    }

                }
            }

        List<WarehouseBillVo> outwarehouseBillVos = storageMapper.selectoutStoragebyDate(simpleDateFormat.parse(date));

        List<WarehouseBillVo> outwarehouseBillDVos = storageMapper.selectoutStoragebyDatedashed(simpleDateFormat.parse(date));
        //对比出库红冲的产品
        for (WarehouseBillVo outwarehouseBillDVo : outwarehouseBillDVos) {
            for (WarehouseBillVo outwarehouseBillVo : outwarehouseBillVos) {

                    if (outwarehouseBillDVo.getMaterialcode().equals(outwarehouseBillVo.getMaterialcode()) && outwarehouseBillDVo.getSupplier().equals(outwarehouseBillVo.getSupplier())) {
                        outwarehouseBillVo.setOutcount((outwarehouseBillVo.getOutcount()!=null?outwarehouseBillVo.getOutcount():0) - (outwarehouseBillDVo.getOutcount()!=null?outwarehouseBillDVo.getOutcount():0));
                        outwarehouseBillVo.setOutmoney((outwarehouseBillVo.getOutmoney()!=null?outwarehouseBillVo.getOutmoney():0.00) - (outwarehouseBillDVo.getOutmoney()!=null?outwarehouseBillDVo.getOutmoney():0.00));
                        outwarehouseBillVo.setOutprice(BigDecimalUtil.divint(outwarehouseBillVo.getOutmoney(), outwarehouseBillVo.getOutcount()));
                        break;
                    }


            }
        }


        //对比入库出库的产品
        for (WarehouseBillVo outwarehouseBillVo : outwarehouseBillVos) {
            boolean flag=true;
            for (WarehouseBillVo warehouseBillInVo : warehouseBillInVos) {

                if(outwarehouseBillVo.getMaterialcode().equals(warehouseBillInVo.getMaterialcode())&&outwarehouseBillVo.getSupplier().equals(warehouseBillInVo.getSupplier())) {
                    warehouseBillInVo.setOutcount(outwarehouseBillVo.getOutcount());
                    warehouseBillInVo.setOutmoney(outwarehouseBillVo.getOutmoney());
                    warehouseBillInVo.setOutprice(outwarehouseBillVo.getOutprice());
                    flag=false;
                    break;
                }
                }
                if (flag) {
                    warehouseBillInVos.add(outwarehouseBillVo);
                }
        }



        List<Storage> storages = storageMapper.StoragefindList();
        for (WarehouseBillVo warehouseBillInVo : warehouseBillInVos) {
            for (Storage storage : storages) {

                if(warehouseBillInVo.getMaterialcode().equals(storage.getMaterialcode())&&warehouseBillInVo.getSupplier().equals(storage.getSupplier())){

                    warehouseBillInVo.setThiscount(storage.getStocks());
                    warehouseBillInVo.setThisprice(storage.getPrice().floatValue());
                    warehouseBillInVo.setThismoney(storage.getMoney().floatValue());
                    warehouseBillInVo.setPrevcount((warehouseBillInVo.getOutcount()!=null?warehouseBillInVo.getOutcount():0)-(warehouseBillInVo.getIncount()!=null?warehouseBillInVo.getIncount():0)+(warehouseBillInVo.getThiscount()!=null?warehouseBillInVo.getThiscount():0));
                    BigDecimal bg=  new BigDecimal((warehouseBillInVo.getOutmoney()!=null?warehouseBillInVo.getOutmoney():0)).subtract(new BigDecimal((warehouseBillInVo.getInmoney()!=null?warehouseBillInVo.getInmoney():0.00))).add(new BigDecimal((warehouseBillInVo.getThismoney()!=null?warehouseBillInVo.getThismoney():0)));
                    warehouseBillInVo.setPrevmoney(bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                    warehouseBillInVo.setPrevprice(BigDecimalUtil.divint(warehouseBillInVo.getPrevmoney(),warehouseBillInVo.getPrevcount().intValue()));
                    break;
                }
            }
        }

        int rowIndex = 3;

        for (int i = 0; i < warehouseBillInVos.size(); i++) {
            if( storageinbillMapper.selectStorageinbillByStockinid(warehouseBillInVos.get(i).getNumber())!=null){
                if(storageinbillMapper.selectStorageinbillByStockinid(warehouseBillInVos.get(i).getNumber()).getOutsourcewarehouse().indexOf("生产库")==-1) {

                    warehouseBillInVos.remove(i);
                }
            }
            else if(storageoutbillMapper.selectStorageoutbillByStorageoutId(warehouseBillInVos.get(i).getNumber())!=null){
                if(storageoutbillMapper.selectStorageoutbillByStorageoutId(warehouseBillInVos.get(i).getNumber()).getOutsourcewarehouse().indexOf("生产库")==-1){
                    warehouseBillInVos.remove(i);

                }
            }
        }





        for (int i = 0; i < warehouseBillInVos.size(); i++) {
            warehouseBillInVos.get(i).setRownum(i+1);
            Row row = sheet.createRow(rowIndex++);
            row.createCell(0).setCellValue(warehouseBillInVos.get(i).getRownum()!=null?warehouseBillInVos.get(i).getRownum().toString():"");
            row.createCell(1).setCellValue(warehouseBillInVos.get(i).getMaterialcode()!=null?warehouseBillInVos.get(i).getMaterialcode().toString():"");
            row.createCell(2).setCellValue(warehouseBillInVos.get(i).getName()!=null?warehouseBillInVos.get(i).getName().toString():"");
            row.createCell(3).setCellValue(warehouseBillInVos.get(i).getPartnumber()!=null?warehouseBillInVos.get(i).getPartnumber().toString():"");
            row.createCell(4).setCellValue(warehouseBillInVos.get(i).getDescription()!=null?warehouseBillInVos.get(i).getDescription().toString():"");
            row.createCell(5).setCellValue(warehouseBillInVos.get(i).getUnit()!=null?warehouseBillInVos.get(i).getUnit().toString():"");
            row.createCell(6).setCellValue(warehouseBillInVos.get(i).getSupplier()!=null?warehouseBillInVos.get(i).getSupplier().toString():"");
            row.createCell(7).setCellValue(warehouseBillInVos.get(i).getPrevcount()!=null?warehouseBillInVos.get(i).getPrevcount().toString():"");
            row.createCell(8).setCellValue(warehouseBillInVos.get(i).getPrevprice()!=null?warehouseBillInVos.get(i).getPrevprice().toString():"");
            row.createCell(9).setCellValue(warehouseBillInVos.get(i).getPrevmoney()!=null?warehouseBillInVos.get(i).getPrevmoney().toString():"");

            row.createCell(10).setCellValue(warehouseBillInVos.get(i).getIncount()!=null?warehouseBillInVos.get(i).getIncount().toString():"");
            row.createCell(11).setCellValue(warehouseBillInVos.get(i).getInprice()!=null?warehouseBillInVos.get(i).getInprice().toString():"");
            row.createCell(12).setCellValue(warehouseBillInVos.get(i).getInmoney()!=null?warehouseBillInVos.get(i).getInmoney().toString():"");
            row.createCell(13).setCellValue(warehouseBillInVos.get(i).getOutcount()!=null?warehouseBillInVos.get(i).getOutcount().toString():"");
            row.createCell(14).setCellValue(warehouseBillInVos.get(i).getOutprice()!=null?warehouseBillInVos.get(i).getOutprice().toString():"");
            row.createCell(15).setCellValue(warehouseBillInVos.get(i).getOutmoney()!=null?warehouseBillInVos.get(i).getOutmoney().toString():"");
            row.createCell(16).setCellValue(warehouseBillInVos.get(i).getThiscount()!=null?warehouseBillInVos.get(i).getThiscount().toString():"");
            row.createCell(17).setCellValue(warehouseBillInVos.get(i).getThisprice()!=null?warehouseBillInVos.get(i).getThisprice().toString():"");
            row.createCell(18).setCellValue(warehouseBillInVos.get(i).getThismoney()!=null?warehouseBillInVos.get(i).getThismoney().toString():"");

            row.getCell(0).setCellStyle(cellStyle2);
            row.getCell(1).setCellStyle(cellStyle2);
            row.getCell(2).setCellStyle(cellStyle2);
            row.getCell(3).setCellStyle(cellStyle2);
            row.getCell(4).setCellStyle(cellStyle2);
            row.getCell(5).setCellStyle(cellStyle2);
            row.getCell(6).setCellStyle(cellStyle2);
            row.getCell(7).setCellStyle(cellStyle2);
            row.getCell(8).setCellStyle(cellStyle2);
            row.getCell(9).setCellStyle(cellStyle2);
            row.getCell(10).setCellStyle(cellStyle2);
            row.getCell(11).setCellStyle(cellStyle2);
            row.getCell(12).setCellStyle(cellStyle2);
            row.getCell(13).setCellStyle(cellStyle2);
            row.getCell(14).setCellStyle(cellStyle2);
            row.getCell(15).setCellStyle(cellStyle2);
            row.getCell(16).setCellStyle(cellStyle2);
            row.getCell(17).setCellStyle(cellStyle2);
            row.getCell(18).setCellStyle(cellStyle2);





        }

        FileOutputStream fileOut=new FileOutputStream("C:\\ruoyi\\taizhang\\"+month+"仓库出入库台账.xlsx");
        workbook.write(fileOut);
        fileOut.close();
        SimpleMailMessage("C:\\ruoyi\\taizhang\\"+month+"仓库出入库台账.xlsx");

    }

}
