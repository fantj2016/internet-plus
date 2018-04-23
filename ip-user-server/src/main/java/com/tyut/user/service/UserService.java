package com.tyut.user.service;


import com.tyut.user.domain.User;
import com.tyut.user.response.ServerResponse;

/**
 * Created by Fant.J.
 * 2018/4/21 13:40
 */
public interface UserService {
    /** 增加*/
    ServerResponse insert(User user);
    /** 更新*/
    ServerResponse update(User user);
    /** 查询单个*/
    ServerResponse selectById(int id);
}
