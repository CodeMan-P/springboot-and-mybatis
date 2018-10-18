package com.pp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pp.model.User;
import com.pp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping(value = "/user")
public class UserController {
    @RequestMapping(value = {"/", "/index"})
    public String index(Map<String, Object> model) {
        model.put("time", new Date());
        model.put("message", "hello");
        return "index";
    }
    @GetMapping("/thymeleaf")
    String test(HttpServletRequest request) {
        //逻辑处理
        request.setAttribute("key", "hello world");
        return "thymeleaf/index";
    }

    @Autowired
    private UserService userService;
    @Autowired
    private ObjectMapper objectMapper;
    @ResponseBody
    @RequestMapping(value = "/add", produces = {"application/json;charset=UTF-8"})
    public int addUser(User user) {
        //{"id":12,"status":0,"loginId":"test10","loginPwd":"000000","nickname":"堕落天使","regTime":1419872149000,"info":"","tag":""}
        try {
            System.out.print(objectMapper.writeValueAsString(user));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return userService.addUser(user);
    }

    @ResponseBody
    @RequestMapping(value = "/all/{pageNum}/{pageSize}", produces = {"application/json;charset=UTF-8"})
    public Object findAllUser(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize) {
        return userService.findAllUser(pageNum, pageSize);
    }

}
