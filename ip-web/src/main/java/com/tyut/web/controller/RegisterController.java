package com.tyut.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.tyut.core.constants.ConsParams;
import com.tyut.core.constants.SexEnum;
import com.tyut.core.pojo.User;
import com.tyut.core.response.ResponseCode;
import com.tyut.core.response.ServerResponse;
import com.tyut.core.utils.FTPUtil;
import com.tyut.user.service.UserService;
import com.tyut.web.dto.RegisterDto;
import com.tyut.web.util.CheckFormat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Created by Fant.J.
 * 2018/5/3 17:20
 */
@Api(description = "邮箱手机号校验、用户注册")
@RestController
@RequestMapping("/register")
public class RegisterController {

    @Reference(version = "2.0.0")
    private UserService userService;

    @ApiOperation("校验email是否已被使用")
    @PostMapping("/checkEmail")
    public ServerResponse checkEmail(@RequestParam String email){
        if (!CheckFormat.isEmail(email)){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(),
                    ConsParams.Email.GET_FORMAT_ERROR_MSG);
        }
        return userService.isExistEmail(email);
    }

    @ApiOperation("校验phone是否已被使用")
    @PostMapping("/checkPhone")
    public ServerResponse checkPhone(@RequestParam String phone){
        System.out.println("拿到的phone:"+phone);

        if (!CheckFormat.isPhone(phone)){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(),
                    ConsParams.Phone.GET_FORMAT_ERROR_MSG);
        }

        return userService.isExistPhone(phone);
    }

    @ApiOperation("注册用户")
    @PostMapping("/user")
    public ServerResponse registerUser(RegisterDto registerDto){
        if (!CheckFormat.isPhone(registerDto.getPhone()) ) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(),
                    ConsParams.Phone.GET_FORMAT_ERROR_MSG);
        }
        if (!CheckFormat.isEmail(registerDto.getEmail())) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(),
                    ConsParams.Email.GET_FORMAT_ERROR_MSG);
        }
        if (!StringUtils.equals(String.valueOf(userService.isExistEmail(registerDto.getEmail()).getData()),
                ConsParams.Email.GET_EXIST_SUCCESS_MSG)){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(),
                    ConsParams.Email.GET_EXIST_ERROR_MSG);
        }
        if (!StringUtils.equals(String.valueOf(userService.isExistPhone(registerDto.getPhone()).getData()),
                ConsParams.Phone.GET_EXIST_SUCCESS_MSG)){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.ERROR.getCode(),
                    ConsParams.Phone.GET_EXIST_ERROR_MSG);
        }
        //如果两次密码相同
        if (StringUtils.equals(registerDto.getPassword(),registerDto.getCheckPassword())){
            User user = new User();
            user.setUserName(registerDto.getName());
            user.setUserPasswd(registerDto.getPassword());
            user.setUserStuNum(registerDto.getNum());
            user.setUserEmail(registerDto.getEmail());
            user.setUserPhone(registerDto.getPhone());
            user.setUserAcademy(registerDto.getAcademy());
            user.setUserSchoolId(registerDto.getSchoolId());
            user.setUserProfession(registerDto.getProfession());
            user.setUserGrade(registerDto.getGrade());
            user.setUserSex(registerDto.getSex());
            user.setUserEducation(registerDto.getEdu());
            user.setUserStuNum(registerDto.getNum());
            //默认头像
            if (registerDto.getSex().equals(SexEnum.GIRL.getCode())){
                user.setUserPortrait(FTPUtil.getFtpIp()+ConsParams.Portrait.BOY_PORTRAIT);
            }else {
                user.setUserPortrait(FTPUtil.getFtpIp()+ConsParams.Portrait.GIRL_PROTRAIT);
            }
            user.setUserCreateTime(new Date());
            return userService.insert(user);
        }else {
            return ServerResponse.createByErrorMessage(ConsParams.Login.PASSWD_NOT_EQUIST_MSG);
        }
    }
}
