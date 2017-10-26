package com.charles.admin.web;


import com.charles.admin.domain.User;
import com.charles.admin.service.UserService;
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
