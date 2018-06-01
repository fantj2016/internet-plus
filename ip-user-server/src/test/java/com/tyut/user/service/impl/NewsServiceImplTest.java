package com.tyut.user.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by Fant.J.
 * 2018/6/1 23:55
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class NewsServiceImplTest {

    @Autowired
    private NewsServiceImpl newsService;

    @Test
    public void selectNewsList() {
        newsService.selectNewsList(12,0,3);
    }

    @Test
    public void hasRead() {
        newsService.hasRead(1);
    }
}
