package com.tyut.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

@SpringBootApplication
@EntityScan("com.tyut.core.pojo")
@MapperScan("com.tyut.user.dao")
public class UserServer {

    public static void main(String[] args) {
        SpringApplication.run(UserServer.class, args);
    }
}
