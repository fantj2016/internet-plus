package com.tyut.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mysql.fabric.Server;
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
import org.springframework.util.StringUtils;
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
        group.setGroupAddress(groupCreateDto.getGroupAddress());
        return groupService.create(group);
    }

    @ApiOperation("通过key 获取队伍信息")
    @GetMapping("/{key}")
    public ServerResponse selectGroupByKey(@PathVariable String key){
        return groupService.selectByKey(key);
    }


    @ApiOperation("通过两个id加入队伍")
    @PostMapping("/join")
    public ServerResponse joinGroup(@RequestParam Integer groupId,
                                    @RequestParam String groupName,
                                    @RequestParam String userId,
                                    @RequestParam Integer cptId){
        return memberService.join(groupId,userId,groupName,cptId);
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
                                       @ApiParam("登录用户(假设该用户是队长)id") @RequestParam String headId,
                                       @ApiParam("需要同意的队员id")@RequestParam String userId){
        return memberService.agreeSomeone(groupId,headId,userId);
    }
    @ApiOperation("队长拒绝用户加入")
    @PostMapping("/reject")
    public ServerResponse rejectSomeone(@ApiParam("队伍id")@RequestParam Integer groupId,
                                       @ApiParam("登录用户(假设该用户是队长)id") @RequestParam String headId,
                                       @ApiParam("需要拒绝的队员id")@RequestParam String userId){
        return memberService.rejectSomeone(groupId,headId,userId);
    }
    @ApiOperation("队长移除队员")
    @PostMapping("/remove")
    public ServerResponse removeSomeone(@ApiParam("队伍id")@RequestParam Integer groupId,
                                        @ApiParam("登录用户(假设该用户是队长)id") @RequestParam String headId,
                                        @ApiParam("需要移除的队员id")@RequestParam String userId){
        return memberService.removeSomeone(groupId,headId,userId);
    }


    @ApiOperation("获取用户参加的所有队伍")
    @GetMapping("/{userId}/myGroup")
    public ServerResponse getMyGroup(@PathVariable String userId){
        return groupService.selectGroupList(userId);
    }

    @ApiOperation("查询队名是否可用")
    @GetMapping("/isExist/{groupName}")
    public ServerResponse isGroupNameExist(@PathVariable String groupName){
        return groupService.isGroupNameExist(groupName);
    }

    @ApiOperation("查询团队信息")
    @GetMapping("/queryInfo/{groupKey}")
    public ServerResponse queryGroupInfoByKey(@PathVariable String groupKey){
        return groupService.queryGroupInfo(groupKey);
    }

    @ApiOperation("修改队伍信息")
    @PostMapping("/updateInfo")
    public ServerResponse updateGroupInfo(Group group){
        if (StringUtils.isEmpty(group.getGroupKey())){
            return ServerResponse.createByErrorMessage("group key 不存在!");
        }
        return groupService.updateGroupInfo(group);
    }

    @ApiOperation("邀请某人加入队伍")
    @PostMapping("/inviteSomeone")
    public ServerResponse inviteSomeone(@RequestParam Integer groupId,
                                        @RequestParam String userName,
                                        @RequestParam String userPhone){
       return memberService.inviteSomeone(groupId,userName,userPhone);
    }

    @ApiOperation("查看是否有邀请参加队伍的信息")
    @GetMapping("/queryBeInvited/{userId}")
    public ServerResponse queryBeInvited(@PathVariable String userId){
        return memberService.queryBeInvited(userId);
    }

    @ApiOperation("接受邀请加入队伍")
    @PostMapping("/agreeInvite")
    public ServerResponse agreeInvite(@RequestParam String userId,
                                      @ApiParam("队员表中的主键id") @RequestParam Integer groupId){
        return memberService.updateInvite(userId,groupId);
    }

    @ApiOperation("退出队伍")
    @PostMapping("/quitGroup")
    public ServerResponse inviteSomeone(@RequestParam Integer groupId,
                                        @RequestParam String userId){
        return memberService.quitGroup(userId,groupId);
    }

    @ApiOperation("忽略邀请 ")
    @PostMapping("/ignoreInvite")
    public ServerResponse ignoreInvite(@RequestParam Integer groupId,
                                        @RequestParam String userId){
        return memberService.ignoreInvite(userId,groupId);
    }
    @ApiOperation("拒绝邀请 ")
    @PostMapping("/rejectInvite")
    public ServerResponse rejectInvite(@RequestParam Integer groupId,
                                        @RequestParam String userId){
        return memberService.rejectInvite(userId,groupId);
    }

    @ApiOperation("添加指导老师,只有队长能添加")
    @PostMapping("/addTeacher")
    public ServerResponse addTeacher(@RequestParam Integer groupId,
                                     @RequestParam String userId,
                                     @RequestParam String teacherName,
                                     @RequestParam String teacherPhone){

       return memberService.addTeacher(groupId,userId,teacherName,teacherPhone);
    }

    @ApiOperation("删除指导老师")
    @PostMapping("/delTeacher")
    public ServerResponse delTeacher(@RequestParam Integer teacherId,
                                     @RequestParam String userId,
                                     @RequestParam Integer groupId){
        return memberService.removeTeacher(teacherId,userId,groupId);
    }
}
