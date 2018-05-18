package com.example.ribbonconsumer.interfacecontroller;

import com.example.ribbonconsumer.entity.User;
import com.example.ribbonconsumer.fallbackservice.UserFallbackService;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name="HELLO-SERVER", fallback = UserFallbackService.class)
public interface InterfaceUserController {

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    List<User> findAll();

    @RequestMapping(value = "/users/{username}", method = RequestMethod.GET)
    User loadByItemCode(@PathVariable("username") String itemCode);
}
