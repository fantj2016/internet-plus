package com.tyut.core.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Fant.J.
 * 2018/5/26 23:27
 */
@Data
@Entity
@Table(name = "ip_group_members")
public class GroupMembers implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;

    private Integer groupId;

    private Integer groupType;

    private String userId;

    private Integer userIdentity;

    private Integer userStatus;

    private String groupName;

    private Date createTime;

}

