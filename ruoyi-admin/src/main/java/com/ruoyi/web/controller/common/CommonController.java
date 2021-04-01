package com.ruoyi.web.controller.common;

import com.ruoyi.common.config.Global;
import com.ruoyi.common.config.ServerConfig;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.system.domain.Bom;
import com.ruoyi.system.domain.Bomdetail;
import com.ruoyi.system.domain.MaterialChild;
import com.ruoyi.system.domain.Storage;
import com.ruoyi.system.service.IBomService;
import com.ruoyi.system.service.IBomdetailService;
import com.ruoyi.system.service.IStorageService;
import com.ruoyi.system.util.DateUtil;
import com.ruoyi.system.util.FileUtil;
import com.ruoyi.system.util.Office2PDF;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;

/**
 * 通用请求处理
 * 
 * @author ruoyi
 */
@Controller
public class CommonController
{
    private static final Logger log = LoggerFactory.getLogger(CommonController.class);

    @Autowired
    private ServerConfig serverConfig;
    @Autowired
    private IStorageService storageService;
    @Autowired
    private IBomService bomService;
    @Autowired
    private IBomdetailService bomdetailService;


    @Value("${ruoyi.profile}")
    private  String BASE_PATH;


    /**
     * 通用下载请求
     * 
     * @param fileName 文件名称
     * @param delete 是否删除
     */
    @GetMapping("common/download")
    public void fileDownload(String fileName, Boolean delete, HttpServletResponse response, HttpServletRequest request)
    {
        try
        {
            if (!FileUtils.isValidFilename(fileName))
            {
                throw new Exception(StringUtils.format("文件名称({})非法，不允许下载。 ", fileName));
            }
            String realFileName = System.currentTimeMillis() + fileName.substring(fileName.indexOf("_") + 1);
            String filePath = Global.getDownloadPath() + fileName;

            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition",
                    "attachment;fileName=" + FileUtils.setFileDownloadHeader(request, realFileName));
            FileUtils.writeBytes(filePath, response.getOutputStream());
            if (delete)
            {
                FileUtils.deleteFile(filePath);
            }
        }
        catch (Exception e)
        {
            log.error("下载文件失败", e);
        }
    }

    /**
     * 通用上传请求
     */
    @PostMapping("/common/upload")
    @ResponseBody
    public AjaxResult uploadFile(MultipartFile file) throws Exception
    {
        try
        {
            // 上传文件路径
            String filePath = Global.getUploadPath();
            // 上传并返回新文件名称
            String fileName = FileUploadUtils.upload(filePath, file);
            String url = serverConfig.getUrl() + fileName;
            AjaxResult ajax = AjaxResult.success();
            ajax.put("fileName", fileName);
            ajax.put("url", url);
            return ajax;
        }
        catch (Exception e)
        {
            return AjaxResult.error(e.getMessage());
        }
    }


    private void  writeStorage() throws Exception{
        List<Storage> list = storageService.selectStorageList(null);
        String file = Global.getProfile()+"/template/storage.xlsx";
        XSSFWorkbook workbook=new XSSFWorkbook(new FileInputStream(new File(file)));
        XSSFSheet sheet = workbook.getSheetAt(0);
        CellStyle cellStyle2=workbook.createCellStyle();
        cellStyle2.setAlignment(HorizontalAlignment.CENTER);
        cellStyle2.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle2.setWrapText(true);
        CreationHelper createHelper = workbook.getCreationHelper();
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-MM-dd"));
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setWrapText(true);
        int index=0;
        for (int i1 = 0; i1 < list.size(); i1++) {
            index++;
            Row row1 = sheet.createRow(index);
            row1.createCell(0).setCellValue(list.get(i1).getMaterialcode());
            row1.createCell(6).setCellValue(list.get(i1).getStocks());
            row1.createCell(10).setCellValue(list.get(i1).getUTime());
            row1.getCell(10).setCellStyle(cellStyle);
            row1.createCell(8).setCellValue(list.get(i1).getOTime());
            row1.getCell(8).setCellStyle(cellStyle);
            row1.createCell(9).setCellValue(list.get(i1).getQTime());
            row1.getCell(9).setCellStyle(cellStyle);
            row1.createCell(7).setCellValue(list.get(i1).getPrice()!=null?list.get(i1).getPrice():0);
            List<MaterialChild> materialChildList = list.get(i1).getMaterialChildList();
            if(materialChildList.size()==1){
                row1.createCell(1).setCellValue(materialChildList.get(0).getName());
                row1.createCell(2).setCellValue(materialChildList.get(0).getPartnumber());
                row1.createCell(3).setCellValue(materialChildList.get(0).getManufacture());
                row1.createCell(4).setCellValue(materialChildList.get(0).getFootprint());
                row1.createCell(5).setCellValue(materialChildList.get(0).getDescription());
                for (int i = 0; i < row1.getLastCellNum(); i++) {
                    if(i<8){
                        row1.getCell(i).setCellStyle(cellStyle2);
                    }

                }
                for (int i = 8; i < row1.getLastCellNum(); i++) {
                    row1.getCell(i).setCellStyle(cellStyle);
                }

            }


            if(materialChildList.size()>1){
                for (int i = 1; i < materialChildList.size(); i++) {
                    index++;
                    Row row2 = sheet.createRow(index);
                    row2.createCell(1).setCellValue(materialChildList.get(i).getName());
                    row2.createCell(2).setCellValue(materialChildList.get(i).getPartnumber());
                    for (Cell cell : row2) {
                        cell.setCellStyle(cellStyle2);
                    }
                }

                sheet.addMergedRegion(new CellRangeAddress(
                        index-materialChildList.size()+1,   //起始行
                        index,   //结束行
                        0,   //起始列
                        0    //结束列
                ));
                sheet.addMergedRegion(new CellRangeAddress(
                        index-materialChildList.size()+1,   //起始行
                        index,   //结束行
                        6,   //起始列
                        6    //结束列
                ));
                sheet.addMergedRegion(new CellRangeAddress(
                        index-materialChildList.size()+1,   //起始行
                        index,   //结束行
                        7,   //起始列
                        7    //结束列
                ));
                sheet.addMergedRegion(new CellRangeAddress(
                        index-materialChildList.size()+1,   //起始行
                        index,   //结束行
                        8,   //起始列
                        8    //结束列
                ));
                sheet.addMergedRegion(new CellRangeAddress(
                        index-materialChildList.size()+1,   //起始行
                        index,   //结束行
                        9,   //起始列
                        9    //结束列
                ));
                sheet.addMergedRegion(new CellRangeAddress(
                        index-materialChildList.size()+1,   //起始行
                        index,   //结束行
                        10,   //起始列
                        10    //结束列
                ));
            }

        }
        String filename=Global.getProfile()+"/backups/"+System.currentTimeMillis()+"库存列表.xlsx";
        FileOutputStream outputStream=new FileOutputStream(filename);
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
        workbook.close();
    }

    private void writeBom() throws Exception{
        List<Bom> boms = bomService.selectBomList(null);
        for (Bom bom : boms) {
            Bomdetail bomdetail = new Bomdetail();
            bomdetail.setBomid(bom.getId());
            List<Bomdetail> bomdetails = bomdetailService.selectBomdetailList(bomdetail);
            String file = Global.getProfile() + "/template/BOM_exportTemplate.xlsx";
            XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(file));
            XSSFSheet sheetAt = workbook.getSheetAt(0);
            CellStyle cellStyle = workbook.createCellStyle();
            CreationHelper createHelper = workbook.getCreationHelper();
            cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-MM-dd"));
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
            cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            cellStyle.setWrapText(true);
            sheetAt.getRow(0).getCell(4).setCellValue(bom.getName());
            sheetAt.getRow(1).getCell(4).setCellValue(bom.getVersion());
            sheetAt.getRow(2).getCell(4).setCellValue(bom.getTimeofmaking());
            sheetAt.getRow(2).getCell(4).setCellStyle(cellStyle);
            sheetAt.getRow(0).getCell(7).setCellValue(bom.getNumber());
            sheetAt.getRow(1).getCell(7).setCellValue(bom.getPartnumber());
            sheetAt.getRow(2).getCell(7).setCellValue(bom.getRemark());

            int index = 5;
            Font font = workbook.createFont();
            font.setFontName("黑体");
            font.setFontHeightInPoints((short) 24);//设置字体大小
            Font font1 = workbook.createFont();
            font.setFontHeightInPoints((short) 16);
            CellStyle cellStyle2 = workbook.createCellStyle();
            cellStyle2.setAlignment(HorizontalAlignment.CENTER);
            cellStyle2.setVerticalAlignment(VerticalAlignment.CENTER);
            cellStyle2.setFont(font1);
            cellStyle2.setWrapText(true);
            for (int i = 0; i < bomdetails.size(); i++) {
                XSSFRow row = sheetAt.createRow(index++);
                row.createCell(0).setCellValue(i + 1);
                row.createCell(1).setCellValue(bomdetails.get(i).getCode() != null ? bomdetails.get(i).getCode().toString() : "");
                row.createCell(2).setCellValue(bomdetails.get(i).getLink() != null ? bomdetails.get(i).getLink().toString() : "");
                row.createCell(3).setCellValue(bomdetails.get(i).getComment() != null ? bomdetails.get(i).getComment().toString() : "");
                row.createCell(4).setCellValue(bomdetails.get(i).getFootprint() != null ? bomdetails.get(i).getFootprint().toString() : "");
                row.createCell(5).setCellValue(bomdetails.get(i).getDescription() != null ? bomdetails.get(i).getDescription().toString() : "");
                row.createCell(6).setCellValue(bomdetails.get(i).getParttype() != null ? bomdetails.get(i).getParttype().toString() : "");
                row.createCell(7).setCellValue(bomdetails.get(i).getDesignator() != null ? bomdetails.get(i).getDesignator().toString() : "");
                row.createCell(8).setCellValue(bomdetails.get(i).getQuantity() != null ? bomdetails.get(i).getQuantity().toString() : "");
                row.createCell(9).setCellValue(bomdetails.get(i).getPrice() != null ? bomdetails.get(i).getPrice().toString() : "");

                for (Cell cell : row) {
                    cell.setCellStyle(cellStyle2);
                }
            }
            String filename=Global.getProfile()+"/backups/"+bom.getPartnumber () +"bom列表.xlsx";
            FileOutputStream outputStream=new FileOutputStream(filename);
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
            outputStream.flush();
            outputStream.close();
            workbook.close();
        }

    }

    /**
     * 本地资源通用下载
     */
    @GetMapping("/common/download/backups")
    public void backups( HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        writeStorage();
        writeBom();
        File file = new File("C:\\ruoyi\\uploadPathTksys\\backups");
        File[] srcfile = file.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return pathname.toString().endsWith(".xlsx");
            }
        });
        String downloadPath=Global.getProfile()+"/backups/backups.zip";
        File zipfile=new File(downloadPath);
        FileUtil.zipFiles(srcfile,zipfile);
        for (File file1 : srcfile) {
            file1.delete();
        }
        ServletOutputStream outputStream = response.getOutputStream();
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition",
                "attachment;fileName=backups.zip");
        FileUtils.writeBytes(downloadPath, response.getOutputStream());

    }

    /**
     * 本地资源通用下载
     */
    @GetMapping("/common/download/resource")
    public void resourceDownload(String resource, HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {



        // 本地资源路径
        String localPath = Global.getProfile();
        // 数据库资源地址
        String downloadPath = localPath + StringUtils.substringAfter(resource.split(",")[0], Constants.RESOURCE_PREFIX);
        // 下载名称
        String downloadName = StringUtils.substringAfterLast(downloadPath, "/");
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition",
                "attachment;fileName=" + FileUtils.setFileDownloadHeader(request, downloadName));

            FileUtils.writeBytes(downloadPath, response.getOutputStream());


    }


    @GetMapping("/common/download/resourcezip")
    public void resourceDownloadzip(String accessory,String offerProject,String cTime, HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {

       final String  suffix=".zip";
        // 本地资源路径
        String localPath = Global.getProfile();
        String[] split = accessory.split(",");
        File[] srcfile=new File[split.length];
        for (int i = 0; i < split.length; i++) {
            java.io.File  file=new File(localPath + StringUtils.substringAfter(accessory.split(",")[i], Constants.RESOURCE_PREFIX));
            srcfile[i]=file;
        }
        /*String path=localPath+*/
        File zipfile=new File(localPath+"/zip"+"/"+offerProject+ DateUtil.dateToStamp(cTime)+suffix);
        FileUtil.zipFiles(srcfile,zipfile);
      String fileName=offerProject+ DateUtil.dateToStamp(cTime)+suffix;
      InputStream inputStream=new FileInputStream(localPath+"/zip"+"/"+offerProject+ DateUtil.dateToStamp(cTime)+suffix);
        fileName= URLEncoder.encode(fileName,"utf-8");


        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition",
                "attachment;fileName=" + FileUtils.setFileDownloadHeader(request, fileName));

        FileUtils.writeBytes(localPath+"/zip"+"/"+offerProject+ DateUtil.dateToStamp(cTime)+suffix, response.getOutputStream());


    }

    @GetMapping("/common/read")
    public void readFile(HttpServletResponse res ,@RequestParam("fileName") String fileName) throws Exception{

        fileName=fileName.substring(fileName.indexOf("upload"));
        InputStream in = null;
        OutputStream out = null;
        String filePath =  fileHandler(fileName);
        //判断是pdf还是word还是excel
        //若是pdf直接读 否则转pdf 再读  //
        try{
            if(filePath != null){
                in = new FileInputStream(filePath);
            }
            res.setContentType("application/pdf");
            out = res.getOutputStream();
            byte[] b = new byte[1024];
            int len = 0;
            while((len = in.read(b)) != -1){
                out.write(b);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(in != null){
                in.close();
            }
            if(out != null){
                out.close();
            }
        }
    }


    /**
     * 文件处理
     * @param fileName
     * @return
     */
    public String fileHandler(String fileName){
        String fileSuffix = FileUtil.getFileSuffix(fileName);
        System.out.println(fileSuffix);
        if("pdf".equals(fileSuffix))
        {
            return BASE_PATH +"/"+ fileName;
        }
        else
        {
            return Office2PDF.openOfficeToPDF(BASE_PATH+"/" + fileName).getAbsolutePath();
        }

    }


}
