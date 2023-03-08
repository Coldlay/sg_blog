package com.liumou;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author coldplay
 * @create 2023-02-24 9:13
 */
@SpringBootApplication
@MapperScan("com.liumou.mapper")
@EnableScheduling
public class HongShuBlogApplication {
    public static void main(String[] args) {

        SpringApplication.run(HongShuBlogApplication.class,args);
    }
}
