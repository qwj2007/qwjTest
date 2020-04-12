package com.winterchen.customer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
//@RabbitListener(queues = "queuesdemo")
public class Reciver2 {
    @RabbitHandler
    public void getHello(String hello)
    {
        System.out.println("Receiver  getHello: 第三个消费者" + hello+"条数据");
    }

}
