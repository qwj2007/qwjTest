package com.winterchen.controller;

import com.winterchen.config.mqconfig.DelayQueueConfiguration;
import com.winterchen.constant.MQConstant;
import com.winterchen.service.rabbitmq.IMessageQueueService;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;


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



    /**
     * 测试正常队列和死信队列
     *
     * @return
     */
    @RequestMapping(value = "/setmq", method = {RequestMethod.GET})
    @ResponseBody
    public String SetMq() {
        // messageQueueService.send(MQConstant.TEST_DEAD_QUEUE_NAME, MQConstant.TEST_QUEUE_NAME, MQConstant.TEST_EXCHANGE, "测试延时队列", 30000);

        for (int i = 0; i < 100; i++) {

            //rabbitTemplate.convertAndSend(MQConstant.TEST_QUEUE_NAME, "测试死信队列" + i);
            //messageQueueService.send("mailQueue","dfdsfdsfsdfsdfsdfsdfsd");
            messageQueueService.send(MQConstant.TEST_QUEUE_NAME,"测试死信队列",10000);
            // messageQueueService.sendByAck(MQConstant.TEST_DEAD_QUEUE_NAME, MQConstant.TEST_QUEUE_NAME, MQConstant.TEST_EXCHANGE, "dfdfdsfsdfds", 10000);
        }
        return "0k";
    }

    /**
     * 测试延时队列，运用“ rabbitmq_delayed_message_exchange”插件，这个插件根据时间长短处理消息。时间短的先处理。
     */
    @RequestMapping(value = "/setToDelayMQ", method = {RequestMethod.GET})
    @ResponseBody
    public void setToDelayMQ() {

        for (int i = 0; i < 100; i++) {
            MessageProperties properties = new MessageProperties();
            properties.setDelay(30000);
            Message message = new Message("测试延迟队列20秒的".getBytes(), properties);
            rabbitTemplate.send(DelayQueueConfiguration.EXCHANGE_NAME, DelayQueueConfiguration.QUEUE_NAME, message);
        }

        for (int i = 0; i < 20; i++) {
            MessageProperties properties = new MessageProperties();
            properties.setDelay(10000);
            Message message = new Message("测试延迟队列10秒的".getBytes(), properties);
            rabbitTemplate.send(DelayQueueConfiguration.EXCHANGE_NAME, DelayQueueConfiguration.QUEUE_NAME, message);
        }
        /*输出结果,根据时间长短输出*/
        /*
延时队列取出的消息：测试延迟队列10秒的
延时队列取出的消息：测试延迟队列10秒的
延时队列取出的消息：测试延迟队列10秒的
延时队列取出的消息：测试延迟队列10秒的
延时队列取出的消息：测试延迟队列10秒的
延时队列取出的消息：测试延迟队列10秒的
延时队列取出的消息：测试延迟队列10秒的
延时队列取出的消息：测试延迟队列10秒的
延时队列取出的消息：测试延迟队列10秒的
延时队列取出的消息：测试延迟队列10秒的
延时队列取出的消息：测试延迟队列10秒的
延时队列取出的消息：测试延迟队列10秒的
延时队列取出的消息：测试延迟队列10秒的
延时队列取出的消息：测试延迟队列10秒的
延时队列取出的消息：测试延迟队列10秒的
延时队列取出的消息：测试延迟队列10秒的
延时队列取出的消息：测试延迟队列10秒的
延时队列取出的消息：测试延迟队列10秒的
延时队列取出的消息：测试延迟队列10秒的
延时队列取出的消息：测试延迟队列10秒的
延时队列取出的消息：测试延迟队列20秒的
延时队列取出的消息：测试延迟队列20秒的
延时队列取出的消息：测试延迟队列20秒的
延时队列取出的消息：测试延迟队列20秒的
延时队列取出的消息：测试延迟队列20秒的
延时队列取出的消息：测试延迟队列20秒的
延时队列取出的消息：测试延迟队列20秒的
延时队列取出的消息：测试延迟队列20秒的
延时队列取出的消息：测试延迟队列20秒的
延时队列取出的消息：测试延迟队列20秒的
延时队列取出的消息：测试延迟队列20秒的
延时队列取出的消息：测试延迟队列20秒的
延时队列取出的消息：测试延迟队列20秒的
延时队列取出的消息：测试延迟队列20秒的
延时队列取出的消息：测试延迟队列20秒的
延时队列取出的消息：测试延迟队列20秒的
延时队列取出的消息：测试延迟队列20秒的
延时队列取出的消息：测试延迟队列20秒的
延时队列取出的消息：测试延迟队列20秒的
延时队列取出的消息：测试延迟队列20秒的
延时队列取出的消息：测试延迟队列20秒的
延时队列取出的消息：测试延迟队列20秒的
延时队列取出的消息：测试延迟队列20秒的
延时队列取出的消息：测试延迟队列20秒的
延时队列取出的消息：测试延迟队列20秒的
延时队列取出的消息：测试延迟队列20秒的
延时队列取出的消息：测试延迟队列20秒的
延时队列取出的消息：测试延迟队列20秒的
延时队列取出的消息：测试延迟队列20秒的
延时队列取出的消息：测试延迟队列20秒的
延时队列取出的消息：测试延迟队列20秒的
延时队列取出的消息：测试延迟队列20秒的
延时队列取出的消息：测试延迟队列20秒的
延时队列取出的消息：测试延迟队列20秒的
延时队列取出的消息：测试延迟队列20秒的
延时队列取出的消息：测试延迟队列20秒的
延时队列取出的消息：测试延迟队列20秒的
延时队列取出的消息：测试延迟队列20秒的
延时队列取出的消息：测试延迟队列20秒的
延时队列取出的消息：测试延迟队列20秒的
延时队列取出的消息：测试延迟队列20秒的
延时队列取出的消息：测试延迟队列20秒的
延时队列取出的消息：测试延迟队列20秒的
延时队列取出的消息：测试延迟队列20秒的
延时队列取出的消息：测试延迟队列20秒的
延时队列取出的消息：测试延迟队列20秒的
延时队列取出的消息：测试延迟队列20秒的
延时队列取出的消息：测试延迟队列20秒的
延时队列取出的消息：测试延迟队列20秒的
延时队列取出的消息：测试延迟队列20秒的
延时队列取出的消息：测试延迟队列20秒的
延时队列取出的消息：测试延迟队列20秒的
延时队列取出的消息：测试延迟队列20秒的
延时队列取出的消息：测试延迟队列20秒的
延时队列取出的消息：测试延迟队列20秒的
延时队列取出的消息：测试延迟队列20秒的
延时队列取出的消息：测试延迟队列20秒的
延时队列取出的消息：测试延迟队列20秒的
延时队列取出的消息：测试延迟队列20秒的
延时队列取出的消息：测试延迟队列20秒的
延时队列取出的消息：测试延迟队列20秒的
延时队列取出的消息：测试延迟队列20秒的
         */

    }

}
