package com.tyut.user.domain;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "ip_user")
public class User implements Serializable {
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
    private String userSex;
    @Column(nullable = false)
    private String userEducation;
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
    private Integer userStuNum;
    @Column(nullable = false)
    private String userGrade;
    @Column(nullable = false)
    private Integer userStatus;
    @Column(nullable = false)
    private Date userCreateTime;
    @Column(nullable = false)
    private Date userUpdateTime;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPasswd() {
        return userPasswd;
    }

    public void setUserPasswd(String userPasswd) {
        this.userPasswd = userPasswd;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getUserEducation() {
        return userEducation;
    }

    public void setUserEducation(String userEducation) {
        this.userEducation = userEducation;
    }

    public String getUserSchool() {
        return userSchool;
    }

    public void setUserSchool(String userSchool) {
        this.userSchool = userSchool;
    }

    public Integer getUserSchoolId() {
        return userSchoolId;
    }

    public void setUserSchoolId(Integer userSchoolId) {
        this.userSchoolId = userSchoolId;
    }

    public String getUserAcademy() {
        return userAcademy;
    }

    public void setUserAcademy(String userAcademy) {
        this.userAcademy = userAcademy;
    }

    public Integer getUserAcademyId() {
        return userAcademyId;
    }

    public void setUserAcademyId(Integer userAcademyId) {
        this.userAcademyId = userAcademyId;
    }

    public String getUserProfession() {
        return userProfession;
    }

    public void setUserProfession(String userProfession) {
        this.userProfession = userProfession;
    }

    public Integer getUserStuNum() {
        return userStuNum;
    }

    public void setUserStuNum(Integer userStuNum) {
        this.userStuNum = userStuNum;
    }

    public String getUserGrade() {
        return userGrade;
    }

    public void setUserGrade(String userGrade) {
        this.userGrade = userGrade;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public Date getUserCreateTime() {
        return userCreateTime;
    }

    public void setUserCreateTime(Date userCreateTime) {
        this.userCreateTime = userCreateTime;
    }

    public Date getUserUpdateTime() {
        return userUpdateTime;
    }

    public void setUserUpdateTime(Date userUpdateTime) {
        this.userUpdateTime = userUpdateTime;
    }
}