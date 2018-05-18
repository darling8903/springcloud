package com.example.ribbonconsumer;

import com.example.ribbonconsumer.entity.User;

public class Controller {

    static TestService service = new TestService();

    public void inputUser(){

        System.out.println("我是controller--ThreadName=" + Thread.currentThread().getName()+Manager.getUser().toString());

        Manager.userThreadLocal.remove();
        service.getUser();

    }
}
