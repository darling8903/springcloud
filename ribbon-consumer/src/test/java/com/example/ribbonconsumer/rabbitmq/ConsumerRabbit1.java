package com.example.ribbonconsumer.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConsumerRabbit1 {
    public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("liudongling");
        factory.setPassword("liudongling");
        factory.setHost("192.168.1.108");

        Connection connection = factory.newConnection();

        Channel channel = connection.createChannel();

        String quequename = "hello-exchange";

        channel.queueDeclare(quequename,true,false,false,null);
        channel.basicQos(1);

        final Consumer consumer = new DefaultConsumer(channel){

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String string = new String(body,"utf-8");
                System.out.println("我是第一个工作者："+string);
                dowork();
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        };

        channel.basicConsume(quequename,false,consumer);

        //channel.close();
        //connection.close();
    }

    private static void dowork(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
