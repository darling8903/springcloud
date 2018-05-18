package com.example.ribbonconsumer.rabbitmq.publishsubscribe;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class LogConsumer1 {

    public static final String exchangename="logs";
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("liudongling");
        factory.setPassword("liudongling");
        factory.setHost("192.168.1.108");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(exchangename,"fanout");
        String queue = channel.queueDeclare().getQueue();

        channel.queueBind(queue,exchangename,"");

        Consumer consumer = new DefaultConsumer(channel){

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body,"utf-8");
                System.out.println("我是日志消费者1----"+message);
            }
        };
        channel.basicConsume(queue,true,consumer);

    }
}
