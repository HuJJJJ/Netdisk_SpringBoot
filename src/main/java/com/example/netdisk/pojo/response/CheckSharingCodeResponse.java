package com.example.netdisk.pojo.response;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CheckSharingCodeResponse extends BaseResponse{

    public CheckSharingCodeResponse(){}
    public String UserName;
}
