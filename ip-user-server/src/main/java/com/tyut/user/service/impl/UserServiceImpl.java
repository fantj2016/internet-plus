package com.tyut.user.service.impl;

import com.tyut.core.pojo.User;
import com.tyut.core.response.ServerResponse;
import com.tyut.core.vo.UserVo;
import com.tyut.user.repostory.UserRepository;
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
            return ServerResponse.createBySuccessMessage("注册成功");
        }
        return ServerResponse.createByErrorMessage("注册失败");
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
    public ServerResponse<UserVo> selectById(int id) {
        User user = userRepository.findOne(id);
        if (user == null){
            return ServerResponse.createByErrorMessage("找不到该用户");
        }
        UserVo userVo = new UserVo();
        userVo.setUserId(user.getUserId());
        userVo.setUserName(user.getUserName());
        userVo.setUserPhone(user.getUserPhone());
        userVo.setUserSchoolId(user.getUserSchoolId());
        return ServerResponse.createBySuccess(userVo);
    }

    /**
     * 根据email查询密码
     *
     * @param email
     */
    @Override
    public String selectPasswdByEmail(String email) {
        return userRepository.selectPasswdByEmail(email);
    }

    /**
     * 根据 phone 查询密码
     *
     * @param phone
     */
    @Override
    public String selectPasswdByPhone(String phone) {
        return userRepository.selectPasswdByPhone(phone);
    }

    /**
     * 判断 email 是否存在
     *
     * @param email
     */
    @Override
    public ServerResponse isExistEmail(String email) {
        int i = userRepository.selectIsExistEmail(email);
        if (i==0){
            //证明 phone 可以使用
            return ServerResponse.createBySuccess("OK");
        }else {
            return ServerResponse.createByErrorMessage("该邮箱已被注册");
        }
    }

    /**
     * 判断 phone 是否存在
     *
     * @param phone
     */
    @Override
    public ServerResponse isExistPhone(String phone) {
        int i = userRepository.selectIsExistPhone(phone);
        if (i==0){
            //证明 phone 可以使用
            return ServerResponse.createBySuccess("OK");
        }else {
            return ServerResponse.createByErrorMessage("该手机号已被注册");
        }
    }

}
