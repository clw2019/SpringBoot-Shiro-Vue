package com.clw;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: clw
 * @Description: SpringBoot 启动类
 * @Date: 2020/7/21 23:00
 */
@SpringBootApplication
@MapperScan("com.clw.*.mapper")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
