package com.example.netdisk.service.impl;

import com.example.netdisk.dao.UserDao;
import com.example.netdisk.DBEntity.UserEntity;
import com.example.netdisk.pojo.query.UserQuery;
import com.example.netdisk.service.UserService;
import com.example.netdisk.vo.RegisterParam;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;


    /**
     * 获取user信息
     * @param query 查询条件
     * **/
    @Override
    public UserEntity GetUser(UserQuery query) {
        return userDao.GetUser(query);
    }


    /**
     * 获取所有user信息
     * **/
    @Override
    public List<UserEntity> GetUsers() {
        return userDao.GetUsers();
    }


    /**
     * 新增user对象
     * @param  user user对象
     * **/
    @Override
    public int AddUser(UserEntity user) {
        return userDao.AddUser(user);
    }


}
