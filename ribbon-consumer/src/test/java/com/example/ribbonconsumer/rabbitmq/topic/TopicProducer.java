package com.example.ribbonconsumer.rabbitmq.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class TopicProducer {
    public static final String exchangename = "topic_test";

    public static final String[] routing = new String[]{
            "lazy.orange.elephant",
            "quick.orange.rabbit",
            "quick.orange.fox",
            "lazy.brown.fox",
            "quick.brown.fox",
            "quick.orange.male,rabbit",
            "lazy.orange.male.rabbit",
    };
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("liudongling");
        factory.setPassword("liudongling");
        factory.setHost("192.168.1.108");

        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();

        channel.exchangeDeclare(exchangename,"topic");


        for (String routingkey: routing
             ) {
            String message = "from "+routingkey+ "routingkey message";
            channel.basicPublish(exchangename,routingkey,null,message.getBytes());

        }
        channel.close();
        connection.close();
    }
}
