package com.example.feignconsumer.controller;

import com.example.feignconsumer.entity.User;
import com.example.feignconsumer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/users",method = RequestMethod.GET)
    public String getUsers(){
        return userService.getUsers();
    }

    @RequestMapping(value = "/testuser",method = RequestMethod.GET)
    public String testuser(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(userService.getUserById("1")).append("\n");
        stringBuilder.append(userService.userbyheader("liudl",23)).append("\n");
        stringBuilder.append(userService.postuser(new User(500L,"zhubajie"))).append("\n");
        return stringBuilder.toString();
    }
}
