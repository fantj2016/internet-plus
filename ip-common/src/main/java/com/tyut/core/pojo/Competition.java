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
@Table(name = "ip_competition")
public class Competition  implements Serializable {
    @Id
    @GeneratedValue
    private Integer cptId;

    private String cptName;

    private String cptType;

    private String cptIcon;

    private String cptImg;

    private String cptContent;

    private String cptIntro;

    private Date cptApplyTime;

    private Date cptStartTime;

    private Date cptEndTime;

    private Integer cptSupportId;

    private Integer cptStatus;

    private Date cptCreateTime;

    private Date cptUpdateTime;

}