package com.tyut.notice.service.impl;

import com.tyut.core.pojo.Notice;
import com.tyut.notice.repostory.NoticeRepostory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Fant.J.
 * 2018/4/30 14:52
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class NoticeServiceImplTest {

    @Autowired
    private NoticeRepostory repostory;

    @Test
    public void selectById() {
    }

    @Test
    public void selectAll() {
        Pageable pageable = new PageRequest(0,3,Sort.Direction.DESC,"noticeId");
        /*Page<Notice> all = repostory.findAll(pageable);
        System.out.println(all);
        all.forEach(p-> System.out.println(p.toString()));*/

        Iterator<Notice> all = repostory.findAll(pageable).iterator();
        Page<Notice> noticePage = repostory.findAll(pageable);
        System.out.println("page对象============="+noticePage.toString());
        List<Notice> list = new ArrayList<Notice>();
        while (all.hasNext()){
            list.add(all.next());
        }
        list.forEach(p-> System.out.println(p.toString()));
    }
}
