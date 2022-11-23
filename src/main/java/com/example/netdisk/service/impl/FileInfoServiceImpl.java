package com.example.netdisk.service.impl;


import com.example.netdisk.DBEntity.FileInfoEntity;
import com.example.netdisk.dao.FileInfoDao;
import com.example.netdisk.pojo.query.FileInfoQuery;
import com.example.netdisk.service.FileInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FileInfoServiceImpl implements FileInfoService {

    @Resource
    private FileInfoDao fileInfoDao;

    /**
     * 新增文件信息
     * @param entity 文件信息
     * **/
    @Override
    public int AddFileInfo(FileInfoEntity entity){
        return fileInfoDao.AddFileInfo(entity);
    }

    /**
     * 移动文件到指定文件夹下
     * @param query 查询条件
     * **/
    public List<FileInfoEntity> GetFileInfoByUserIdAndFolderId(FileInfoQuery query)
    {return fileInfoDao.GetFileInfoByUserIdAndFolderId(query);}

    /**
     * 移动文件到指定文件夹下
     * @param entity 查询条件
     * **/
    public int MoveFile(FileInfoEntity entity){return fileInfoDao.MoveFile(entity);}


    /**
     * 通过FileId删除文件信息
     * @param entity 查询条件
     * **/
    public int DeleteFile(FileInfoQuery entity){return fileInfoDao.DeleteFile(entity);}

    /**
     * 通过FileId查询文件信息
     * @param entity 查询条件
     * **/
    public List<FileInfoEntity> GetFileInfoByFileId(FileInfoQuery entity){ return fileInfoDao.GetFileInfoByFileId(entity);}


    /**
     * 通过userId查询文件信息
     * @param entity 查询条件
     * **/
    @Override
    public List<FileInfoEntity> GetFileInfoByUserId(FileInfoQuery entity){
        return fileInfoDao.GetFileInfoByUserId(entity);
    }

    /**
     * 通过File和NewName修改文件信息记录名称
     * @param entity 查询条件
     * **/
    @Override
    public int FileInfoRename(FileInfoQuery entity){return fileInfoDao.FileInfoRename(entity);}

    /**
     * 通过MD5查询文件信息
     * @param entity 查询条件
     * **/
    @Override
    public List<FileInfoEntity> GetFileInfoByMD5(FileInfoQuery entity){
        return fileInfoDao.GetFileInfoByMD5(entity);
    }

    /**
     * 通过UseId和Type查询文件信息
     * @param entity 查询条件
     * **/
    @Override
    public List<FileInfoEntity> GetFileInfoByType(FileInfoQuery entity){
        return fileInfoDao.GetFileInfoByType(entity);
    }
}
