package com.example.netdisk.DBEntity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    public int Id;
    public  String NickName;
    public  String UserName;
    public  String Password;
}
