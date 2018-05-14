package com.tyut.school;

import com.tyut.core.response.ServerResponse;
import com.tyut.school.service.impl.SchoolServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SchoolServerTests {

    @Autowired
    SchoolServiceImpl schoolService;

    @Test
    public void testselectAll() {
        ServerResponse serverResponse = schoolService.selectAll();
        System.out.println(serverResponse.getData().toString());
    }

}
