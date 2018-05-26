package com.tyut.user.service;


import com.tyut.core.pojo.User;
import com.tyut.core.response.ServerResponse;

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
    /** 根据email查询密码 */
    String selectPasswdByEmail(String email);
    /** 根据 phone 查询密码 */
    String selectPasswdByPhone(String phone);
    /** 判断 email 是否存在*/
    ServerResponse isExistEmail(String email);
    /** 判断 phone 是否存在*/
    ServerResponse isExistPhone(String phone);
    /** 通过 手机或者 邮箱 查询 个人信息 */
    ServerResponse selectMe(String str);
}
