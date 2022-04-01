package com.fundamentsproyect.demo.caseuse;

import com.fundamentsproyect.demo.entity.User;
import com.fundamentsproyect.demo.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class CreateUser {
    private UserService userService;

    public CreateUser(UserService userService) {
        this.userService = userService;
    }

    public User save(User newUser) {
        return userService.save(newUser);
    }
}
