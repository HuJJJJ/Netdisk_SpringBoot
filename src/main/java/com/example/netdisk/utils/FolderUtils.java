package com.example.netdisk.utils;

import com.example.netdisk.DBEntity.FolderEntity;
import com.example.netdisk.pojo.FolderTree;
import lombok.var;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FolderUtils {


    /**
     * 构建文件夹树结构
     **/

    private FolderTree RootNode;
    private List<FolderEntity> folderEntitys;

    public List<FolderTree> BuiderFolderTree(List<FolderEntity> folders) {
        folderEntitys = folders;
        List<FolderTree> Trees = new ArrayList<>();
        //构建根节点并找出根节点的所有子节点
        RootNode = new FolderTree("Root", 0, 0);
        for (var j : GetChildNode(RootNode.id)) {
            RootNode.children.add(GetChildNode(j));
        }
        Trees.add(RootNode);

        return Trees;
    }

    /**
     * 获取某个节点的子节点
     **/

    private FolderTree GetChildNode(FolderTree TreeNode) {

        for (var folder : folderEntitys) {
            if (folder.ParentId == TreeNode.id) {
                var node = new FolderTree(folder.FolderName, folder.Id, folder.ParentId);
                TreeNode.children.add(node);
                GetChildNode(node);
            }
        }
        return TreeNode;
    }

    private List<FolderTree> GetChildNode(int id) {
        var ChildNodes = new ArrayList<FolderTree>();
        for (var folder : folderEntitys) {
            if (folder.ParentId == id) {
                var node = new FolderTree(folder.FolderName, folder.Id, folder.ParentId);
                ChildNodes.add(node);
            }
        }
        return ChildNodes;
    }

}
