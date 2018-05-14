package com.tyut.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.tyut.school.service.SchoolService;
import com.tyut.core.response.ServerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Fant.J.
 * 2018/4/24 19:08
 */
@Api(description = "学校接口")
@RestController
@RequestMapping("/sch")
public class SchoolController {

    @Reference(version = "2.0.1")
    private SchoolService schoolService;

    @ApiOperation("获取所有学校列表")
    @Cacheable(value = "school",key = "'getAll'")
    @GetMapping("/all")
    public ServerResponse getAll(){
        return schoolService.selectAll();
    }


    @ApiOperation("根据关键词查询学校")
    @Cacheable(value = {"school"},key = "'getByLike'+#like")
    @GetMapping("/like/{like}")
    public ServerResponse getByLike(@ApiParam("关键词") @PathVariable String like){
        return schoolService.selectLike(like);
    }


}
