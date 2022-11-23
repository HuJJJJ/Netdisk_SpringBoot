package com.example.netdisk.vo;

import lombok.Data;

@Data
public class SacnFileMD5Param {
    public int UserId;
    public String MD5;
    public String FileName;
    public int Type;
}
