package com.example.ribbonconsumer.controller;

import com.example.ribbonconsumer.command.UserCommand;
import com.example.ribbonconsumer.entity.User;
import com.example.ribbonconsumer.service.ConsumerService;
import com.netflix.discovery.converters.Auto;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
@RequestMapping(value = "/consumer")
public class ConsumerController {

    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    ConsumerService consumerService;


    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public String getUserById(@RequestParam(value = "id") String id){

        System.out.println("getUserById");
        //ResponseEntity<User> forEntity = restTemplate.getForEntity("http://HELLO-SERVER/hello/{1}", User.class,2);
//        UriComponents uriComponents = UriComponentsBuilder.fromUriString("http://HELLO-SERVER/hello/{id}")
//                .build().expand(1).encode();
//        URI uri = uriComponents.toUri();
//        ResponseEntity<User> forEntity = restTemplate.getForEntity(uri, User.class);
//        return forEntity.getBody();
        return restTemplate.getForEntity("http://HELLO-SERVER/users/hello/{1}",String.class,id).getBody();
    }

    @RequestMapping(value = "/userobj",method = RequestMethod.GET)
    public User getUserObjById(@RequestParam(value = "id") String id){
        System.out.println("getUserObjById");
        ResponseEntity<User> forEntity = restTemplate.getForEntity("http://HELLO-SERVER/users/hello/{1}", User.class,id);
//        UriComponents uriComponents = UriComponentsBuilder.fromUriString("http://HELLO-SERVER/hello/{id}")
//                .build().expand(1).encode();
//        URI uri = uriComponents.toUri();
//        ResponseEntity<User> forEntity = restTemplate.getForEntity(uri, User.class);
        return forEntity.getBody();
    }

    @RequestMapping(value = "/users",method = RequestMethod.GET)
    public String getUsers(){
        System.out.println("getUsers");
        return restTemplate.getForEntity("http://HELLO-SERVER/users",String.class).getBody();
    }

    @RequestMapping(value = "/saveusers",method = RequestMethod.GET)
    public String saveUsers(){
        System.out.println("saveusers");
        User user = new User(RandomUtils.nextLong(), UUID.randomUUID().toString());
        return restTemplate.postForEntity("http://HELLO-SERVER/users/save",user,String.class).getBody();
    }

   @RequestMapping(value = "/hystrixusers",method = RequestMethod.GET)
    public String hystrixGetUsers(){
        return consumerService.helloConsumer();
    }

    @RequestMapping(value = "/usercommandusers",method = RequestMethod.GET)
    public User usercommandGetUsers(){
        HystrixRequestContext.initializeContext();
        User u = new UserCommand(com.netflix.hystrix.HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("example"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("example22")),restTemplate,1L).execute();
        //判断是不是根据分组实现缓存
        Future<User> userfuture = new UserCommand(com.netflix.hystrix.HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("example1"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("example22")),restTemplate,1L).queue();
        try {
            Thread.sleep(4000);
            try {
                User user = userfuture.get();
                System.out.println("sleep 之后的user"+user.toString());
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return u;
    }


}
