package com.example.ribbonconsumer.rabbitmq.routing;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class LogLevelConsumer2 {

    public static final String[] routing = new String[]{"info"};
    public static final String exchangename = "direct_log";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("liudongling");
        factory.setPassword("liudongling");
        factory.setHost("192.168.1.108");

        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();

        channel.exchangeDeclare(exchangename,"direct");

        String queue = channel.queueDeclare().getQueue();

        channel.queueBind(queue,exchangename,routing[0]);


        Consumer consumer = new DefaultConsumer(channel){

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body,"utf-8");
                System.out.println(message);
            }
        };

        channel.basicConsume(queue,true,consumer);
    }
}
