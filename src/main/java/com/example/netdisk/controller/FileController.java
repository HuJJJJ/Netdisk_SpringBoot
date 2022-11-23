package com.example.netdisk.controller;


import com.example.netdisk.DBEntity.FileInfoEntity;
import com.example.netdisk.pojo.query.FileInfoQuery;
import com.example.netdisk.pojo.response.*;
import com.example.netdisk.service.FileInfoService;
import com.example.netdisk.utils.ReflectUtil;
import com.example.netdisk.utils.UploadUtils;
import com.example.netdisk.vo.*;
import lombok.Data;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("File")
@Data
@ConfigurationProperties(prefix = "hjjjjconfig")
public class FileController {

    private String downloadBasePath;
    @Autowired
    FileInfoService fileInfoService;
    @Autowired
    UploadUtils uploadUtils;


    /**
     * 根据type和id获取用户文件列表
     **/
    @PostMapping("/Move")
    public MoveFileResponse MoveFile(@RequestBody MoveFileParam request) {
        var response = new MoveFileResponse();
        response.setSuccess(true);
        var entity = new FileInfoEntity();
        try {
            entity.FolderId = request.NewFolderId;
            entity.Id = request.FileId;
            var result = fileInfoService.MoveFile(entity);
            response.setData(result);
            return response;
        } catch (Exception ex) {
            response.setSuccess(false);
            response.setMsg(ex.getMessage());
            return response;
        }
    }


    /**
     * 根据type和id获取用户文件列表
     **/
    @PostMapping("/GetFileList")
    public GetFileResponse GetFileList(@RequestBody GetFileParam request) {
        GetFileResponse response = new GetFileResponse();
        try {
            var query = new FileInfoQuery();
            query.UserId = request.UserId;
            query.Type = request.Type;
            List<FileInfoEntity> result = new ArrayList<>();
            if (query.Type > 0) {
                result = fileInfoService.GetFileInfoByType(query);

            } else if (query.Type == 0) {
                result = fileInfoService.GetFileInfoByUserId(query);
            }
            if (result == null || result.size() <= 0) throw new Exception("找不到文件");
            response.setData(result);
        } catch (Exception ex) {
            response.setSuccess(false);
            response.setMsg(ex.getMessage());
            return response;
        }
        return response;
    }

    @PostMapping("/GetFileByFolder")
    public GetFileByFolderResponse GetFileByFolderIdAndUserId(@RequestBody GetFolderParam request) {
        var entity = new FileInfoQuery();
        var response = new GetFileByFolderResponse();
        List<FileInfoEntity> result = new ArrayList<>();
        response.setSuccess(true);
        try {
            entity.UserId = request.UserId;
            entity.FolderId = request.FolderId;
            result = fileInfoService.GetFileInfoByUserIdAndFolderId(entity);
            response.setData(result);
            return response;
        } catch (Exception ex) {
            response.setSuccess(false);
            response.setMsg(ex.getMessage());
            return response;
        }
    }


    /**
     * 删除文件
     **/
    @PostMapping("/DeleteFile")
    public void Delete(@RequestBody FileParam request) {
        FileInfoQuery entity = new FileInfoQuery();
        entity.setFileId(request.FileId);
        var result = fileInfoService.DeleteFile(entity);
        int a = 0;
    }

    /**
     * 添加文件信息到数据库
     *
     * @param fileParam 文件信息
     **/
    @PostMapping("AddFileInfo")
    public AddFileInfoResponse AddFileInfo(@RequestBody AddFileParam fileParam) {
        AddFileInfoResponse response = new AddFileInfoResponse();
        response.setSuccess(true);
        try {
            //通过反射类型c转换
            FileInfoEntity fileInfo = ReflectUtil.convertObject(fileParam, FileInfoEntity.class);
            fileInfo.setUserId(fileParam.UserId);
            fileInfo.setType(fileParam.Type);
            fileInfo.setFolderId(0);
            fileInfo.setFilePath(downloadBasePath + fileParam.NewFileName);
            var result = fileInfoService.AddFileInfo(fileInfo);

            if (result > 0) {
                response.setData(true);
            } else {
                response.setData(false);
            }
            return response;
        } catch (Exception ex) {
            response.setData(false);
            response.setMsg(ex.getMessage());
            return response;
        }
    }


    /**
     * 重命名文件
     *
     * @param request 文件信息
     **/
    @PostMapping("/Rename")
    public RenameResponse FileRename(@RequestBody RenameParam request) {
        var response = new RenameResponse();
        response.setSuccess(true);
        try {
            var entity = new FileInfoQuery();
            entity.setNewName(request.NewName);
            entity.setFileId(request.FileId);
            var result = fileInfoService.FileInfoRename(entity);
            response.setData(result > 0 ? true : false);
            return response;
        } catch (Exception ex) {
            response.setSuccess(false);
            response.setMsg(ex.getMessage());
            return response;
        }
    }


    /**
     * 下载文件
     *
     * @param request 文件信息
     **/
    @PostMapping("/Download")
    public void DownloadFile(@RequestBody FileParam request, HttpServletResponse response) throws IOException {
        var entity = new FileInfoQuery();
        entity.FileId = request.FileId;
        var fileInfo = fileInfoService.GetFileInfoByFileId(entity);

        File file = new File(uploadUtils.GetRootPath() + "\\" + fileInfo.get(0).NewFileName);
        byte[] buffer = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            //文件是否存在
            if (file.exists()) {
                //设置响应
                response.setContentType("application/octet-stream;charset=UTF-8");
                response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
                response.setHeader("Content-Disposition", "attachment;filename=" + fileInfo.get(0).FileName);
                response.setCharacterEncoding("UTF-8");
                os = response.getOutputStream();
                bis = new BufferedInputStream(new FileInputStream(file));
                while (bis.read(buffer) != -1) {
                    os.write(buffer);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bis != null) {
                    bis.close();
                }
                if (os != null) {
                    os.flush();
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

}
