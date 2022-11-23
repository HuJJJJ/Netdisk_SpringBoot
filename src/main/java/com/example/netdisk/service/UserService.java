package com.example.netdisk.service;

import com.example.netdisk.DBEntity.UserEntity;
import com.example.netdisk.pojo.query.UserQuery;
import com.example.netdisk.vo.RegisterParam;

import java.util.List;



public interface UserService {

    /**
     * 获取user信息
     * @param query 查询条件
     * **/
    public UserEntity GetUser(UserQuery query);

    /**
     * 获取所有user信息
     * **/
    public List<UserEntity> GetUsers();

    /**
     * 新增user对象
     * @param  user user对象
     * **/
    public int AddUser(UserEntity user);

}
