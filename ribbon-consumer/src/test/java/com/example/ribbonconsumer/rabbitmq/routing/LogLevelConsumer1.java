package com.example.ribbonconsumer.rabbitmq.routing;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class LogLevelConsumer1 {

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

        String queue = channel.queueDeclare().getQueue();
        for (String routingkey: routing
             ) {
            channel.queueBind(queue,exchangename,routingkey);
        }

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
