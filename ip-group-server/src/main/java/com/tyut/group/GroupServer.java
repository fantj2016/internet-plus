package com.tyut.group;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan("com.tyut.core")
public class GroupServer {

    public static void main(String[] args) {
        SpringApplication.run(GroupServer.class, args);
    }
}
