package com.example.ribbonconsumer.rabbitmq.publishsubscribe;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class LogProducer {

    public static final String exchangename="logs";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("liudongling");
        factory.setPassword("liudongling");
        factory.setHost("192.168.1.108");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(exchangename,"fanout");

        for (int i = 0; i < 5; i++) {
            String message = "hello -------"+i;
            channel.basicPublish(exchangename,"",null,message.getBytes());
        }
        channel.close();
        connection.close();

    }
}
