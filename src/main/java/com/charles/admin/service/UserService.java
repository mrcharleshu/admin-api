package com.charles.admin.service;


import com.charles.admin.domain.User;

public interface UserService {
    User findOne(Integer userId);

    User saveOne(User user);
}
