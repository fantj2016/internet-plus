package com.tyut.web.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by Fant.J.
 * 2018/5/3 15:46
 */
@Data
public class RegisterDto {

    private String email;
    private String phone;
    @NotBlank
    private String password;
    private String checkPassword;
    @NotBlank
    private Integer schoolId;
    @ApiModelProperty("学院")
    private String academy;
    @ApiModelProperty("专业")
    private String profession;
    @ApiModelProperty("年级")
    private String grade;
    @ApiModelProperty("学号")
    @NotBlank
    private String num;
    private String userName;
    private Integer sex;
    @ApiModelProperty("学历：本科、硕士..")
    private Integer edu;
}
