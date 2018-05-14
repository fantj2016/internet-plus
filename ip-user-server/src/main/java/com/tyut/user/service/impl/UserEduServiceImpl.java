package com.tyut.user.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.tyut.core.pojo.UserEdu;
import com.tyut.core.response.ServerResponse;
import com.tyut.user.repostory.UserEduRepository;
import com.tyut.user.service.UserEduService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Fant.J.
 * 2018/5/6 16:01
 */
@Service(version = "2.0.0")
public class UserEduServiceImpl implements UserEduService {

    @Autowired
    private UserEduRepository userEduRepository;

    @Override
    public ServerResponse getAll() {
        List<UserEdu> all = userEduRepository.findAll();
        return ServerResponse.createBySuccess(all);
    }
}
