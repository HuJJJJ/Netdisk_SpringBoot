package com.example.netdisk.pojo.response;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddSharingResponse extends BaseResponse{

    private String SharingCode;

    public AddSharingResponse(){}
}
