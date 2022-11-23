package com.example.netdisk.service;

import com.example.netdisk.DBEntity.SharingEntity;
import com.example.netdisk.pojo.query.SharingQuery;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SharingService {

    /**
     * 新增分享码信息
     *
     * @param entity 查询信息
     **/
    public int AddSharingInfo(SharingEntity entity);

    /**
     * 删除分享码信息
     *
     * @param entity 查询信息
     **/
    public int DelSharingInfo(SharingEntity entity);

    /**
     * 根据分享码查询信息
     *
     * @param entity 查询信息
     **/
    public List<SharingEntity> QuerySharingCodeByCode(SharingQuery entity);
}
