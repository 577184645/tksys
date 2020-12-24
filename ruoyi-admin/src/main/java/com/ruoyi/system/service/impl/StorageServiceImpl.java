package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.system.common.Const;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.exception.OtherException;
import com.ruoyi.system.mapper.*;
import com.ruoyi.system.service.IStorageService;
import com.ruoyi.system.util.BigDecimalUtil;
import com.ruoyi.system.vo.WarehouseBillVo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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



    @Override
    @Transactional
    public int insertStorage(Storageinbill storageinbill, String productList) {
        //如果入库出现重复
    /*    if (storageinbillMapper.selectStorageinbillByStockinid(storageinbill.getStockinid()) != null) {
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
        }*/
        JSONArray productArray = JSONArray.fromObject(productList);
        for (int i = 0; i < productArray.size(); i++) {
            JSONObject jsonObject = productArray.getJSONObject(i);
            Storage storage = new Storage();
            WarehouseRecord warehouseRecord = new WarehouseRecord();
            Storageindetail storageindetail = new Storageindetail();
            storageindetail.setMaterialcode(jsonObject.getString("materialcode"));
            storageindetail.setCounts(jsonObject.getLong("counts"));
            storageindetail.setPrice(jsonObject.getDouble("price"));
            storageindetail.setMoney(jsonObject.getDouble("money"));
            if(jsonObject.has("rate")&&StringUtils.isNotBlank(jsonObject.getString("rate"))&&!jsonObject.getString("rate").equals("null")) {
                storageindetail.setRate(jsonObject.getString("rate"));
            }
            if(jsonObject.has("taxamount")&&StringUtils.isNotBlank(jsonObject.getString("taxamount"))&&!jsonObject.getString("taxamount").equals("null")) {
                storageindetail.setTaxamount(Double.valueOf(jsonObject.getString("taxamount")));
            }
            if(jsonObject.has("comments")&&StringUtils.isNotBlank(jsonObject.getString("comments"))&&!jsonObject.getString("comments").equals("null")) {
                storageindetail.setComments(jsonObject.getString("comments"));

            }
            if(jsonObject.has("serialNumber")&&StringUtils.isNotBlank(jsonObject.getString("serialNumber"))&&!jsonObject.getString("serialNumber").equals("null")) {
                storageindetail.setSerialNumber(jsonObject.getString("serialNumber"));
            }
            storageindetail.setSupplier(storageinbill.getSupplier());
            storageindetail.setStorageinbillid(storageinbill.getStockinid());
            storage.setTypeId(storageinbill.getOutsourcewarehouseid());
            storage.setMaterialcode(storageindetail.getMaterialcode());
            storage.setMaterialId(jsonObject.getLong("id"));
            //如果库存存在
            Storage storageInfo = storageMapper.selectStorageInfoByMaterialcodeAndTypeid(storage);

            if (storageInfo!=null) {
                storageindetail.setSid(storageInfo.getId());
                Long oldstocks = storageInfo.getStocks();
                double oldmoney = storageInfo.getMoney();
                storage.setStocks(oldstocks+storageindetail.getCounts());
                List<Storageindetail> storageindetails = storageindetailMapper.selectStorageindetailByStorageinbillSid(storageindetail.getSid());
                double  sum=storageindetail.getPrice();
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
            //添加库存记录
            storageindetailMapper.insertStorageindetail(storageindetail);
            warehouseRecord.setType(Const.WarehouseRecordStatus.STORAGE_IN);
            warehouseRecord.setNumber(storageinbill.getStockinid());
            warehouseRecord.setMaterialcode(storageindetail.getMaterialcode());
            warehouseRecord.setCount(storageindetail.getCounts());
            warehouseRecord.setPrice(storageindetail.getPrice());
            warehouseRecord.setMoney(storageindetail.getMoney());
            warehouseRecord.setSerialNumber(storageindetail.getSerialNumber());
            warehouseRecord.setSupplier(storageindetail.getSupplier());
            warehouseRecord.setRemark(storageindetail.getComments());
            warehouseRecordMapper.insertWarehouseRecord(warehouseRecord);

        }
        //得到入库的具体位置
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

       //判断是否为预入库
        if(storageinbill.getOutsourcewarehouse().indexOf("生产库")!=-1&&StringUtils.isEmpty(storageinbill.getInvoiceid())){
            storageinbill.setStorageStatus(Const.Storagestatus.BEFOREHAND.getCode());
        }else{
            storageinbill.setStorageStatus(Const.Storagestatus.OFFICIAL.getCode());

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
       /* if (storageoutbillMapper.selectStorageoutbillByStorageoutId(storageoutbill.getStorageoutid()) != null) {
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
        }*/
        JSONArray productArray = JSONArray.fromObject(productList);
        for (int i = 0; i < productArray.size(); i++) {
            JSONObject jsonObject = productArray.getJSONObject(i);
            Storage storage = new Storage();
            WarehouseRecord warehouseRecord = new WarehouseRecord();
            Storageoutdetail storageoutdetail = new Storageoutdetail();
            Long stocks = storageMapper.selectStorageById(jsonObject.getLong("id")).getStocks();
            if (stocks < Long.valueOf(jsonObject.getString("counts"))) {
               throw new OtherException("库存不足!");
            }
            storageoutdetail.setSid(Long.valueOf(jsonObject.getString("id")));
            storageoutdetail.setCounts(Long.valueOf(jsonObject.getInt("counts")));
            storageoutdetail.setMaterialcode(jsonObject.getString("materialcode"));
            storageoutdetail.setStorageoutbillid(storageoutbill.getStorageoutid());
            if (StringUtils.isNotBlank(jsonObject.getString("serialNumber"))&&jsonObject.getString("serialNumber").length()>0) {
                storageoutdetail.setSerialNumber(jsonObject.getString("serialNumber"));
            }
            if (StringUtils.isNotBlank(jsonObject.getString("comments"))&&jsonObject.getString("comments").length()>0) {
                storageoutdetail.setComments(jsonObject.getString("comments"));
            }
            //添加入库单详细信息
            storageoutdetailMapper.insertStorageoutdetail(storageoutdetail);
            storage.setId(jsonObject.getLong("id"));
            //得到原有库存相减得到更新库存价格
            Storage oldstorage = storageMapper.selectStorageById(storage.getId());
            storage.setMoney( BigDecimalUtil.mul(oldstorage.getPrice(), oldstorage.getStocks() - storageoutdetail.getCounts()).doubleValue());
            storage.setStocks(oldstorage.getStocks() - storageoutdetail.getCounts());
            storage.setoTime(new Date());
            storageMapper.updateStorageById(storage);
            //添加库存记录
            warehouseRecord.setType(Const.WarehouseRecordStatus.STORAGE_OUT);
            warehouseRecord.setNumber(storageoutbill.getStorageoutid());
            warehouseRecord.setMaterialcode(jsonObject.getString("materialcode"));
            warehouseRecord.setCount(storageoutdetail.getCounts());
            warehouseRecord.setSerialNumber(jsonObject.getString("serialNumber"));
            warehouseRecord.setRemark(storageoutdetail.getComments());
            warehouseRecordMapper.insertWarehouseRecord(warehouseRecord);
        }
        //得到出库的具体位置
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


    //新增出库单
        return storageoutbillMapper.insertStorageoutbill(storageoutbill);

    }

    @Override
    public int quitStorage(Storagequitbill storagequitbill, String productList) {
        //如果退库单出现重复
       /* if (storagequitbillMapper.selectStoragequitbillByStoragequitbillId(storagequitbill.getStoragequitbillid()) != null) {
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
        }*/
        JSONArray productArray = JSONArray.fromObject(productList);
        for (int i = 0; i < productArray.size(); i++) {
            JSONObject jsonObject = productArray.getJSONObject(i);
            Storage storage = new Storage();
            WarehouseRecord warehouseRecord = new WarehouseRecord();
            Storagequitdetail storagequitdetail = new Storagequitdetail();
            storagequitdetail.setCounts(Long.valueOf(jsonObject.getInt("counts")));
            storagequitdetail.setSid(jsonObject.getLong("id"));
            storagequitdetail.setMaterialcode(jsonObject.getString("materialcode"));
            if (StringUtils.isNotBlank(jsonObject.getString("serialNumber"))&&jsonObject.getString("serialNumber").length()>0) {
                storagequitdetail.setSerialNumber(jsonObject.getString("serialNumber"));
            }
            if (StringUtils.isNotBlank(jsonObject.getString("comments"))&&jsonObject.getString("comments").length()>0) {
                storagequitdetail.setComments(jsonObject.getString("comments"));
            }
            storagequitdetail.setStoragequitbillid(storagequitbill.getStoragequitbillid());
            storagequitdetailMapper.insertStoragequitdetail(storagequitdetail);
            storage.setId(jsonObject.getLong("id"));
            //得到原有库存相减得到更新库存价格
            Storage oldstorage = storageMapper.selectStorageById(storage.getId());
            storage.setMoney( BigDecimalUtil.mul(oldstorage.getPrice(), oldstorage.getStocks() + storagequitdetail.getCounts()).doubleValue());
            storage.setStocks(oldstorage.getStocks() + storagequitdetail.getCounts());
            storage.setqTime(new Date());
            storageMapper.updateStorageById(storage);
            warehouseRecord.setType(Const.WarehouseRecordStatus.STORAGE_QUIT);
            warehouseRecord.setNumber(storagequitdetail.getStoragequitbillid());
            warehouseRecord.setMaterialcode(jsonObject.getString("materialcode"));
            warehouseRecord.setCount(storagequitdetail.getCounts());
            warehouseRecord.setSerialNumber(jsonObject.getString("serialNumber"));
            warehouseRecord.setRemark(storagequitdetail.getRemark());
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


                 for (int i = 0; i < warehouseBillDInVos.size(); i++) {
                     for (int i1 = 0; i1 < warehouseBillInVos.size(); i1++) {
                         if (warehouseBillDInVos.get(i).getMaterialcode().equals(warehouseBillInVos.get(i1).getMaterialcode()) && warehouseBillDInVos.get(i).getSupplier().equals(warehouseBillInVos.get(i1).getSupplier())) {
                             warehouseBillInVos.get(i1).setIncount(  warehouseBillInVos.get(i1).getIncount() -warehouseBillDInVos.get(i).getIncount());
                             warehouseBillInVos.get(i1).setInmoney(  warehouseBillInVos.get(i1).getInmoney()-warehouseBillDInVos.get(i).getInmoney());
                             warehouseBillInVos.get(i1).setInprice(BigDecimalUtil.divint(warehouseBillInVos.get(i1).getInmoney(), warehouseBillInVos.get(i1).getIncount()));
                           if( warehouseBillInVos.get(i1).getIncount()==0){
                               warehouseBillInVos.remove(i1);
                           }

                             break;
                         }
                     }
                 }




        List<WarehouseBillVo> outwarehouseBillVos = storageMapper.selectoutStoragebyDate(simpleDateFormat.parse(date));

        List<WarehouseBillVo> outwarehouseBillDVos = storageMapper.selectoutStoragebyDatedashed(simpleDateFormat.parse(date));
        //对比出库红冲的产品


                 for (int i = 0; i < outwarehouseBillDVos.size(); i++) {
                     for (int i1 = 0; i1 < outwarehouseBillVos.size(); i1++) {
                         if (outwarehouseBillDVos.get(i).getMaterialcode().equals(outwarehouseBillVos.get(i1).getMaterialcode()) && outwarehouseBillDVos.get(i).getSupplier().equals(outwarehouseBillVos.get(i1).getSupplier())) {
                             outwarehouseBillVos.get(i1).setOutcount(  outwarehouseBillVos.get(i1).getOutcount() -outwarehouseBillDVos.get(i).getOutcount());
                             outwarehouseBillVos.get(i1).setOutmoney(  outwarehouseBillVos.get(i1).getOutmoney()-outwarehouseBillDVos.get(i).getOutmoney());
                             outwarehouseBillVos.get(i1).setOutprice(BigDecimalUtil.divint(outwarehouseBillVos.get(i1).getOutmoney(), outwarehouseBillVos.get(i1).getOutcount()));
                             if( outwarehouseBillVos.get(i1).getOutcount()==0){
                                 outwarehouseBillVos.remove(i1);
                             }
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

                if(warehouseBillInVo.getMaterialcode().equals(storage.getMaterialcode())){

                    warehouseBillInVo.setThiscount(storage.getStocks());
                    warehouseBillInVo.setThisprice(storage.getPrice().floatValue());
                    warehouseBillInVo.setThismoney(storage.getMoney()!=null?storage.getMoney().floatValue():0);
                    warehouseBillInVo.setPrevcount((warehouseBillInVo.getOutcount()!=null?warehouseBillInVo.getOutcount():0)-(warehouseBillInVo.getIncount()!=null?warehouseBillInVo.getIncount():0)+(warehouseBillInVo.getThiscount()!=null?warehouseBillInVo.getThiscount():0));
                    BigDecimal bg=  new BigDecimal((warehouseBillInVo.getOutmoney()!=null?warehouseBillInVo.getOutmoney():0)).subtract(new BigDecimal((warehouseBillInVo.getInmoney()!=null?warehouseBillInVo.getInmoney():0.00))).add(new BigDecimal((warehouseBillInVo.getThismoney()!=null?warehouseBillInVo.getThismoney():0)));
                    warehouseBillInVo.setPrevmoney(bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                    warehouseBillInVo.setPrevprice(BigDecimalUtil.divint(warehouseBillInVo.getPrevmoney(),warehouseBillInVo.getPrevcount().intValue()));
                    break;
                }
            }
        }

        int rowIndex = 3;
                 System.out.println(warehouseBillInVos.size());
                 for (int i = 0; i < warehouseBillInVos.size(); i++) {
                     if( storageinbillMapper.selectStorageinbillByStockinid(warehouseBillInVos.get(i).getNumber())!=null){
                         if(storageinbillMapper.selectStorageinbillByStockinid(warehouseBillInVos.get(i).getNumber()).getOutsourcewarehouse().indexOf("生产库")==-1) {

                              warehouseBillInVos.remove(i);
                         }
                     }
                     else if(storageoutbillMapper.selectStorageoutbillByStorageoutId(warehouseBillInVos.get(i).getNumber())!=null){
                        if( storageoutbillMapper.selectStorageoutbillByStorageoutId(warehouseBillInVos.get(i).getNumber()).getOutsourcewarehouse().indexOf("生产库")==-1){
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
