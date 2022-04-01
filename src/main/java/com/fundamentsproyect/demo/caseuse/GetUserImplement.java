package com.fundamentsproyect.demo.caseuse;

import com.fundamentsproyect.demo.entity.User;
import com.fundamentsproyect.demo.service.UserService;

import java.util.List;

public class GetUserImplement  implements GetUser
{
    private UserService userService;

    public GetUserImplement(UserService userService) {
        this.userService = userService;
    }

    @Override
    public List<User> getAll() {
        return userService.getAllUser();
    }
}
