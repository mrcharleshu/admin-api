package com.charles.admin.service.impl;

import com.charles.admin.domain.User;
import com.charles.admin.repository.UserRepository;
import com.charles.admin.service.UserService;
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
