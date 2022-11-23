package com.example.netdisk.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.var;
import org.apache.commons.codec.binary.Hex;
import org.apache.tomcat.util.net.WriteBuffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class FileUtils {

    @Autowired
    ListUtils listUtils;


    /**
     * 获取MultipartFile的MD5
     *
     * @param file 文件
     **/
    public String GetMultipartFileMD5(MultipartFile file) {
        try {
            byte[] bytes = file.getBytes();
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] digest = md5.digest(bytes);
            return new BigInteger(1, digest).toString(16);
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 获取File的MD5
     *
     * @param filePath 文件路径
     **/
    public String GetFileMD5(String filePath) {
        var file = new File(filePath);
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            var fs = new FileInputStream(file);
            byte[] buffer = new byte[4096];
            int len = 0;
            while ((len = fs.read(buffer)) != -1) {
                md5.update(buffer, 0, len);
            }
            return new String(Hex.encodeHex(md5.digest()));
        } catch (Exception ex) {
            return null;
        }
    }


    /**
     * 获取File的扩展名
     *
     * @param FullName 文件全名
     **/
    public String GetFileExtension(String FullName) {
        int extIndex = FullName.lastIndexOf(".");
        var sttr = FullName.split(".");
        if (extIndex == -1) {
            return "";
        } else {
            return FullName.substring(extIndex);
        }
    }


    /**
     * 获取某个文件夹下的所有文件
     *
     * @param dirpath 文件夹路径
     **/
    public static List<String> GetAllFile(String dirpath) {

        File dir = new File(dirpath);

        List<String> allFileList = new ArrayList<>();

        // 判断文件夹是否存在
        if (!dir.exists()) {
            System.out.println("目录不存在");
            return null;
        }

        // 获取文件列表
        File[] fileList = dir.listFiles();
        assert fileList != null;
        for (File file : fileList) {
            if (file.isDirectory()) {
                // 递归处理文件夹
                // 如果不想统计子文件夹则可以将下一行注释掉
                //getAllFile(path);
            } else {
                // 如果是文件则将其加入到文件数组中
                allFileList.add(file.getPath());
            }
        }
        return allFileList;
    }

    /**
     * 合并某个文件夹下的所有文件
     *
     * @param dirpath  文件夹路径
     * @param savePath 最终文件路径
     **/
    public void MergeFile(String dirpath, String savePath) throws IOException {

        //需要合并的文件list
        var fileList = GetAllFile(dirpath);
        fileList.removeIf(x -> x.contains("Offset.txt"));
        fileList = listUtils.ByNumberSort(fileList);

        //最终文件
        var outputfile = new File(savePath);
        var fos = new FileOutputStream(outputfile);

        //缓冲区
        byte[] buffer = new byte[4 * 1024 * 1024];
        int bytesRead;

        for (var file : fileList) {

            FileInputStream fis = new FileInputStream(file);
            while ((bytesRead = fis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }
            fis.close();
        }
        fos.flush();
        fos.close();
    }


    /**
     * 删除文件夹和文件夹下的所有文件
     * **/
    public static Boolean deleteFile(File file) {
        System.gc();

        //判断文件不为null或文件目录存在
        if (file == null || !file.exists()) {
            System.out.println("文件删除失败,请检查文件是否存在以及文件路径是否正确");
            return false;
        }
        //获取目录下子文件
        File[] files = file.listFiles();
        //遍历该目录下的文件对象
        for (File f : files) {
            //判断子目录是否存在子目录,如果是文件则删除
            if (f.isDirectory()) {
                //递归删除目录下的文件
                deleteFile(f);
            } else {
                //文件删除
             var res =    f.delete();
                //打印文件名
                System.out.println("文件名：" + f.getName());
            }
        }
        //文件夹删除
        file.delete();
        System.out.println("目录名：" + file.getName());
        return true;
    }


}
