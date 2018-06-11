package com.tyut.core.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserUpdateVo implements Serializable {

    private String userId;
    private String userName;
    private String userPhone;
    private Integer userEducation;
    private Integer userSchoolId;
    private String userAcademy;
    private String userProfession;
    private String userStuNum;
    private String userGrade;
}