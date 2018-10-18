package com.pp.springbootandmybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.pp.mapper")
@ComponentScan(basePackages = {"com.pp"})
public class SpringbootAndMybatisApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 注意这里要指向原先用main方法执行的Application启动类
        return builder.sources(SpringbootAndMybatisApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootAndMybatisApplication.class,args);
    }
}
