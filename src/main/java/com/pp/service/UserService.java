package com.pp.service;

import com.pp.model.User;

import java.util.List;

public interface UserService {
    int addUser(User user);
    List<User> findAllUser(int pageNum, int p);
}
