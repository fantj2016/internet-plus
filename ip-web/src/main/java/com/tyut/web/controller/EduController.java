package com.tyut.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.tyut.core.response.ServerResponse;
import com.tyut.user.service.UserEduService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.Cacheable;

/**
 * Created by Fant.J.
 * 2018/5/6 16:26
 */
@Api("学历Api")
@RestController
@RequestMapping("/edu")
public class EduController {
    @Reference(version = "2.0.0")
    private UserEduService userEduService;


    @ApiOperation("获取学历列表")
    @GetMapping("/list")
    @Cacheable(value = "eduList")
    public ServerResponse getEduList(){
        return userEduService.getAll();
    }
}
