package com.ruoyi.system.util;


import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.MaterialChild;
import com.ruoyi.system.domain.Storage;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;


public class WebUtil {
	
	
	public static void downloadExcel(HttpServletResponse response,Workbook wb,String fileName) throws Exception{
		response.setHeader("Content-Disposition", "attachment;filename="+new String(fileName.getBytes("utf-8"),"iso-8859-1"));
		response.setContentType("application/ynd.ms-excel;charset=UTF-8");
		OutputStream out=response.getOutputStream();
		wb.write(out);
		out.flush();
		out.close();
	}
	
	
	
	
	 public static void downloadExcel(HttpServletResponse response,HttpServletRequest request,String path) {
         try {
             InputStream inStream = ExcelUtil.class.getResourceAsStream(path);
             
             int index=path.lastIndexOf("/");
             
          
             response.reset();
         	response.setContentType("application/ynd.ms-excel;charset=UTF-8");
             response.addHeader("Content-Disposition",
                     "attachment;filename=" +new String(path.substring(index).getBytes("utf-8"),"iso-8859-1"));
           
             
             byte[] b = new byte[200];
             int len;

             while ((len = inStream.read(b)) > 0){
                 response.getOutputStream().write(b, 0, len);
             }
             inStream.close();
         } catch (IOException e) {
             e.printStackTrace();
         }
     }



    /**
     1.将表头写入excel 创建第一行
     2.定义全局index  定位到第几行
     3.循环list 从第一行开始
     4.如果是一个子类在当前行输出具体信息,默认处理第一行
     5.如果有多个子类从子类的第二个对象开始循环,追加新行
     6.在materialChildList.size()>1中定义boolean变量用于只对有多个子类的编码、库存进行 行合并
     合并单元格的起始行位置是当前index-materialChildList.size()+1(加1是因为要到当前行的下一行写)  结束是index
     */
    public static Workbook readStorageList(List<Storage> list) {
        Workbook workbook=new XSSFWorkbook();
        Sheet sheet=workbook.createSheet("库存列表");
        String [] titleName={"编码","名称","型号","品牌","封装","描述","库存量","最后入库时间","最后出库时间","最后退料时间"};
        Row row = sheet.createRow(0);
        sheet.setColumnWidth(1, 4000);
        sheet.setColumnWidth(2, 4500);
        sheet.setColumnWidth(3, 6000);
        sheet.setColumnWidth(4, 6000);
        sheet.setColumnWidth(5, 6000);
        sheet.setColumnWidth(6, 3000);
        sheet.setColumnWidth(7, 4000);
        sheet.setColumnWidth(8, 4000);
        sheet.setColumnWidth(9, 4000);
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
        for (int i = 0; i < titleName.length; i++) {
            row.createCell(i).setCellValue(titleName[i]);
            row.getCell(i).setCellStyle(cellStyle2);
        }
        int index=0;
        for (int i1 = 0; i1 < list.size(); i1++) {
            index++;
            Row row1 = sheet.createRow(index);
            row1.createCell(0).setCellValue(list.get(i1).getMaterialcode());
            row1.getCell(0).setCellStyle(cellStyle2);
            row1.createCell(6).setCellValue(list.get(i1).getStocks());
            row1.getCell(6).setCellStyle(cellStyle2);
            row1.createCell(7).setCellValue(list.get(i1).getuTime());
            row1.getCell(7).setCellStyle(cellStyle2);
            row1.createCell(8).setCellValue(list.get(i1).getoTime());
            row1.getCell(8).setCellStyle(cellStyle2);
            row1.createCell(9).setCellValue(list.get(i1).getqTime());
            row1.getCell(9).setCellStyle(cellStyle2);
            row1.getCell(7).setCellStyle(cellStyle);
            row1.getCell(8).setCellStyle(cellStyle);
            row1.getCell(9).setCellStyle(cellStyle);
            List<MaterialChild> materialChildList = list.get(i1).getMaterialChildList();
            row1.createCell(1).setCellValue(materialChildList.get(0).getName());
            row1.getCell(1).setCellStyle(cellStyle2);
            row1.createCell(2).setCellValue(materialChildList.get(0).getPartnumber());
            row1.getCell(2).setCellStyle(cellStyle2);
            row1.createCell(3).setCellValue(materialChildList.get(0).getManufacture());
            row1.getCell(3).setCellStyle(cellStyle2);
            row1.createCell(4).setCellValue(materialChildList.get(0).getFootprint());
            row1.getCell(4).setCellStyle(cellStyle2);
            row1.createCell(5).setCellValue(materialChildList.get(0).getDescription());
            if(materialChildList.size()>1){
                for (int i = 1; i < materialChildList.size(); i++) {
                    index++;
                    Row row2 = sheet.createRow(index);
                    row2.createCell(1).setCellValue(materialChildList.get(i).getName());
                    row2.getCell(1).setCellStyle(cellStyle2);
                    row2.createCell(2).setCellValue(materialChildList.get(i).getPartnumber());
                    row2.getCell(2).setCellStyle(cellStyle2);
                }
                boolean flag=true;
                if(flag) {
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
                    }
                    flag = false;
                }
        }
        return workbook;
    }
}






























