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
@Table(name = "ip_cpt_grade")

public class Grade  implements Serializable {

    @Id
    @GeneratedValue
    private Integer gradeId;

    private Integer gradeGroupId;

    private Date gradeCreateTime;

    private Date gradeUpdateTime;
}