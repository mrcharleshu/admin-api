package com.charles.adminapi.web;


import com.charles.adminapi.domain.User;
import com.charles.adminapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping(value = "users/{userId}")
    public User findOne(@PathVariable final Integer userId) {
        return userService.findOne(userId);
    }

    @PostMapping(value = "users")
    public User save(@RequestBody final User user) {
        return userService.saveOne(user);
    }
}
