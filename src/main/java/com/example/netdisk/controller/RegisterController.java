package com.example.netdisk.controller;

import com.example.netdisk.DBEntity.UserEntity;
import com.example.netdisk.pojo.response.RegisterResponse;
import com.example.netdisk.service.UserService;
import com.example.netdisk.utils.ReflectUtil;
import com.example.netdisk.vo.RegisterParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Register")
public class RegisterController {

    @Autowired
    private UserService userService;


    @PostMapping
    public RegisterResponse index(@RequestBody RegisterParam registerParam) {
        RegisterResponse response = new RegisterResponse();
        try {

            UserEntity user = ReflectUtil.convertObject(registerParam, UserEntity.class);
            int result = userService.AddUser(user);
            response.setSuccess(true);
            response.setData(result);
            return response;
        } catch (DuplicateKeyException ex) {
            response.setSuccess(false);
            response.setMsg("该用户名已被注册");
            return response;
        } catch (Exception ex) {
            response.setSuccess(false);
            response.setMsg(ex.getMessage());
            return response;
        }
    }
}
