package com.example.ribbonconsumer.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("liudongling");
        factory.setPassword("liudongling");
        factory.setHost("192.168.1.108");

        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();

        String quequename = "hello-exchange";

        //channel.queueDeclare(quequename,false,false,false,null);

        channel.queueDeclare(quequename,true,false,false,null);

        for (int i = 1; i < 10; i++) {
            String message = "hello world";
            message = message+i;
            channel.basicPublish("",quequename, MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes());

        }

        channel.close();
        connection.close();
    }
}
