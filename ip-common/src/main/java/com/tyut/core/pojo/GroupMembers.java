package com.tyut.core.pojo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

    private Integer userId;

    private Integer userIdentity;

    private Integer userStatus;

    private String groupName;

}
