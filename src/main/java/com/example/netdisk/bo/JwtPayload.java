package com.example.netdisk.bo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtPayload {
    //用户id
    private int Id;

    //用户名
    private String UserName;
}
