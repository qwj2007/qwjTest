package com.winterchen.customer;

import com.winterchen.constant.MQConstant;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 作者：qiwj
 * 时间：2020/4/12
 */
@Component
//消息队列消费
public class QueueCustomer {

    @RabbitListener(queues = MQConstant.TEST_QUEUE_NAME)
    @RabbitHandler
    public void process(String body) {
        String bod=body;
        System.out.println(bod+ " 第一个方法");
    }
    @RabbitListener(queues = MQConstant.TEST_QUEUE_NAME)
    @RabbitHandler
    public void process1(String body) {
        String bod=body;
        System.out.println(bod+" 第二个方法");
    }

    @RabbitListener(queues = MQConstant.TEST_DEAD_QUEUE_NAME)
    @RabbitHandler
    public void process2(String body) {
        String bod=body;
        System.out.println(bod+" 私信队列第一个方法");
    }
    @RabbitListener(queues = MQConstant.TEST_DEAD_QUEUE_NAME)
    @RabbitHandler
    public void process3(String body) {
        String bod=body;
        System.out.println(bod+" 私信队列第二个方法");
    }
/*
    @RabbitListener(queues = "dead")
    @RabbitHandler
    public void processDeadQueue(String body) {
        String bod=body;
        System.out.println(bod+" 死信队列转发队列方法"+"dead");
    }

    @RabbitListener(queues = "mailQueue")
    @RabbitHandler
    public void processDeadQueue1(String body) {
        String bod=body;
        System.out.println(bod+" 死信队列转发队列方法mailQueue");
    }
    //*/
}
