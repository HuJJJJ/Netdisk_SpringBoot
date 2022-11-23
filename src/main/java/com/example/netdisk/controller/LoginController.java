package com.example.netdisk.controller;

import com.example.netdisk.bo.JwtPayload;
import com.example.netdisk.DBEntity.UserEntity;
import com.example.netdisk.pojo.query.UserQuery;
import com.example.netdisk.pojo.response.LoginResponse;
import com.example.netdisk.service.UserService;
import com.example.netdisk.utils.JWTUtils;
import com.example.netdisk.vo.LoginParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("Login")
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping
    public LoginResponse login(@RequestBody LoginParam loginParam) {
        LoginResponse response = new LoginResponse();
        response.setSuccess(true);
        try {
            if (loginParam.UserName == null && loginParam.Password == null) throw new Exception("参数错误.");

            UserEntity user = userService.GetUser(new UserQuery
                    (0,
                            loginParam.UserName,
                            loginParam.Password));

            if (user == null) throw new Exception("账号或密码错误.");

            String token = "";
            //获取token
            token = JWTUtils.CreateToken(new JwtPayload(user.getId(), user.getUserName()));
            if (token == "" || token == null) throw new Exception("Token生成失败");
            response.setToken(token);
            response.setId(user.Id);
            response.setNickName(user.getNickName());
            response.setUserName(user.getUserName());
            return response;

        } catch (Exception ex) {
            response.setSuccess(false);
            response.setMsg(ex.getMessage());
            return response;
        }
    }

}
