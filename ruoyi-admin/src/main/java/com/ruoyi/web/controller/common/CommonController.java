package com.ruoyi.web.controller.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.system.util.DateUtil;
import com.ruoyi.system.util.FileUtil;
import com.ruoyi.system.util.Office2PDF;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.config.ServerConfig;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.FileUtils;

import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipOutputStream;

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
