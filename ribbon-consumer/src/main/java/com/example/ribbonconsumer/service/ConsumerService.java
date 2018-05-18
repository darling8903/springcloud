package com.example.ribbonconsumer.service;

import com.example.ribbonconsumer.entity.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ConsumerService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "helloFallback")
    public String helloConsumer(){

        //Future<User> future = new UserCommand(com.netflix.hystrix.HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("example")),restTemplate,1L).queue();
//        User u = null;
//        try {
//            u = future.get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//        return u;

        return restTemplate.getForEntity("http://HELLO-SERVER/users",String.class).getBody();
    }

    public String helloFallback(){
        return "error";
    }

}
