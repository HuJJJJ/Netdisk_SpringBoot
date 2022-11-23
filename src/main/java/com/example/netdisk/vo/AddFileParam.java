package com.example.netdisk.vo;


import lombok.Data;

@Data
public class AddFileParam {
    public String FileName;
    public String NewFileName;
    public String FilePath;
    public int Type;
    public int IsDelete;
    public int UserId;
    public String MD5;
}
