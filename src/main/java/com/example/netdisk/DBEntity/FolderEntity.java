package com.example.netdisk.DBEntity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FolderEntity {
    public int Id;
    public String FolderName;
    public int ParentId;
    public int UserId;
}
