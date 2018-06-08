package com.tyut.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.tyut.core.pojo.Group;
import com.tyut.core.response.ServerResponse;
import com.tyut.user.service.GroupMemberService;
import com.tyut.user.service.GroupService;
import com.tyut.web.dto.GroupCreateDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Created by Fant.J.
 * 2018/5/27 16:02
 */
@Api(description = "队伍")
@RestController
@RequestMapping("/group")
@Slf4j
public class GroupController {

    @Reference(version = "2.0.0")
    private GroupService groupService;
    @Reference(version = "2.0.0")
    private GroupMemberService memberService;

    @ApiOperation("创建队伍")
    @PostMapping("/create")
    public ServerResponse createGroup(GroupCreateDto groupCreateDto){
        Group group = new Group();
        group.setGroupName(groupCreateDto.getGroupName());
        group.setGroupPhone(groupCreateDto.getGroupPhone());
        group.setGroupHeaderId(groupCreateDto.getGroupHeaderId());
        group.setGroupType(groupCreateDto.getGroupType());
        return groupService.create(group);
    }

    @ApiOperation("通过key 获取队伍信息")
    @GetMapping("/{key}")
    public ServerResponse selectGroupByKey(@PathVariable String key){
        return groupService.selectByKey(key);
    }


    @ApiOperation("通过两个id加入队伍")
    @PostMapping("/join")
    public ServerResponse joinGroup(@RequestParam Integer groupId,@RequestParam String groupName,@RequestParam String userId){
        return memberService.join(groupId,userId,groupName);
    }

    @ApiOperation("通过groupId 查询队员列表")
    @GetMapping("/find/{groupId}")
    public ServerResponse findGroupById(@PathVariable Integer groupId){
        log.info("获取到groupid为{}",groupId);
        return memberService.findGroupsByGroupId(groupId);
    }

    @ApiOperation("队长同意用户加入")
    @PostMapping("/agree")
    public ServerResponse agreeSomeone(@ApiParam("队伍id")@RequestParam Integer groupId,
                                       @ApiParam("登录用户id") @RequestParam String headId,
                                       @ApiParam("需要同意的队员id")@RequestParam String userId){
        return memberService.agreeSomeone(groupId,headId,userId);
    }

    @ApiOperation("获取用户参加的所有队伍")
    @GetMapping("/{userId}/myGroup")
    public ServerResponse getMyGroup(@PathVariable String userId){
        return groupService.selectGroupList(userId);
    }
}
