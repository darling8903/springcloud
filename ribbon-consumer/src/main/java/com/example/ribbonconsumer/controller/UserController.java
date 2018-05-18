package com.example.ribbonconsumer.controller;


import com.example.ribbonconsumer.entity.User;
import com.example.ribbonconsumer.interfacecontroller.InterfaceUserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {


    
    @Autowired
    private InterfaceUserController interfaceUserController;

    @RequestMapping(method = RequestMethod.GET)
    public List<User> list() {
        return this.interfaceUserController.findAll();
    }

    @RequestMapping(value = "/{itemCode}", method = RequestMethod.GET)
    public User detail(@PathVariable String itemCode) {
        return this.interfaceUserController.loadByItemCode(itemCode);
    }




}
