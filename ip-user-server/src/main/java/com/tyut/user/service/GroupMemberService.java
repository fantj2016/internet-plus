package com.tyut.user.service;

import com.mysql.fabric.Server;
import com.tyut.core.pojo.GroupMembers;
import com.tyut.core.response.ServerResponse;
import org.apache.ibatis.annotations.Param;

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
    /** 查询某位用户是否存在在某个队伍 */
    boolean isExsitSomeone(String userId,Integer groupId);
}
