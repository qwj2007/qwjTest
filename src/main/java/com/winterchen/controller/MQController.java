package com.winterchen.controller;

import com.winterchen.constant.MQConstant;
import com.winterchen.service.rabbitmq.IMessageQueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("mq")
public class MQController {
    @Autowired
    private IMessageQueueService messageQueueService;
    @RequestMapping("/que")
    public void  setmqq()
    {
        for(int i=0;i<2000;i++)
        {
            messageQueueService.send(MQConstant.HI_QUEUE_NAME,"你好"+i);
            System.out.println("你好"+i);
        }
    }

    @RequestMapping("/queues")
    public void  setQueuesInfo()
    {
        for(int i=0;i<100000;i++)
        {
            messageQueueService.send(MQConstant.HELLO_QUEUE_NAME,"你好"+i);
            System.out.println("你好"+i);
        }

    }
    @RequestMapping("/dead")
    public void deadQueue() throws InterruptedException
    {
        //3种方式都可以
        //往死信队列中发送消息，如果超过1分钟，消息就会发送到现在的HELLO_QUEUE_NAME 对列中
        for(int i=0;i<20;i++)
        {
            messageQueueService.send(MQConstant.DEFAULT_EXCHANGE,MQConstant.DEFAULT_DEAD_LETTER_QUEUE_NAME,MQConstant.HELLO_QUEUE_NAME,"测试延迟发送消息"+i,60000);
            //Thread.sleep(2000);
        }

    }
}
