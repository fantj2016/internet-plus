package com.tyut.web.security.authention;

import com.alibaba.fastjson.JSON;
import com.tyut.core.response.ServerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义登录失败处理器
 * Created by Fant.J.
 */
@Component
public class MyAuthenticationFailHandler extends SimpleUrlAuthenticationFailureHandler {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());


    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        logger.info("登录失败");
            //设置状态码
            response.setStatus(500);
            response.setContentType("application/json;charset=UTF-8");
            //将 登录失败 信息打包成json格式返回
            response.getWriter().write(JSON.toJSONString(ServerResponse.createByErrorMessage(exception.getMessage())));
        ;
    }
}
