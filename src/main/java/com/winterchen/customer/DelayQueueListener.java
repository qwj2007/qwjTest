package com.winterchen.customer;

import com.winterchen.config.mqconfig.DelayQueueConfiguration;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Component;

/**
 * 测试延时队列，运用“ rabbitmq_delayed_message_exchange”插件，
 * 这个插件根据时间长短处理消息。时间短的先处理。
 * 这个插件需要安装，并启用
 */
@Component
public class DelayQueueListener {
    // 消息转换器

    @RabbitListener(queues = DelayQueueConfiguration.QUEUE_NAME)
    @RabbitHandler
    public void consumer(Message message) {
        System.out.println("延时队列取出的消息："+ new String(message.getBody()));
    }
    //*/
}
