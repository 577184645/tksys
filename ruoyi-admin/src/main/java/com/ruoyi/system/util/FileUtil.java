package com.ruoyi.system.util;

import com.github.pagehelper.util.StringUtil;
import org.apache.commons.lang.StringUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * @author QC
 * @create 2020-09-10 14:59
 */
public class FileUtil {




    /**
     * 功能:压缩多个文件成一个zip文件
     * @param srcfile：源文件列表
     * @param zipfile：压缩后的文件
     */
    public static void zipFiles(File[] srcfile,File zipfile){
        byte[] buf=new byte[1024];
        try {
            //ZipOutputStream类：完成文件或文件夹的压缩
            ZipOutputStream out=new ZipOutputStream(new FileOutputStream(zipfile));
            for(int i=0;i<srcfile.length;i++){
                FileInputStream in=new FileInputStream(srcfile[i]);
                out.putNextEntry(new ZipEntry(srcfile[i].getName()));
                int len;
                while((len=in.read(buf))>0){
                    out.write(buf,0,len);
                }
                out.closeEntry();
                in.close();
            }
            out.close();
            System.out.println("压缩完成.");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    /**
     * 压缩文件(zip)
     * @param srcFiles
     * @param zippath
     * @throws Exception
     */
    public static void Zip(List<File> srcFiles , String zippath)throws Exception {
        File file=new File(zippath);
        if(!file.exists()){
            file.createNewFile();
        }
        FileOutputStream out = new FileOutputStream(file);
        long start = System.currentTimeMillis();
        ZipOutputStream zos = null ;
        try {
            zos = new ZipOutputStream(out);
            for (File srcFile : srcFiles) {
                byte[] buf = new byte[4096 * 1024];
                zos.putNextEntry(new ZipEntry(srcFile.getName()));
                int len;
                FileInputStream in = new FileInputStream(srcFile);
                while ((len = in.read(buf)) != -1){
                    zos.write(buf, 0, len);
                }
                zos.closeEntry();
                in.close();
            }
            long end = System.currentTimeMillis();
            System.out.println("压缩完成，耗时：" + (end - start) +" ms");
        } catch (Exception e) {
            throw new RuntimeException("zip error from ZipUtils",e);
        }finally{
            if(zos != null){
                try {
                    zos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 解压zip格式文件
     *
     * @param zippath zip文件路径。
     * @param targetDir  要解压到的目标路径。
     * @return 如果目标文件不是zip文件则返回false。
     * @throws IOException 如果发生I/O错误。
     */
    public static boolean decompressZip(String zippath, String targetDir) throws IOException {
        File originFile=new File(zippath);
        if (!targetDir.endsWith(File.separator)) {
            targetDir += File.separator;
        }
        ZipFile zipFile = new ZipFile(originFile, Charset.forName("GBK"));
        ZipEntry zipEntry;
        Enumeration<? extends ZipEntry> entry = zipFile.entries();
        while (entry.hasMoreElements()) {
            zipEntry = entry.nextElement();
            String fileName = zipEntry.getName();
            File outputFile = new File(targetDir + fileName);
            if (zipEntry.isDirectory()) {
                forceMkdirs(outputFile);
                continue;
            } else if (!outputFile.getParentFile().exists()) {
                forceMkdirs(outputFile.getParent());
            }
            OutputStream outputStream = new FileOutputStream(outputFile);
            InputStream inputStream = zipFile.getInputStream(zipEntry);
            int len;
            byte[] buffer = new byte[8192];
            while (-1 != (len = inputStream.read(buffer))) {
                outputStream.write(buffer, 0, len);
            }
            outputStream.close();
            inputStream.close();
        }
        zipFile.close();
        return true;
    }
    private static File forceMkdirs(File file) {
        if (!file.exists()) {
            file.mkdirs();
        } else if (!file.isDirectory()) {
            file.delete();
            file.mkdirs();
        }
        return file;
    }

    private static File forceMkdirs(String pathName) {
        return forceMkdirs(new File(pathName));
    }
    /**
     *获取路径下所有文件(递归遍历)
     * @param fileList
     * @param path
     * @throws Exception
     */
    public static void getFiles(List<File>fileList, String path){
        try {
            File file = new File(path);
            if(file.isDirectory()){
                File []files = file.listFiles();
                for(File fileIndex:files){
                    //如果这个文件是目录，则进行递归搜索
                    if(fileIndex.isDirectory()){
                        getFiles(fileList,fileIndex.getPath());
                    }else {
                        //如果文件是普通文件，则将文件句柄放入集合中
                        fileList.add(fileIndex);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     *
     * @param dir 将要删除的文件目录
     * @return boolean Returns "true" if all deletions were successful.
     * If a deletion fails, the method stops attempting to
     * delete and returns "false".
     */
    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            //递归删除目录中的子目录下
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }

    /**
     * 复制文件
     * @param sourcefile 要复制的文件
     * @param targetPath 目标文件夹路径
     * @throws IOException
     */
    public static void copyFile(File sourcefile, String targetPath) throws IOException {
        File targetFile =new File(targetPath+"/"+sourcefile.getName());

        //新建文件输入流并对它进行缓冲
        FileInputStream input = new FileInputStream(sourcefile);
        BufferedInputStream inbuff = new BufferedInputStream(input);

        //新建文件输出流并对它进行缓冲
        FileOutputStream out = new FileOutputStream(targetFile);
        BufferedOutputStream outbuff = new BufferedOutputStream(out);

        //缓冲数组
        byte[] b = new byte[1024 * 5];
        int len = 0;
        while ((len = inbuff.read(b)) != -1) {
            outbuff.write(b, 0, len);
        }
        //刷新此缓冲的输出流
        outbuff.flush();

        //关闭流
        inbuff.close();
        outbuff.close();
        out.close();
        input.close();
    }

    /**
     * 移动文件
     * @param sourcefile 文件
     * @param targetPath 目标路径
     */
    public static void moveFileAndDelete(File sourcefile, String targetPath){
        try {
            copyFile(sourcefile, targetPath);
            sourcefile.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 移动文件(不删除)
     * @param sourcefile 文件
     * @param targetPath 目标路径
     */
    public static void moveFile(File sourcefile, String targetPath){
        try {
            copyFile(sourcefile, targetPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 搜索文件(模糊匹配)
     * @param fileName 文件名
     * @param targetPath 目标路径
     * @param suffix 指定文件后缀(jpg)
     * @return
     */
    public static List<File> searchFile(String fileName, String targetPath,String suffix){
        List<File> list=new ArrayList<>();
        getFiles(list,targetPath);
        if(list!=null && list.size()>0){
            List<File> result=new ArrayList<>();
            for (File file:list){
                if(file.getName().contains(fileName)){
                    if(!StringUtils.isBlank(suffix)){
                        if(getSuffix(file.getName()).equals(suffix)){
                            result.add(file);
                        }else{
                            continue;
                        }
                    }else{
                        result.add(file);
                    }

                }
            }
            return result;
        }
        return null;
    }

    /**
     * 判断文件后缀
     * @param fileName
     * @return
     */
    public static String getSuffix(String fileName){
        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
        return suffix;
    }

    /**
     * 获取文件名
     * @param filePath
     * @return
     */
    public static String getFileName(String filePath) {
        String filename = filePath.substring(filePath.lastIndexOf("/") + 1);
        return filename;
    }

    public static String getFileSuffix(String fileName){
        if(StringUtil.isEmpty(fileName) || fileName.lastIndexOf(".")<0 ){
                       return "error";
                    }
                return fileName.substring(fileName.lastIndexOf(".")+1);
    }




    }
