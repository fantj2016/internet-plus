package com.tyut.school.service.impl;

import com.tyut.core.response.ServerResponse;
import com.tyut.school.service.SchoolService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by Fant.J.
 * 2018/4/24 19:30
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SchoolServiceImplTest {


    @Autowired
    private SchoolService schoolService;


    @Test
    public void selectAll() {
        ServerResponse serverResponse = schoolService.selectAll();
        System.out.println(serverResponse.getData().toString());
    }

    @Test
    public void testselectLike(){
        ServerResponse result = schoolService.selectLike("山");
        System.out.println(result.getData().toString());

    }
}
