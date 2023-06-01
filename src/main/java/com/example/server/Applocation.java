package com.example.server;

import com.example.server.controller.ClassificationController;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//exclude = {DataSourceAutoConfiguration.class}
@SpringBootApplication()
@MapperScan({"com.example.server.mapper"})
public class Applocation {
    private static Logger logger = LoggerFactory.getLogger(Applocation.class);
    public static void main(String[] args) {
        SpringApplication.run(Applocation.class, args);
        logger.info("启动成功");
        System.out.println("启动成功");

    }
}
