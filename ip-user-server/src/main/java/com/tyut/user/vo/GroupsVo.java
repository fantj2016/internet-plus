package com.tyut.user.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Fant.J.
 * 2018/5/27 17:45
 */
@Data
public class GroupsVo implements Serializable {

    private Integer id;

    private Integer groupId;

    private String groupName;

    private String userName;

    private String userPhone;

    private Integer userIdentity;

    private String cptName;

    private String groupKey;

    private Integer cptId;

    private String cptType;

    private String cptIcon;

    private String cptImg;

    private Integer cptStatus;

//    @ManyToOne
//    private User user;

//    private Integer userStatus;
}
