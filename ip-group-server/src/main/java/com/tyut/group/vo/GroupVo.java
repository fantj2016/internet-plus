package com.tyut.group.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

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
}
