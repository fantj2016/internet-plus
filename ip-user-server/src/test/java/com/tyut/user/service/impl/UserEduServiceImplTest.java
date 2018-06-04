package com.tyut.user.service.impl;

import com.tyut.core.pojo.UserEdu;
import com.tyut.user.repostory.UserEduRepository;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.Charset;
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
    @Autowired
    private UserServiceImpl userService;

    @Test
    public void getAll() {
        List<UserEdu> all = repository.findAll();
        all.forEach(p-> System.out.println(p.toString()));
    }

    @Test
    public void upload() throws IOException {
        String path = "/upload";
        MockMultipartFile mockMultipartFile = new MockMultipartFile("QQ图片20180430184714.jpg", new FileInputStream(new File("C:\\Users\\84407\\Desktop\\QQ图片20180430184714.jpg")));
        userService.uploadFile((MultipartFile)mockMultipartFile, path);
    }
}
