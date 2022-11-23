package com.example.netdisk.dao;


import com.example.netdisk.DBEntity.FileInfoEntity;
import com.example.netdisk.DBEntity.SharingEntity;
import com.example.netdisk.pojo.query.SharingQuery;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SharingDao {
    /**
     * 新增分享码信息
     * @param entity 查询信息
     * **/
    public int AddSharingInfo(SharingEntity entity);

    /**
     * 删除分享码信息
     * @param entity 查询信息
     * **/
    public int DelSharingInfo(SharingEntity entity);

    /**
     * 根据分享码查询信息
     * @param entity 查询信息
     * **/
    public List<SharingEntity> QuerySharingCodeByCode(SharingQuery entity);
}
