package com.ruoyi.system.util;



import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.utils.poi.ExcelUtil;
import org.apache.poi.ss.usermodel.Workbook;



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
	
}






























