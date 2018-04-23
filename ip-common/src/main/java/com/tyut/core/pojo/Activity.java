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
@Table(name = "ip_activity")
public class Activity  implements Serializable {

    @Id
    @GeneratedValue
    private Integer actId;

    private String actName;

    private Date actCreateTime;

    private Date actUpdateTime;

    private String actContent;

}