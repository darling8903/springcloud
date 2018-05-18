package com.example.ribbonconsumer.fallbackservice;

import com.example.ribbonconsumer.entity.User;
import com.example.ribbonconsumer.interfacecontroller.InterfaceUserController;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class UserFallbackService implements InterfaceUserController {
    @Override
    public List<User> findAll() {
        return Collections.emptyList();
    }

    @Override
    public User loadByItemCode(String itemCode) {
        return new User(999L,"nobody");
    }
}
