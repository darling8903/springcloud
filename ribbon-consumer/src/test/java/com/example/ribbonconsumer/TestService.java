package com.example.ribbonconsumer;

import com.example.ribbonconsumer.entity.User;

public class TestService {

    public void getUser(){
        User user1 = Manager.getUser();
        System.out.println("我是service ThreadName="+Thread.currentThread().getName()+user1.toString());
    }
}
