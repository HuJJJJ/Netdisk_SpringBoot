package com.example.netdisk.service.impl;

import com.example.netdisk.DBEntity.FolderEntity;
import com.example.netdisk.dao.FileInfoDao;
import com.example.netdisk.dao.FolderDao;
import com.example.netdisk.pojo.query.FolderQuery;
import com.example.netdisk.service.FolderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class FolderServiceImpl implements FolderService {

    @Resource
    private FolderDao folderDao;

    /**
     * 新增文件夹
     *
     * @param entity 查询信息
     **/
    public int AddFolder(FolderEntity entity) {
        return folderDao.AddFolder(entity);
    }


    /**
     * 获取Folder信息根据userid和folderId
     * @param query 查询条件
     * **/
    public List<FolderEntity> GetFolderByParentIdAndUserId(FolderQuery query)
    {return  folderDao.GetFolderByParentIdAndUserId(query);}

    /**
     * 修改文件夹名称
     * **/
    public int FolderRename(FolderEntity entity){return folderDao.FolderRename(entity);}

    /**
     * 删除文件夹
     *
     * @param entity 查询信息
     **/
    public int DelFolder(FolderEntity entity) {
        return folderDao.DelFolder(entity);
    }

    /**
     * 获取Folder信息根据id
     *
     * @param query 查询条件
     **/
    public FolderEntity GetFolderByFolderId(FolderQuery query) {
        return folderDao.GetFolderByFolderId(query);
    }

    /**
     * 获取Folder信息根据id
     *
     * @param query 查询条件
     **/
    public List<FolderEntity> GetFolderByUserId(FolderQuery query) {
        return folderDao.GetFolderByUserId(query);
    }

    /**
     * 获取Folder信息根据id
     *
     * @param query 查询条件
     **/
    public List<FolderEntity> GetFolderByParentId(FolderQuery query) {
        return folderDao.GetFolderByParentId(query);
    }

    /**
     * 获取所有Folder信息
     **/
    public List<FolderEntity> GetFolders() {
        return folderDao.GetFolders();
    }
}
