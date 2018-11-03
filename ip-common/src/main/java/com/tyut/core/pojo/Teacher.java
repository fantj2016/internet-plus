package com.tyut.core.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "ip_teacher")
public class Teacher implements Serializable {
    @Id
    @GeneratedValue
    private Integer teacherId;

    private Integer groupId;

    private String teacherName;

    private String teacherPhone;
}
