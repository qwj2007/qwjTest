package com.winterchen.customer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
//@RabbitListener(queues = "queuesdemo")
public class Reciver {
    @RabbitHandler
    public void process(String hello)
    {
        System.out.println("Receiver  : 第一个消费者" + hello+"条数据");
    }



}
