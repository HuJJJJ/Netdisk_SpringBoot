package com.example.netdisk.service;

import com.example.netdisk.DBEntity.FolderEntity;
import com.example.netdisk.pojo.query.FolderQuery;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FolderService {
    /**
     * 新增文件夹
     * @param entity 查询信息
     * **/
    public int AddFolder(FolderEntity entity);

    /**
     * 删除文件夹
     * @param entity 查询信息
     * **/
    public int DelFolder(FolderEntity entity);

    /**
     * 获取Folder信息根据id
     * @param query 查询条件
     * **/
    public FolderEntity GetFolderByFolderId(FolderQuery query);

    /**
     * 获取Folder信息根据id
     * @param query 查询条件
     * **/
    public List<FolderEntity> GetFolderByUserId(FolderQuery query);

    /**
     * 获取Folder信息根据id
     * @param query 查询条件
     * **/
    public List<FolderEntity> GetFolderByParentId(FolderQuery query);

    /**
     * 获取所有Folder信息
     * **/
    public List<FolderEntity> GetFolders();

    /**
     * 修改文件夹名称
     * **/
    public int FolderRename(FolderEntity entity);

    /**
     * 获取Folder信息根据userid和folderId
     * @param query 查询条件
     * **/
    public List<FolderEntity> GetFolderByParentIdAndUserId(FolderQuery query);


}
