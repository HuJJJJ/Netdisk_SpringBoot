package com.example.netdisk.controller;


import com.example.netdisk.Exception.SharingCodeTimeOutExcetion;
import com.example.netdisk.DBEntity.SharingEntity;
import com.example.netdisk.pojo.query.FileInfoQuery;
import com.example.netdisk.pojo.query.SharingQuery;
import com.example.netdisk.pojo.response.AddSharingResponse;
import com.example.netdisk.pojo.response.CheckSharingCodeResponse;
import com.example.netdisk.service.FileInfoService;
import com.example.netdisk.service.SharingService;
import com.example.netdisk.vo.AddSharingParam;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("Sharing")
public class SharingController {

    @Autowired
    private SharingService sharingService;

    @Autowired
    private FileInfoService fileInfoService;

    @PostMapping("/Add")
    public AddSharingResponse Add(@RequestBody AddSharingParam request) throws ParseException {
        AddSharingResponse response = new AddSharingResponse();
        response.setSuccess(true);
        try {
            var uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            var entity = new SharingEntity();
            entity.FileId = request.FileId;
            entity.SharingCode = uuid;
            entity.GivenPeriod = format.parse(request.GivenPeriod);
            entity.UserName = request.UserName;
            var result = sharingService.AddSharingInfo(entity);
            response.setSharingCode(uuid);
            response.setData(result);
            return response;
        } catch (Exception ex) {
            response.setSuccess(false);
            response.setMsg(ex.getMessage());
            return response;
        }
    }


    @PostMapping("Check")
    public CheckSharingCodeResponse Check(@RequestBody String code) {
        var response = new CheckSharingCodeResponse();
        response.setSuccess(true);
        try {
            //处理分享码
            var index1 = code.indexOf('"');
            var index2 = code.indexOf('"', index1 + 1);
            var sharingCode = code.substring(index1 + 1, index2);

            //查询
            var querySharingEntity = new SharingQuery();
            querySharingEntity.SharingCode = sharingCode;
            var sharing = sharingService.QuerySharingCodeByCode(querySharingEntity).get(0);

            //查看分享码是否过期
            Date date = new Date();
            if (date.compareTo(sharing.GivenPeriod) > 0)
                throw new SharingCodeTimeOutExcetion("分享码已过期");

            //查询文件信息
            var queryFileEntity = new FileInfoQuery();
            queryFileEntity.setFileId(sharing.FileId);
            var fileInfo = fileInfoService.GetFileInfoByFileId(queryFileEntity).get(0);
            response.setData(fileInfo);
            response.setUserName(sharing.UserName);
            return response;
        } catch (SharingCodeTimeOutExcetion ex) {
            response.setSuccess(false);
            response.setMsg(ex.getMessage());
            response.setData(-1);
            return response;
        } catch (Exception ex) {
            response.setSuccess(false);
            response.setMsg(ex.getMessage());
            response.setData(0);
            return response;
        }
    }
}
