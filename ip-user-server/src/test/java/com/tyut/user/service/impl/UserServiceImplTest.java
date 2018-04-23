package com.tyut.user.service.impl;

import com.tyut.user.response.ServerResponse;
import com.tyut.user.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Fant.J.
 * 2018/4/21 19:00
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {


    @Autowired
    private UserService userService;

    @Test
    public void selectById() {
//        User one = repository.findOne(1);
        ServerResponse serverResponse = userService.selectById(1);
        System.out.println(serverResponse.getData()+" "+serverResponse.getStatus());
    }
}
