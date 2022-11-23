package com.example.netdisk.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.example.netdisk.DBEntity.FolderEntity;
import com.example.netdisk.pojo.query.FolderQuery;
import com.example.netdisk.pojo.response.BaseResponse;
import com.example.netdisk.pojo.response.GetFileByFolderResponse;
import com.example.netdisk.pojo.response.GetFolderInFolderResponse;
import com.example.netdisk.pojo.response.folderRenameResponse;
import com.example.netdisk.service.FolderService;
import com.example.netdisk.utils.FolderUtils;
import com.example.netdisk.vo.AddFolderParam;
import com.example.netdisk.vo.FolderRenameParam;
import com.example.netdisk.vo.GetFolderParam;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Folder")
public class FolderController {


    @Autowired
    private FolderUtils folderUtils;
    @Autowired
    private FolderService folderService;

    @PostMapping("/Add")
    public void Add(@RequestBody  AddFolderParam request) {
        var entity = new FolderEntity();
        entity.UserId = request.UserId;
        entity.FolderName = request.FolderName;
        entity.ParentId = request.ParentId;
        var result = folderService.AddFolder(entity);
    }

    @PostMapping("/GetFolderInFolder")
    public GetFolderInFolderResponse GetFolderInFolder(@RequestBody GetFolderParam request) {
        var query = new FolderQuery();
        var response = new GetFolderInFolderResponse();
        response.setSuccess(true);
        try {
            query.UserId = request.UserId;
            query.ParentId = request.FolderId;
            var result = folderService.GetFolderByParentIdAndUserId(query);
            if (result == null || result.size() <= 0) throw new Exception("Result is null");
            response.setData(result);
            return response;
        } catch (Exception ex) {
            response.setSuccess(false);
            response.setMsg(ex.getMessage());
            return response;
        }
    }

    @PostMapping("/Rename")
    public folderRenameResponse Rename(@RequestBody FolderRenameParam request) {

        var response = new folderRenameResponse();
        var entity = new FolderEntity();
        response.setSuccess(true);
        try {
            entity.Id = request.Id;
            entity.FolderName = request.NewName;
            var result = folderService.FolderRename(entity);
            response.setData(result);
            return response;
        } catch (Exception ex) {
            response.setSuccess(false);
            response.setMsg(ex.getMessage());
            return response;
        }
    }

    @RequestMapping(value = {"/GetByUserId/{userId}"})
    public GetFileByFolderResponse GetFolderByUserId(@PathVariable("userId") int userId) {
        var query = new FolderQuery();
        var response = new GetFileByFolderResponse();
        response.setSuccess(true);
        try {
            query.UserId = userId;
            var result = folderService.GetFolderByUserId(query);
            response.setData(result);
            return response;
        } catch (Exception ex) {
            response.setSuccess(false);
            response.setMsg(ex.getMessage());
            return response;
        }
    }

    @RequestMapping(value = {"/GetFolderTree/{userId}"})
    public GetFileByFolderResponse GetFolderTree(@PathVariable("userId") int userId) {
        var query = new FolderQuery();
        var response = new GetFileByFolderResponse();
        response.setSuccess(true);
        try {
            query.UserId = userId;
            var result = folderService.GetFolderByUserId(query);
            var tree = folderUtils.BuiderFolderTree(result);
            response.setData(tree);
            return response;
        } catch (Exception ex) {
            response.setSuccess(false);
            response.setMsg(ex.getMessage());
            return response;
        }
    }
    @RequestMapping(value = {"/GetFolderById/{folderId}"})
    public BaseResponse GetFolderById(@PathVariable("folderId") int folderId) {
        var query = new FolderQuery();
        var  response =new BaseResponse();
        response.setSuccess(true);
        try {
            query.Id = folderId;
            var result = folderService.GetFolderByFolderId(query);
            response.setData(result);
            return response;
        }catch (Exception ex){
            response.setSuccess(false);
            response.setMsg(ex.getMessage());
            return  response;
        }


    }
}
