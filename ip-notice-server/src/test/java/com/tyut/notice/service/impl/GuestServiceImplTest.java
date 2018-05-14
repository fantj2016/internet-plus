package com.tyut.notice.service.impl;

import com.tyut.core.pojo.Guest;
import com.tyut.core.response.ServerResponse;
import com.tyut.notice.repostory.GuestRepostory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Fant.J.
 * 2018/4/30 16:01
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GuestServiceImplTest {

    @Autowired
    private GuestServiceImpl guestService;

    @Test
    public void selectAll0() {
        ServerResponse all0 = guestService.selectAll0(1);
        System.out.println(all0.getData().toString());
    }
}
