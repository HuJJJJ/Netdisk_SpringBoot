package com.example.netdisk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication

@ServletComponentScan(basePackages = "com.example.netdisk.servlet")
public class HjjjjNetdiskApplication {

    public static void main(String[] args) {
        SpringApplication.run(HjjjjNetdiskApplication.class, args);
    }

}
