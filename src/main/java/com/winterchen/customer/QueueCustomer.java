package com.winterchen.customer;

import com.winterchen.constant.MQConstant;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.amqp.core.Message;
import com.rabbitmq.client.Channel;
import java.io.IOException;

/**
 * 作者：qiwj
 * 时间：2020/4/12
 */
@Component
//消息队列消费
public class QueueCustomer {
/*
    @RabbitListener(queues = MQConstant.TEST_QUEUE_NAME)
    @RabbitHandler
    public void process(Channel channel,String msg, Message message) throws IOException  {

        System.out.println("收到消息  : " +msg);
        try {
            //告诉服务器收到这条消息 已经被我消费了 可以在队列删掉 这样以后就不会再发了 否则消息服务器以为这条消息没处理掉 后续还会在发
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
            System.out.println("接收成功");
        } catch (IOException e) {
            e.printStackTrace();
            //丢弃这条消息
            //channel.basicNack(message.getMessageProperties().getDeliveryTag(), false,false);
            System.out.println("receiver fail");
        }

        //String bod=body;
        //System.out.println(bod+ " 第一个方法");
    }//*/

    @RabbitListener(queues = MQConstant.TEST_QUEUE_NAME)
    @RabbitHandler
    public void process1(String body) {
        String bod=body;
        System.out.println(bod+" 第二个方法");
    }
    @RabbitListener(queues = MQConstant.TEST_QUEUE_NAME)
    @RabbitHandler
    public void process11(String body) {
        String bod=body;
        System.out.println(bod+" 第一个方法");
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

    @RabbitListener(queues = "dead_queue")
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
