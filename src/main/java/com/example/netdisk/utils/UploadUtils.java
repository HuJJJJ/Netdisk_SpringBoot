package com.example.netdisk.utils;

import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Paths;


@Service
public class UploadUtils {


    /**
     * 获取文件保存的根路径
     **/
    public static String GetRootTempPath() throws FileNotFoundException {
        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        File upload = new File(path.getAbsoluteFile(), "static/temp");
        if (!upload.exists()) {
            upload.mkdirs();
        }
        return upload.getPath();
    }

    public static String GetRootPath() throws FileNotFoundException {
        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        File upload = new File(path.getAbsoluteFile(), "static/upload");
        if (!upload.exists()) {
            upload.mkdirs();
        }
        return upload.getPath();
    }




    /**
     * 保存文件
     *
     * @param file        需要保存的文件
     * @param saveDirPath 文件夹路径
     * @param fileName    文件名
     **/
    public String SaveFile(MultipartFile file, String saveDirPath, int fileName)   {

        try {
            //判断文件夹是否存在，不存在则创建
            java.io.File saveFile = new File(saveDirPath);
            if (!saveFile.exists()) {
                saveFile.mkdirs();
            }

            //保存文件
            FileInputStream fileInputStream = (FileInputStream) file.getInputStream();
            String savePath = Paths.get(saveDirPath, String.valueOf(fileName)).toString();
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(savePath));
            byte[] bs = new byte[1024];
            int len;
            while ((len = fileInputStream.read(bs)) != -1) {
                bos.write(bs, 0, len);
            }
            bos.flush();
            bos.close();

            return savePath;
        }catch (IOException ex){
            return null;
        }catch (Exception ex){
            return null;
        }

    }

    /**
     * 保存偏移量
     *
     * @param dirPath 保存文件的文件夹路径
     * @param Offset  偏移量
     **/
    public void saveOffset(String dirPath, int Offset) throws IOException {
        String path = Paths.get(dirPath, "Offset.txt").toString();
        File offsetFile = new File(path);
        if (!offsetFile.exists()) {
            offsetFile.createNewFile();
        }
        FileWriter fw = new FileWriter(offsetFile.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(String.valueOf(Offset));

        bw.flush();
        bw.close();
        fw.close();
    }


}
