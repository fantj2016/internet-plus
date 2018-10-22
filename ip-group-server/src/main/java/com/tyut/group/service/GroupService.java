package com.tyut.group.service;

import com.tyut.core.pojo.Group;
import com.tyut.core.response.ServerResponse;

/**
 * Created by Fant.J.
 * 2018/5/26 23:11
 */
public interface GroupService {

    /** 创建队伍 */
    ServerResponse create(Group group);

    /** 根据key 查询队伍 */
    ServerResponse selectByKey(String key);

    /** 通过用户id查询  用户所在的 队伍列表 */
    ServerResponse selectGroupList(Integer userId);

    /** 查询团队信息 */
    ServerResponse queryGroupInfo(String groupKey);

    /** 更改团队信息 */
    ServerResponse updateGroupInfo(Group group);

}
