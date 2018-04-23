package com.tyut.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.tyut.core.response.ServerResponse;
import com.tyut.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Fant.J.
 * 2018/4/23 19:20
 */

@RestController
public class UserController {

    @Reference(version = "2.0.0")
    private UserService userService;

    @RequestMapping("/user")
    @ResponseBody
    public ServerResponse get(){
        return userService.selectById(1);
    }
}
