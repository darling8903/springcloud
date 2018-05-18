package com.example.ribbonconsumer;

import com.example.ribbonconsumer.entity.User;

public class Manager {
    static ThreadLocal<User> userThreadLocal = new ThreadLocal<User>();

    public static User getUser(){
        User u = (User)userThreadLocal.get();
        if(u == null){
            User user = new User();
            user.setId(-1L);
            user.setName("default");
            u = user;
        }
        return u;

    }

    public static void setUser(User user){
        userThreadLocal.set(user);
    }

}
