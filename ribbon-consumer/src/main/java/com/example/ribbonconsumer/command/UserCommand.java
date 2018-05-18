package com.example.ribbonconsumer.command;

import com.example.ribbonconsumer.entity.User;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


public class UserCommand extends HystrixCommand<User> {

    private RestTemplate restTemplate;
    private Long id;

    public UserCommand(Setter setter,RestTemplate restTemplate, Long id) {
        super(setter);
        System.out.println("construct method");
        this.restTemplate = restTemplate;
        this.id = id;
    }

    @Override
    protected User run() throws Exception {
        System.out.println("request method run");
        User forObject = restTemplate.getForObject("http://HELLO-SERVER/users/hello/{1}", User.class, id);
        return forObject;
    }

      @Override
      protected String getCacheKey() {
          return String.valueOf(id);
      }

    @Override
    protected User getFallback() {
        return new User(-1L,"no name");
    }
}
