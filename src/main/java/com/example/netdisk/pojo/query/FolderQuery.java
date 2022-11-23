package com.example.netdisk.pojo.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Mapper;

@Data
@Mapper
@AllArgsConstructor
@NoArgsConstructor
public class FolderQuery {
    public int Id;
    public int UserId;
    public int ParentId;
    public String FolderName;
}
