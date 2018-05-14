package com.tyut.web.exception;

import com.tyut.core.response.ServerResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Fant.J.
 * 2018/5/1 14:18
 */
//@RestController
//@ControllerAdvice
public class GlobalExceptionHandler {
    private Logger logger = LoggerFactory.getLogger("GlobalExceptionHandler");

    @ExceptionHandler(value = BaseException.class)
    @ResponseBody
    public Object baseErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        logger.error("---BaseException Handler---Host {} invokes url {} ERROR: {}", req.getRemoteHost(), req.getRequestURL(), e.getMessage());
        return e.getMessage();
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ServerResponse defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        if (e instanceof org.springframework.web.servlet.NoHandlerFoundException) {
            logger.info("路径不存在");
            return ServerResponse.createByErrorCodeMessage(404,"路径不存在");
        } else {
            logger.info("服务器响应错误");
            return ServerResponse.createByErrorCodeMessage(500,"服务器错误");
        }
    }
}
