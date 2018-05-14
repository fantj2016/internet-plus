package com.tyut.notice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EntityScan("com.tyut.core.pojo")
public class NoticeServer {

    public static void main(String[] args) {
        SpringApplication.run(NoticeServer.class, args);
    }

}
