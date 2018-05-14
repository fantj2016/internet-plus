package com.tyut.web.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Fant.J.
 * 2018/5/1 14:28
 */
public class HttpErrorHandler implements ErrorController {
    private final static String ERROR_PATH = "/error";

    @RequestMapping(value = ERROR_PATH, produces = "text/html")
    public String errorHtml(HttpServletRequest request) {
        return "404";
    }

    @RequestMapping(value = ERROR_PATH)
    @ResponseBody
    public Object error(HttpServletRequest request) {
        return "404";
    }


    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
