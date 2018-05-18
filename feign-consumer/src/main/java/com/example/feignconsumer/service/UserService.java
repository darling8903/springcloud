package com.example.feignconsumer.service;

import com.example.feignconsumer.entity.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("hello-service")
public interface UserService {

    @RequestMapping("/users")
    String getUsers();

    @RequestMapping(value = "/users/hello/{id}",method = RequestMethod.GET)
    String getUserById(@PathVariable("id") String id);

    @RequestMapping(value = "/users/userbyheader",method = RequestMethod.GET)
    String userbyheader(@RequestHeader("name") String name, @RequestHeader("age") Integer age);

    @RequestMapping(value = "/users/postuser",method = RequestMethod.POST)
    String postuser(@RequestBody User user);

}
