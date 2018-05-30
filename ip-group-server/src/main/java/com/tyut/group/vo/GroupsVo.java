package com.tyut.group.vo;

import com.tyut.core.pojo.User;
import lombok.Data;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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

//    @ManyToOne
//    private User user;

//    private Integer userStatus;
}
