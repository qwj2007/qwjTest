package com.winterchen.config.mqconfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Configuration;

/**
 * 作者：qiwj
 * 时间：2020/4/13
 *
 * 测试延时队列，运用“ rabbitmq_delayed_message_exchange”插件，
 * 这个插件根据时间长短处理消息。时间短的先处理。
 * 这个插件需要安装，并启用
 *
 */
@Configuration
public class DelayQueueConfiguration {
    public static final String QUEUE_NAME = "delay_queue";

    public static final String EXCHANGE_NAME = "delay_exchange";
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME, true);
    }

    // 定义一个延迟交换机
    @Bean
    public CustomExchange delayExchange() {
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("x-delayed-type", "direct");
        return new CustomExchange(EXCHANGE_NAME, "x-delayed-message", true, false, args);
    }

    // 绑定队列到这个延迟交换机上
    @Bean
    public Binding delayBinding() {
        return BindingBuilder.bind(queue()).to(delayExchange()).with(QUEUE_NAME).noargs();
    }
}
