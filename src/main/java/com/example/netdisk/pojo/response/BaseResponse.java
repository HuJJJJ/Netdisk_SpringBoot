package com.example.netdisk.pojo.response;


import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.transform.Result;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse {
    private boolean Success;
    private String Msg;
    private Object Data;


    public String ToJsonString() {

        return JSON.toJSONString(this);
    }


}
