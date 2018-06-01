package com.tyut.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.tyut.core.constants.ConsParams;
import com.tyut.core.pojo.User;
import com.tyut.core.response.ServerResponse;
import com.tyut.core.vo.UserVo;
import com.tyut.user.service.UserEduService;
import com.tyut.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.annotations.Cacheable;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * Created by Fant.J.
 * 2018/4/23 19:20
 */

@Api(value = "/UserController",description = "用户登录接口")
@RestController
@Slf4j
//@RequestMapping("/user")
public class UserController {

    @Reference(version = "2.0.0")
    private UserService userService;

    @GetMapping("/me")
    public ServerResponse getCurrentUser(Authentication user) throws UnsupportedEncodingException {

        String username = user.getName();
        log.info("从Authentication里获取到的username为{}",username);
        return userService.selectMe(username);
    }

    @ApiOperation("修改用户信息")
    @PostMapping("/update")
    public ServerResponse updateUser(User user){
        return userService.update(user);
    }



}
