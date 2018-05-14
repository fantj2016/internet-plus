package com.tyut.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.tyut.core.response.ServerResponse;
import com.tyut.core.vo.UserVo;
import com.tyut.user.service.UserEduService;
import com.tyut.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.annotations.Cacheable;

/**
 * Created by Fant.J.
 * 2018/4/23 19:20
 */

@Api(value = "/UserController",description = "用户登录接口")
@RestController
//@RequestMapping("/user")
public class UserController {

    @Reference(version = "2.0.0")
    private UserService userService;

    @GetMapping("/user/{id}")
    @ResponseBody
    public ServerResponse get(@PathVariable Integer id){
        return userService.selectById(id);
    }

    @ApiOperation("获取token")
    @PostMapping("/authentication/form")
    public ServerResponse getToken(@ApiParam("账号") @RequestParam String username,
                                   @ApiParam("密码") @RequestParam String password
                                   ){

        return null;
    }

    @ApiOperation("刷新token")
    @PostMapping("/oauth/token")
    public ServerResponse refreshToken(@ApiParam("") @RequestParam String grant_type,
                                        @ApiParam("") @RequestParam String refresh_token){
        return null;
    }

    @ApiIgnore
    @GetMapping("/authentication/require")
    public ServerResponse getAuthenticationRequire(){
        return ServerResponse.createByErrorCodeMessage(403,"请先登录!");
    }

}
