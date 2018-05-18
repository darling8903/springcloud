package com.example.ribbonconsumer.rabbitmq.RPC;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class CalculateService {
    public static final String rpc_queue_name = "rpc_queue";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("liudongling");
        factory.setPassword("liudongling");
        factory.setHost("192.168.1.108");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        try{
            //String quequename = "hello-exchange";
            //channel.queueDeclare(quequename,false,false,false,null);

            channel.queueDeclare(rpc_queue_name,false,false,false,null);
            channel.basicQos(1);

            QueueingConsumer consumer = new QueueingConsumer(channel);
            channel.basicConsume(rpc_queue_name,consumer);

            while (true){
                String response = null;

                QueueingConsumer.Delivery delivery = consumer.nextDelivery();
                BasicProperties properties = delivery.getProperties();
                AMQP.BasicProperties replyproperties = new AMQP.BasicProperties.Builder().correlationId(
                        properties.getCorrelationId()).build();

                String message = new String(delivery.getBody(),"utf-8");
                int i = Integer.parseInt(message);

                response = "我是被远程调用的方法------"+i;

                channel.basicPublish("",properties.getReplyTo(),replyproperties,response.getBytes());
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);

            }
        }catch (Exception e){
            if(connection != null){
                connection.close();

            }
        }

    }
}
