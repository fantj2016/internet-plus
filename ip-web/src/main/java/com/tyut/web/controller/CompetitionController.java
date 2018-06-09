package com.tyut.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.tyut.core.response.ServerResponse;
import com.tyut.notice.service.CompetitionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Fant.J.
 * 2018/5/6 16:08
 */
@Api(description = "赛题APi")
@RestController
@RequestMapping("/cpt")
public class CompetitionController {
    @Reference(version = "2.0.0")
    private CompetitionService competitionService;


    @ApiOperation("获取赛题详情")
    @GetMapping("/{id}")
    @Cacheable(value = "cpt",key = "'getOne' + #id")
    public ServerResponse getOne(@PathVariable Integer id){
        return competitionService.selectById(id);
    }


    @ApiOperation("获取赛题列表")
    @GetMapping("/list")
    public ServerResponse getTitleList(){
        return competitionService.selectTitleList();
    }
}
