package com.charles.adminapi.service.impl;

import com.charles.adminapi.domain.User;
import com.charles.adminapi.repository.UserRepository;
import com.charles.adminapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User findOne(Integer userId) {
        return userRepository.findOne(userId);
    }

    @Override
    public User saveOne(User user) {
        return userRepository.save(user);
    }
}
