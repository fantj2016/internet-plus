package com.tyut.core.pojo;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "ip_user")
public class User  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;
    @Column(nullable = false)
    private String userName;
    @Column(nullable = false)
    private String userPasswd;
    @Column(nullable = false)
    private String userPhone;
    @Column(nullable = false)
    private String userEmail;
    @Column(nullable = false)
    private Integer userSex;
    @Column(nullable = false)
    private Integer userEducation;
    @Column(nullable = false)
    private String userSchool;
    @Column(nullable = false)
    private Integer userSchoolId;
    @Column(nullable = false)
    private String userAcademy;
    @Column(nullable = false)
    private Integer userAcademyId;
    @Column(nullable = false)
    private String userProfession;
    @Column(nullable = false)
    private String userStuNum;
    @Column(nullable = false)
    private String userGrade;
    @Column(nullable = false)
    private Integer userStatus;
    @Column(nullable = false)
    private Date userCreateTime;
    @Column(nullable = false)
    private Date userUpdateTime;

}