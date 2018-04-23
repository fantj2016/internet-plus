package com.tyut.core.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@Data
@Entity
@Table(name = "ip_group_members")
public class Members  implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    private Integer groupId;

    private Integer userId;
}