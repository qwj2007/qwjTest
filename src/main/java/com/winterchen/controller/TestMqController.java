package com.winterchen.controller;

import com.winterchen.constant.MQConstant;
import com.winterchen.service.rabbitmq.IMessageQueueService;
import freemarker.template.Template;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.PrimitiveIterator;

/**
 * 作者：qiwj
 * 时间：2020/4/12
 */
@RequestMapping("/mq")
@Controller
public class TestMqController {
    @Autowired
    private IMessageQueueService messageQueueService;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping(value = "/setmq", method = {RequestMethod.GET})
    @ResponseBody
    public String SetMq() {
        for (int i = 0; i < 100; i++) {

            //rabbitTemplate.convertAndSend("mailQueue", "测试死信队列" + i);
            //messageQueueService.send("mailQueue","dfdsfdsfsdfsdfsdfsdfsd");
            //messageQueueService.send(MQConstant.TEST_QUEUE_NAME,"测试死信队列",10000);
            messageQueueService.send(MQConstant.TEST_DEAD_QUEUE_NAME, MQConstant.TEST_QUEUE_NAME, MQConstant.TEST_EXCHANGE, "dfdfdsfsdfds", 10000);
        }
        return "0k";
    }
}
