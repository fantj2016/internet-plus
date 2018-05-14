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

    private Integer groupHeaderId;

    private Integer groupSchoolId;

    private String groupPhone;

    private Integer groupType;

    private Integer groupStatus;

    private Date groupCreateTime;

    private Date groupUpdateTime;

}