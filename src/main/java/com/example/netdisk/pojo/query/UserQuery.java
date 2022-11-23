package com.example.netdisk.pojo.query;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Mapper;

@Data
@Mapper
@AllArgsConstructor
@NoArgsConstructor
public class UserQuery {
    private  Integer Id;
    private  String UserName;
    private  String Password;
}
