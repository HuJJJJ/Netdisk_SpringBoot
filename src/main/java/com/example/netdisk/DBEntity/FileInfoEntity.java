package com.example.netdisk.DBEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileInfoEntity {
    public  int Id;
    public String FileName;
    public String NewFileName;
    public String FilePath;
    public int Type;
    public int IsDelete;
    public int UserId;
    public String MD5;

    public int FolderId;
}
