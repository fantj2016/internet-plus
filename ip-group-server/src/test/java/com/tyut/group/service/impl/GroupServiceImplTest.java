package com.tyut.group.service.impl;

import com.tyut.core.pojo.Group;
import com.tyut.core.response.ServerResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

/**
 * Created by Fant.J.
 * 2018/5/27 13:05
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GroupServiceImplTest {


    @Autowired
    private GroupServiceImpl groupService;

    @Autowired
    private GroupMemServiceImpl memService;

    @Test
    public void testCreateGroup(){

        Group group = new Group();
        group.setGroupName("云顶队");
        group.setGroupType(3);
//        group.setGroupHeaderId(1);
        group.setGroupPhone("15235951681");
        groupService.create(group);
    }

    @Test
    public void testselectByKey(){
        ServerResponse e80fd1437a5b45d791f6640e03007ef2 = groupService.selectByKey("e80fd1437a5b45d791f6640e03007ef2");
        System.out.println(e80fd1437a5b45d791f6640e03007ef2.toString());
    }

    @Test
    public void testJoin(){
//        memService.join(60008,12,"sdf");
    }

    @Test
    public void selectGroupList(){
        groupService.selectGroupList(12);
    }

    @Test
    public void testfindAllBygGroupId(){
        memService.findAllBygGroupId(60008);
    }


}
