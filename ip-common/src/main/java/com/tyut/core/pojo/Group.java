package com.tyut.core.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


@Data
@Entity
@Table(name = "ip_group")
public class Group  implements Serializable {

    @Id
    @GeneratedValue
    private Integer groupId;

    private String groupName;

    private String groupHeaderId;

    private Integer groupSchoolId;

    private String groupPhone;

    private Integer groupType;

    private String groupKey;

    private Integer groupStatus;

    private String groupAddress;

    private Date groupCreateTime;

    private Date groupUpdateTime;


    public Group(Integer groupId,String groupName, String groupPhone, Integer groupType, String groupAddress) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.groupPhone = groupPhone;
        this.groupType = groupType;
        this.groupAddress = groupAddress;
    }
}