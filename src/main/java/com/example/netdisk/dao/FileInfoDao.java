package com.example.netdisk.dao;


import com.example.netdisk.DBEntity.FileInfoEntity;
import com.example.netdisk.pojo.query.FileInfoQuery;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FileInfoDao {

    /**
     * 新增文件信息
     * @param entity 文件信息
     * **/
    public int AddFileInfo(FileInfoEntity entity);

    /**
     * 通过FileId删除文件信息
     * @param entity 查询条件
     * **/
    public int DeleteFile(FileInfoQuery entity);

    /**
     * 通过File和NewName修改文件信息记录名称
     * @param entity 查询条件
     * **/
    public int FileInfoRename(FileInfoQuery entity);


    /**
     * 移动文件到指定文件夹下
     * @param entity 查询条件
     * **/
    public int MoveFile(FileInfoEntity entity);


    /**
     * 移动文件到指定文件夹下
     * @param query 查询条件
     * **/
    public List<FileInfoEntity> GetFileInfoByUserIdAndFolderId(FileInfoQuery query);

    /**
     * 通过FileId查询文件信息
     * @param entity 查询条件
     * **/
    public List<FileInfoEntity> GetFileInfoByFileId(FileInfoQuery entity);

    /**
     * 通过userId查询文件信息
     * @param entity 查询条件
     * **/
    public List<FileInfoEntity> GetFileInfoByUserId(FileInfoQuery entity);

    /**
     * 通过MD5查询文件信息
     * @param entity 查询条件
     * **/
    public List<FileInfoEntity> GetFileInfoByMD5(FileInfoQuery entity);

    /**
     * 通过UseId和Type查询文件信息
     * @param entity 查询条件
     * **/
    public List<FileInfoEntity> GetFileInfoByType(FileInfoQuery entity);

}
