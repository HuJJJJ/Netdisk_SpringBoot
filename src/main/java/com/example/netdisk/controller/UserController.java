package com.example.netdisk.controller;


import com.example.netdisk.DBEntity.UserEntity;
import com.example.netdisk.pojo.query.UserQuery;
import com.example.netdisk.pojo.response.UserResponse;
import com.example.netdisk.service.UserService;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("User")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/GetUsers")
    public UserResponse GetUsers() {
        UserResponse response = new UserResponse();
        response.setSuccess(true);
        try {
            List<UserEntity> users = userService.GetUsers();
            response.setMsg("Success");
            response.setData(users);
            return response;
        } catch (Exception ex) {
            return response;
        }
    }

    @PostMapping("/GetUser")
    public UserResponse GetUser(int id) {

        UserResponse response = new UserResponse();
        response.setSuccess(true);
        UserQuery query = new UserQuery();
        query.setId(id);
        try {
            UserEntity user = userService.GetUser(query);
            response.setMsg("Success");
            response.setData(user);
            return response;
        } catch (Exception ex) {
            return response;
        }
    }






}
