package com.example.netdisk.pojo.response;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShardUploadResponse extends BaseResponse{
   public int Code;

   public ShardUploadResponse(){}
}
