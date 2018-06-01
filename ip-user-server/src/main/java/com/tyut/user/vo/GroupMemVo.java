package com.tyut.user.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 队伍成员信息
 * Created by Fant.J.
 * 2018/5/29 20:51
 */
@Data
public class GroupMemVo implements Serializable {
//    g.group_name,u.user_name,u.user_phone,g.user_identity,g.user_status

    private  String groupName;

    private String userName;

    private String userPhone;
    /**
     * 队员身份    0 队员 1 队长
     */
    private Integer userIdentity;
    /**
     * 是否通过申请（申请入队）
     */
    private Integer userStatus;
}
