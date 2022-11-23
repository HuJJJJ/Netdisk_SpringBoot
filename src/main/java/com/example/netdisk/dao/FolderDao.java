package com.example.netdisk.dao;

import com.example.netdisk.DBEntity.FolderEntity;
import com.example.netdisk.DBEntity.SharingEntity;
import com.example.netdisk.DBEntity.UserEntity;
import com.example.netdisk.pojo.query.FolderQuery;
import com.example.netdisk.pojo.query.UserQuery;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FolderDao {
    /**
     * 新增文件夹
     * @param entity 查询信息
     * **/
    public int AddFolder(FolderEntity entity);

    /**
     * 修改文件夹名称
     * **/
    public int FolderRename(FolderEntity entity);

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
     * 获取Folder信息根据userid和folderId
     * @param query 查询条件
     * **/
    public List<FolderEntity> GetFolderByParentIdAndUserId(FolderQuery query);

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



}
