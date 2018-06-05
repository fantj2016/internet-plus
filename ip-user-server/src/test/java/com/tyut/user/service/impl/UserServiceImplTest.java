package com.tyut.user.service.impl;

import com.tyut.core.response.ServerResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by Fant.J.
 * 2018/6/5 17:13
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @Autowired
    private UserServiceImpl service;
    @Test
    public void selectMe() {
        service.selectMe("15235951681");
    }
}
