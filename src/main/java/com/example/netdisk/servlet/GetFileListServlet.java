package com.example.netdisk.servlet;

import lombok.var;
import org.springframework.web.bind.annotation.CrossOrigin;
import sun.misc.IOUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


//根据用户ID查所有文件列表
@WebServlet(name = "GetFileList", urlPatterns = "/GetFileList")
@CrossOrigin(origins = "*")
public class GetFileListServlet extends HttpServlet {

    @Override
    protected  void doPost(HttpServletRequest request, HttpServletResponse response) {
           String contentType = request.getContentType();
           contentType = contentType.split(":")[0];
    }



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        var uid = request.getParameterNames();
        int a =0;
    }

}
