package com.zhao.springboot.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ShopApplication {

    public static void main(String[] args) {
        //这里是主版本
        SpringApplication.run(ShopApplication.class, args);
    }

}
