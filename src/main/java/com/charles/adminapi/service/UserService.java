package com.charles.adminapi.service;


import com.charles.adminapi.domain.User;

public interface UserService {
    User findOne(Integer userId);

    User saveOne(User user);
}
