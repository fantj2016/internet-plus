package com.tyut.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.tyut.core.response.ServerResponse;
import com.tyut.notice.service.GuestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;


/**
 * Created by Fant.J.
 * 2018/4/30 16:12
 */
@Api(description = "大赛评委API")
@RestController
@RequestMapping("/guest")
public class GuestController {

    @Reference(version = "2.0.0")
    private GuestService guestService;


    @Cacheable(value = "guests-0", key = "'findGuest-0' + #id")
    @ApiOperation(value = "根据比赛id查询所有的专家")
    @GetMapping("/get0/{id}")
    @ResponseBody
    public ServerResponse getAll0(@ApiParam("比赛的id") @PathVariable("id")Integer id){
        return guestService.selectAll0(id);
    }

    @Cacheable(value = "guests-1", key = "'findGuest-1' + #id")
    @ApiOperation(value = "根据比赛id查询所有的评委")
    @GetMapping("/get1/{id}")
    @ResponseBody
    public ServerResponse getAll1(@ApiParam("比赛的id")@PathVariable("id")Integer id){
        return guestService.selectAll1(id);
    }
}
