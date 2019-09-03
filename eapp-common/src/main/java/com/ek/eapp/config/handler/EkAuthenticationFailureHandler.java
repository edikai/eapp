package com.ek.eapp.config.handler;

import com.alibaba.fastjson.JSON;
import com.ek.eapp.util.R;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName: EkAuthenticationFailureHandler
 * @Description: TODO
 * @Author: qin_hqing
 * @Date: 2019-08-06
 * @Version: V2.0
 **/

@Component
public class EkAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Credentials","true");
        // 设定类容为json的格式
        httpServletResponse.setContentType("application/json;charset=UTF-8");

        httpServletResponse.getWriter().write(JSON.toJSONString(R.error("登录失败")));
    }

}
