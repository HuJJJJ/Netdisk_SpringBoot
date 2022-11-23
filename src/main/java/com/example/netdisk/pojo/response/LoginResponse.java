package com.example.netdisk.pojo.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse extends  BaseResponse{
    private String token;
    private  String nickName;
    private  String userName;
    private  int id;

    public  LoginResponse(){

    }
}
