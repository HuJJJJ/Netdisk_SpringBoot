package com.example.netdisk.pojo.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MergeFileResponse extends  BaseResponse{
    private int Code;

    public  MergeFileResponse(){}
}
