package com.tyut.user.service.impl;

import com.tyut.core.pojo.UserEdu;
import com.tyut.user.repostory.UserEduRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Fant.J.
 * 2018/5/6 16:53
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserEduServiceImplTest {

    @Autowired
    private UserEduRepository repository;

    @Test
    public void getAll() {
        List<UserEdu> all = repository.findAll();
        all.forEach(p-> System.out.println(p.toString()));
    }
}
