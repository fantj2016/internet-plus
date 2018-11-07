package com.tyut.user.service;

import com.tyut.core.response.ServerResponse;

/**
 * Created by Fant.J.
 * 2018/5/26 23:21
 */
public interface GroupMemberService {
    /** 加入队伍 */
    ServerResponse join(Integer groupId, String userId, String groupName,Integer cptId);
    /** 根据groupid 查询队伍 */
    ServerResponse findGroupsByGroupId(Integer groupId);
    /** 同意用户加入队伍 */
    ServerResponse agreeSomeone(Integer groupId, String headId, String userId);
    /** 拒绝用户加入队伍 */
    ServerResponse rejectSomeone(Integer groupId, String headId, String userId);
    /** 移除某位队员 */
    ServerResponse removeSomeone(Integer groupId, String headId, String userId);
    /** 邀请某人加入 */
    ServerResponse inviteSomeone(Integer groupId, String userName,String userPhone);
    /** 查询是否有被邀请信息 */
    ServerResponse queryBeInvited(String userId);
    /** 退出队伍 */
    ServerResponse quitGroup(String userId,Integer groupId);
    /** 接受邀请加入队伍  */
    ServerResponse updateInvite(String userId, Integer memberId);
    /** 添加指导教师 */
    ServerResponse addTeacher(Integer groupId, String headId, String teacherName, String teacherPhone);
    /** 删除指导老师 */
    ServerResponse removeTeacher(Integer teacherId, String userId, Integer groupId);
    /** 忽略邀请 */
    ServerResponse ignoreInvite(String userId, Integer groupId);
    /** 拒绝邀请 */
    ServerResponse rejectInvite(String userId, Integer groupId);

    /** 解散队伍 */
    ServerResponse disbandGroup(String headerId,String groupKey);
}
