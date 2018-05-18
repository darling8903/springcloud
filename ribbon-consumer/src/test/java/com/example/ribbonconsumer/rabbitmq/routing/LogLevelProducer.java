package com.example.ribbonconsumer.rabbitmq.routing;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class LogLevelProducer {
    public static final String[] routing = new String[]{"debug","info","warning"};

    public static final String exchangename = "direct_log";
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("liudongling");
        factory.setPassword("liudongling");
        factory.setHost("192.168.1.108");

        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();

        channel.exchangeDeclare(exchangename,"direct");

        for (String routingkey :routing) {
            String message = "我的日志级别是："+routingkey;
            channel.basicPublish(exchangename,routingkey,null,message.getBytes());
        }

        channel.close();
        connection.close();
    }
}
