package com.tyut.user.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Fant.J.
 * 2018/5/31 19:29
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GroupServiceImplTest {

    @Autowired
    private GroupServiceImpl group;
    @Test
    public void selectGroupList() {
        group.selectGroupList(12);

    }
}
