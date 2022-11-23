package com.example.netdisk.pojo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class FolderTree {
    public String lable;
    public int parentId;
    public int id;
   public List<FolderTree> children;

   public  FolderTree(String lable,int id,int parentId){
       this.lable =lable;
       this.id = id;
       this.parentId =parentId;
       this.children=new ArrayList<>();
   }
}
