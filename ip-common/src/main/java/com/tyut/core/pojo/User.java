package com.tyut.core.pojo;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "ip_user")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class User  implements Serializable {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 32)
    private String userId;
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
    private String userPortrait;
    @Column(nullable = false)
    private Date userCreateTime;
    @Column(nullable = false)
    private Date userUpdateTime;
//
//    @OneToMany
//    @JoinColumn(name = "user_id")
//    private GroupMembers groupMembers;

}