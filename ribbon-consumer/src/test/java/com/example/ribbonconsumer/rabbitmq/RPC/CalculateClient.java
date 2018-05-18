package com.example.ribbonconsumer.rabbitmq.RPC;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

public class CalculateClient {
    public static String replyqueuename;
    public static Channel channel;
    public static String requestQueuename = "rpc_queue";
    public static QueueingConsumer consumer;
    public static Connection connection;

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        initvar();
        String call = call("30");
        System.out.println("调用rpc方法返回的值是： "+call);
        if(connection != null){
            System.out.println("关闭connection");
            connection.close();
        }

    }

    public static void initvar() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("liudongling");
        factory.setPassword("liudongling");
        factory.setHost("192.168.1.108");

        connection = factory.newConnection();

        channel = connection.createChannel();
        replyqueuename = channel.queueDeclare().getQueue();
        consumer = new QueueingConsumer(channel);

        channel.basicConsume(replyqueuename,true,consumer);
    }

    private static String call(String message) throws IOException, InterruptedException {
        String response = null;
        String uuid = UUID.randomUUID().toString();
        AMQP.BasicProperties props = new AMQP.BasicProperties.Builder().correlationId(uuid).replyTo(replyqueuename).build();
        channel.basicPublish("",requestQueuename,props,message.getBytes());
        while(true){
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            if(delivery.getProperties().getCorrelationId().equals(uuid)){
                response = new String(delivery.getBody(),"utf-8");
                break;
            }
        }
        return response;
    }
}
