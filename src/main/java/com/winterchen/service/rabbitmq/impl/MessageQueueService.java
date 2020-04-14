package com.winterchen.service.rabbitmq.impl;


import com.alibaba.fastjson.JSON;
import com.winterchen.constant.MQConstant;
import com.winterchen.service.rabbitmq.IMessageQueueService;
import com.winterchen.utils.DLXMessage;
import com.winterchen.utils.FastJsonUtils;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service("messageQueueService")
public class MessageQueueService implements IMessageQueueService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void send(String queueName, String message) {
        rabbitTemplate.convertAndSend(queueName, message);
    }



    @Override
    public void send(String exchange, String queueName, String message) {
        rabbitTemplate.convertAndSend(exchange, queueName, message);
    }



    @Override
    public void send(String sourceQueueName, String distExpireQueueName, String exchange, String msg, long times) {
        DLXMessage dlxMessage = new DLXMessage(sourceQueueName, msg, times);
        MessagePostProcessor processor = new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setExpiration(times + "");
                return message;
            }
        };
        dlxMessage.setExchange(exchange);
        rabbitTemplate.convertAndSend(exchange, distExpireQueueName, JSON.toJSONString(dlxMessage), processor);
    }


    @Override
    public void send(String queueName, String message, long times) {
        DLXMessage dlxMessage = new DLXMessage(queueName, message, times);
        MessagePostProcessor processor = new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setExpiration(times + "");
                return message;
            }
        };
        rabbitTemplate.convertAndSend(queueName, (Object) JSON.toJSONString(dlxMessage), processor);

    }






    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {
        System.out.println("sender return success" + message.toString()+"==="+i+"==="+s1+"==="+s2);

    }
}
