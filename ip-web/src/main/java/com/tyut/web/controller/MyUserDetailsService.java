package com.tyut.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.tyut.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Created by Fant.J.
 * 2018/5/2 20:47
 */
@Component
@Slf4j
public class MyUserDetailsService implements UserDetailsService {

    @Reference(version = "2.0.0")
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String passwd = "";
        log.info("收到的账号"+username);
        if (CheckFormat.isEmail(username)){
             passwd = userService.selectPasswdByEmail(username);
        }else if (CheckFormat.isPhone(username)){
             passwd = userService.selectPasswdByPhone(username);
        }else {
            throw new RuntimeException("登录账号不存在");
        }
        log.info("查到的密码"+passwd);
        return new User(username, passwd, AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER"));
    }
}
