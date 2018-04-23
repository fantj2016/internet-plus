package com.tyut.user.service.impl;

import com.tyut.user.domain.User;
import com.tyut.user.repostory.UserRepository;
import com.tyut.user.response.ServerResponse;
import com.tyut.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.dubbo.config.annotation.Service;

/**
 * Created by Fant.J.
 * 2018/4/21 13:41
 */
@Service(version = "2.0.0")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * 增加
     */
    @Override
    public ServerResponse insert(User user) {
        User save = userRepository.save(user);
        if (save != null){
            return ServerResponse.createBySuccessMessage("用户添加成功");
        }
        return ServerResponse.createByErrorMessage("用户添加失败");
    }

    /**
     * 更新
     */
    @Override
    public ServerResponse update(User user) {
        return null;
    }

    /**
     * 查询单个
     */
    @Override
    public ServerResponse selectById(int id) {
        User user = userRepository.findOne(id);
        if (user == null){
            return ServerResponse.createByErrorMessage("找不到该用户");
        }
        user.setUserPasswd(" ");
        return ServerResponse.createBySuccess(user);
    }
}
