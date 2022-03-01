package com.example.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//exclude = {DataSourceAutoConfiguration.class}
@SpringBootApplication()
@MapperScan({"com.example.server.mapper"})
public class Applocation {
    public static void main(String[] args) {
        SpringApplication.run(Applocation.class, args);
        System.out.println("hello");
    }
}
