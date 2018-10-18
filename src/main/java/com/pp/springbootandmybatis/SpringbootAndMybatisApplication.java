package com.pp.springbootandmybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.pp.mapper")
@ComponentScan(basePackages = {"com.pp"})
public class SpringbootAndMybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootAndMybatisApplication.class,args);
    }
}
