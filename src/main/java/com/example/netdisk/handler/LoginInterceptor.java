package com.example.netdisk.handler;

import com.example.netdisk.pojo.response.BaseResponse;
import com.example.netdisk.pojo.response.CheckTokenResponse;
import com.example.netdisk.utils.JWTUtils;
import com.sun.xml.internal.ws.util.StringUtils;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        /**
         * 判断token是否为空，为空则未登录
         * token不为空，则使用JWTUtils验证token是否正确
         * */
        String token = request.getHeader("Authorization");

        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        log.info("=================request start===========================");
        String requestURI = request.getRequestURI();
        log.info("request uri:{}", requestURI);
        log.info("request method:{}", request.getMethod());
        log.info("token:{}", token);
        log.info("=================request end===========================");


        CheckTokenResponse result = new CheckTokenResponse();
        result.setMsg("校验失败");
        result.setSuccess(false);


        if (token == null || token.isEmpty()) {
            response.setContentType("application/json;charset=utf-8");
            response.sendError(401);
            response.getWriter().print(result.ToJsonString());
            return false;
        }

        //校验token
        Claims x = JWTUtils.verifyJwt(token);
        if (x == null || x.isEmpty()) {
            response.setContentType("application/json;charset=utf-8");
            response.sendError(401);
            response.getWriter().print(result.ToJsonString());
            return false;
        }

        return true;
    }

}
