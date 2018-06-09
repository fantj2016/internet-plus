package com.tyut.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.tyut.core.response.ServerResponse;
import com.tyut.notice.service.NoticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Fant.J.
 * 2018/4/30 13:56
 */
@Api(description = "公告API")
@RestController
@RequestMapping("/notice")
public class NoticeController {

    @Reference(version = "2.0.0")
    NoticeService noticeService;


    @Cacheable(value = "notice", key = "'getOne' + #id")
    @ApiOperation(value = "根据公告id获取详情")
    @GetMapping("/{id}")
    @ResponseBody
    public ServerResponse getOne(@PathVariable Integer id){
        return noticeService.selectById(id);
    }


    @Cacheable(value = "notice", key = "'getAll' + #page+#size")
    @ApiOperation(value = "根据page(起始页)和size(每页条数)获取公告列表")
    @GetMapping("/list/{page}/{size}")
    @ResponseBody
    public ServerResponse getAll(@ApiParam("起始页") @PathVariable("page")Integer page,
                                 @ApiParam("每页条数")@PathVariable("size")Integer size){
        if (page == null || size == null){
            return ServerResponse.createByErrorMessage("参数page和size不规范");
        }
        return noticeService.selectAll(page,size);
    }
}
