package com.zhao.springboot.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ShopApplication {
//这里是分支一代码
    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class, args);
    }

}
