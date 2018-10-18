package com.pp.springbootandmybatis;

import com.pp.model.User;
import com.pp.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootAndMybatisApplicationTests {

    @Test
    public void contextLoads() {
    }
    @Autowired
    private UserService userService;
    @Test
    public void TestFindUser(){
        List<User> tmp = userService.findAllUser(1,3);
        System.out.println(tmp.size());

    }
}
