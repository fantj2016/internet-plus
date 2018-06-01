package com.tyut.user.service;

import com.tyut.core.response.ServerResponse;

/**
 * Created by Fant.J.
 * 2018/5/26 23:21
 */
public interface GroupMemberService {
    /** 加入队伍 */
    ServerResponse join(Integer groupId, Integer userId, String groupName);
    /** 根据groupid 查询队伍 */
    ServerResponse findGroupsByGroupId(Integer groupId);
    /** 同意用户加入队伍 */
    ServerResponse agreeSomeone(Integer groupId, Integer headId, Integer userId);
}
