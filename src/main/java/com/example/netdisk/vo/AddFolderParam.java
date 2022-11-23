package com.example.netdisk.vo;


import lombok.Data;

@Data
public class AddFolderParam {
    public String FolderName;
    public int UserId;
    public int ParentId;
}
