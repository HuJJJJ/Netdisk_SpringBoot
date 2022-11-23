package com.example.netdisk.controller;

import com.example.netdisk.DBEntity.FileInfoEntity;
import com.example.netdisk.pojo.query.FileInfoQuery;
import com.example.netdisk.pojo.response.AddFileInfoResponse;
import com.example.netdisk.pojo.response.MergeFileResponse;
import com.example.netdisk.pojo.response.ScanFileMD5Response;
import com.example.netdisk.pojo.response.ShardUploadResponse;
import com.example.netdisk.service.FileInfoService;
import com.example.netdisk.utils.FileUtils;
import com.example.netdisk.utils.ProgressUtils;
import com.example.netdisk.utils.ReflectUtil;
import com.example.netdisk.utils.UploadUtils;
import com.example.netdisk.vo.AddFileParam;
import com.example.netdisk.vo.MerageFileParam;
import com.example.netdisk.vo.SacnFileMD5Param;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Random;

@RestController
@RequestMapping("Upload")
@Data
@AllArgsConstructor
@ConfigurationProperties(prefix = "hjjjjconfig")
public class FileUploadController {

private String downloadBasePath;

    //上传工具类
    @Autowired
    UploadUtils uploadUtils;


    @Autowired
    FileInfoService fileInfoService;

    //文件扩展类
    @Autowired
    FileUtils fileUtils;


    //获取进度条
    @Autowired
    ProgressUtils progressUtils;

    //文件保存的临时路径
    private String UploadTempPath;

    //文件最终保存路径
    private String UploadPath;

    public FileUploadController() {
        try {

            UploadTempPath = UploadUtils.GetRootTempPath();
            UploadPath = UploadUtils.GetRootPath();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //查看服务器上是否有相同的文件，相同文件则实现秒传
    @PostMapping("/ScanFileMD5")
    public ScanFileMD5Response ScanFilesMD5(@RequestBody SacnFileMD5Param request) throws Exception {
        System.out.println(downloadBasePath);
        ScanFileMD5Response response =new ScanFileMD5Response();
        response.setSuccess(true);
        try {
            //查找是否有相同md5的文件
            FileInfoQuery query = new FileInfoQuery();
            query.MD5 = request.MD5;
            query.UserId = request.UserId;
            var result = fileInfoService.GetFileInfoByMD5(query);
            if (result.size() <= 0) throw new Exception("没有有相同的文件");
            //有则直接新增记录
            var entity = new FileInfoEntity();
            entity.FileName = request.FileName;
            entity.MD5 = request.MD5;
            entity.FilePath = downloadBasePath+result.get(0).NewFileName;
            entity.UserId = request.UserId;
            entity.NewFileName = result.get(0).NewFileName;
            entity.Type = request.Type;
            entity.IsDelete =0;
            entity.FolderId =0;
            var addResult = fileInfoService.AddFileInfo(entity);
            if(addResult<=0) throw  new Exception("添加记录失败");
            response.setData(true);
            return response;
        } catch (Exception ex) {
        response.setSuccess(false);
        response.setData(false);
        response.setMsg(ex.getMessage());
        return response;
        }
    }


    /**
     * 文件分片上传
     *
     * @param File               文件
     * @param md5                文件md5值
     * @param currentChunkNumber 当前第几片
     * @param uuid               唯一标识
     * @param totalChunk         总片数
     **/
    @PostMapping(value = "/Shard", consumes = "multipart/form-data")
    public ShardUploadResponse ShardToUpload(MultipartFile File, String uuid, int currentChunkNumber, String md5, int totalChunk) throws IOException {

        var response = new ShardUploadResponse();
        try {
            if (File == null) throw new Exception("文件为空");

            //初始化response
            response.setCode(200);
            response.setSuccess(true);

            //保存文件的文件夹路径
            String rootPath = uploadUtils.GetRootTempPath();
            String saveDirPath = Paths.get(rootPath, String.valueOf(uuid)).toString();
            //保存文件
            var uploadPath = uploadUtils.SaveFile(File, saveDirPath, currentChunkNumber);

            if (uploadPath != null) {
                if (!md5.equals(fileUtils.GetFileMD5(uploadPath))) throw new Exception("md5值不正确");
            } else throw new Exception("文件保存失败");

            //保存偏移量
            uploadUtils.saveOffset(saveDirPath, currentChunkNumber);
            //获取当前文件上传进度
            var progress = progressUtils.ExecPercent(currentChunkNumber, totalChunk);
            response.setData(progress);
            return response;
        } catch (Exception ex) {
            response.setSuccess(false);
            response.setCode(5000);
            response.setMsg(ex.getMessage());
            return response;
        }
    }


    /**
     * 合并文件
     *
     * @param request 文件信息
     **/
    @PostMapping("/MergeFile")
    public MergeFileResponse MergeFile(@RequestBody MerageFileParam request) throws IOException {
        var response = new MergeFileResponse();
        response.setSuccess(true);
        try {
            //临时文件路径
            var tempPath = Paths.get(UploadTempPath, request.Uuid).toString();

            //随机文件名,为了防止文件名冲突
            var rnd = new Random();
            var fileName = System.currentTimeMillis() + rnd.nextInt(999) + fileUtils.GetFileExtension(request.FileName);
            //最终保存路径
            var savePath = Paths.get(UploadPath, fileName).toString();

            fileUtils.MergeFile(tempPath, savePath);

            String md5 = fileUtils.GetFileMD5(savePath);
            if (!md5.equals(request.Md5)) throw new Exception("md5不正确");
            response.setData(fileName);
            response.setCode(20000);
            //删除临时文件夹
            fileUtils.deleteFile(new File(tempPath));
            return response;
        } catch (Exception ex) {
            response.setSuccess(false);
            response.setMsg("文件合并失败:" + ex.getMessage());
            return response;
        }
    }




}