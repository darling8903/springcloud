package com.example.ribbonconsumer;

import com.example.ribbonconsumer.entity.User;

public class ThreadTest {
    public static void main(String[] args) {

        Controller controller = new Controller();

        new Thread(new Runnable() {
            @Override
            public void run() {
                User u = new User();
                u.setId(1111L);
                u.setName("liudongling");
                Manager.setUser(u);

                controller.inputUser();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                User u = new User();
                u.setId(2222L);
                u.setName("zhaojianxing");
                Manager.setUser(u);
                controller.inputUser();
            }
        }).start();
    }
}
