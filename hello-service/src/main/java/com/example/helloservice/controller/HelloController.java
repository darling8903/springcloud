package com.example.helloservice.controller;

import com.example.helloservice.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/users")
public class HelloController {

    @Autowired
    private DiscoveryClient client;

    private List<User> products;
    @PostConstruct
    protected void buildProducts() {
        products = new ArrayList<>();
        products.add(new User(22L,"liudongling"));
        products.add(new User(24L,"zhaojianxing"));
        products.add(new User(26L,"sunwukong"));
    }



    //这什么都不是
    @RequestMapping(value = "/hello/{id}", method = RequestMethod.GET)
    public Object index(@PathVariable("id") Long id){
        System.out.println(id);
        User user = new User();
        //此处加休眠是测feignconsumer的重试次数
//        int sleeptime = new Random().nextInt(4900);
//        System.out.println("测feignconsumer的重试标志");
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        //********************************************
        if(id==1){
            user.setId(1L);
            user.setName("liudongling");
        }
        if(id==2){
            user.setId(2L);
            user.setName("zhaojianxing");
        }
        ServiceInstance localServiceInstance = client.getLocalServiceInstance();
        System.out.println("host:==="+localServiceInstance.getHost()+"port:==="+localServiceInstance.getPort());
        return user;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<User> list() {
        return products;
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public User detail(@PathVariable String username) {
        for (User product : products) {
            if (product.getName().equalsIgnoreCase(username))
                return product;
        }
        return null;
    }

    @RequestMapping(value = "/postuser", method = RequestMethod.POST)
    public void save(@RequestBody User user) {
        System.out.println("*****************postuser:"+user);
    }

    @RequestMapping(value = "/userbyheader", method = RequestMethod.GET)
    public void userbyheader(@RequestHeader String name,@RequestHeader Integer age) {
        System.out.println("****header_name:"+name+"****header_age:"+age);
    }

}
