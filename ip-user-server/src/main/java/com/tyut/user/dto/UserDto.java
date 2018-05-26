package com.tyut.user.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Fant.J.
 * 2018/5/26 21:23
 */

@Data
public class UserDto implements Serializable {
    private Integer userId;
    private String userPhone;
    private String userSchool;
    private String userEmail;
    private String userName;
    private String userAcademy;
    private Integer userEducation;
    private String userGrade;
    private String userProfession;
    private Integer userSex;
    private String userStuNum;

    public UserDto(Integer userId, String userPhone, String userSchool, String userEmail, String userName, String userAcademy, Integer userEducation, String userGrade, String userProfession, Integer userSex, String userStuNum) {
        this.userId = userId;
        this.userPhone = userPhone;
        this.userSchool = userSchool;
        this.userEmail = userEmail;
        this.userName = userName;
        this.userAcademy = userAcademy;
        this.userEducation = userEducation;
        this.userGrade = userGrade;
        this.userProfession = userProfession;
        this.userSex = userSex;
        this.userStuNum = userStuNum;
    }
}
