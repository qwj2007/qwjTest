package com.winterchen.service.rabbitmq.impl;


import com.winterchen.constant.MQConstant;
import com.winterchen.service.rabbitmq.IMessageQueueService;
import com.winterchen.utils.DLXMessage;
import com.winterchen.utils.FastJsonUtils;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("messageQueueService")
public class MessageQueueService implements IMessageQueueService {


    @Autowired
    private AmqpTemplate rabbitTemplate;
    @Override
    public void send( String queueName, String message) {
        rabbitTemplate.convertAndSend(queueName, message);
    }

    @Override
    public void send(String queueName, String message, long times) {
        DLXMessage dlxMessage = new DLXMessage(queueName,message,times);
        MessagePostProcessor processor = new MessagePostProcessor(){
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setExpiration(times + "");
                return message;
            }
        };
        dlxMessage.setExchange(MQConstant.DEFAULT_EXCHANGE);
        rabbitTemplate.convertAndSend(MQConstant.DEFAULT_EXCHANGE,MQConstant.DEFAULT_DEAD_LETTER_QUEUE_NAME, FastJsonUtils.toJSONString(dlxMessage), processor);
        System.out.println("往死消息队列中发送消息");
    }

    @Override
    public void send(String dealQueue, String queueName, String message, long times) {
        DLXMessage dlxMessage = new DLXMessage(queueName,message,times);
        MessagePostProcessor processor = new MessagePostProcessor(){
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setExpiration(times + "");
                return message;
            }
        };
        dlxMessage.setExchange(MQConstant.DEFAULT_EXCHANGE);
        rabbitTemplate.convertAndSend(MQConstant.DEFAULT_EXCHANGE,dealQueue, FastJsonUtils.toJSONString(dlxMessage), processor);
        System.out.println("往死消息队列中发送消息");
    }

    @Override
    public void send(String exchange, String dealQueueName, String queueName, String message, long times) {
        DLXMessage dlxMessage = new DLXMessage(exchange,queueName,message,times);
        MessagePostProcessor processor = new MessagePostProcessor(){
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setExpiration(times + "");
                return message;
            }
        };
        //dlxMessage.setExchange(MQConstant.DEFAULT_EXCHANGE);
        rabbitTemplate.convertAndSend(exchange,dealQueueName, FastJsonUtils.toJSONString(dlxMessage), processor);
        System.out.println("往死消息队列中发送消息");
    }
}
