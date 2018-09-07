package com.tyut.user.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 根据key 查到某个队伍
 * Created by Fant.J.
 * 2018/5/26 23:14
 */
@Data
public class GroupVo implements Serializable {

    /**
     * 队伍 id
     */
    private Integer groupId;
    /**
     * 队伍名字
     */
    private String groupName;
    /**
     * 竞赛名字
     */
    private String cptName;
    /**
     * 加队口令
     */
    private String groupKey;
    /**
     * 队伍地址
     */
    private String groupAddress;
    /**
     * 联系方式
     */
    private String groupPhone;
}
