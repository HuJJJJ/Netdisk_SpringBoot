package com.example.netdisk.pojo.query;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Mapper;

@Data
@Mapper
@AllArgsConstructor
@NoArgsConstructor
public class FileInfoQuery {
    public int UserId;
    public int Type;
    public String  MD5;
    public int FileId;
    public int IsDelete = 0;
    public String NewName;

    public int FolderId;

}
