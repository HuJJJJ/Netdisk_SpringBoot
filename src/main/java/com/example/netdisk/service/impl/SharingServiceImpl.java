package com.example.netdisk.service.impl;

import com.example.netdisk.DBEntity.SharingEntity;
import com.example.netdisk.dao.FileInfoDao;
import com.example.netdisk.dao.SharingDao;
import com.example.netdisk.pojo.query.SharingQuery;
import com.example.netdisk.service.SharingService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SharingServiceImpl implements SharingService {

    @Resource
    private SharingDao sharingDao;

    /**
     * 新增分享码信息
     *
     * @param entity 查询信息
     **/
    public int AddSharingInfo(SharingEntity entity){return sharingDao.AddSharingInfo(entity);}

    /**
     * 删除分享码信息
     *
     * @param entity 查询信息
     **/
    public int DelSharingInfo(SharingEntity entity){return sharingDao.DelSharingInfo(entity);}

    /**
     * 根据分享码查询信息
     *
     * @param entity 查询信息
     **/
    public List<SharingEntity> QuerySharingCodeByCode(SharingQuery entity){return sharingDao.QuerySharingCodeByCode(entity);}
}
