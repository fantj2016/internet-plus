package com.tyut.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.tyut.core.pojo.News;
import com.tyut.core.response.ServerResponse;
import com.tyut.user.service.NewsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Fant.J.
 * 2018/6/2 0:29
 */
@Api(description = "个人消息接口")
@RestController
@RequestMapping("/news")
public class NewsController {
    @Reference(version = "2.0.0")
    private NewsService newsService;


    @ApiOperation("获取消息列表（分页）")
    @GetMapping("/{userId}/{pageNum}/{pageSize}/list")
    public ServerResponse selectNewsList(
            @RequestParam(value = "offset",defaultValue = "0",required = false) Integer pageNum,
            @PathVariable Integer pageSize,
            @PathVariable Integer userId){
        return newsService.selectNewsList(userId,pageNum,pageSize);
    }

    @ApiOperation("消息标记为已读")
    @GetMapping("/hasRead")
    public ServerResponse hasRead(@PathVariable Integer newsId){
        return newsService.hasRead(newsId);
    }

}
