package com.tyut.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.tyut.user.service.UserService;
import com.tyut.web.exception.BaseException;
import com.tyut.web.util.CheckFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Fant.J.
 * 2018/5/2 20:47
 */
@Component
public class MyUserDetailsService implements UserDetailsService {

    @Reference(version = "2.0.0")
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String passwd = "";
        System.out.println("收到的账号"+username);
        if (CheckFormat.isEmail(username)){
             passwd = userService.selectPasswdByEmail(username);
        }else if (CheckFormat.isPhone(username)){
             passwd = userService.selectPasswdByPhone(username);
        }else {
            throw new RuntimeException("登录账号不存在");
        }
        System.out.println("查到的密码"+passwd);
        return new User(username, passwd, AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER"));
    }
}
