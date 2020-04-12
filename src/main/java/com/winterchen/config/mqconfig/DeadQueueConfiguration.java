package com.winterchen.config.mqconfig;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Binding;
import java.util.HashMap;
import java.util.Map;
import org.springframework.amqp.core.Queue;
/**
 * 作者：qiwj
 * 时间：2020/4/12
 * rabbitmq的死信队列应用
 *
 #####定义业务（普通）队列的时候指定参数
 x-dead-letter-exchange: 用来设置死信后发送的交换机
 x-dead-letter-routing-key：用来设置死信的routingKey
 */
@Configuration
public class DeadQueueConfiguration {

    /**
    *创建业务队列,生产者网这个消息队列发送消息，时间过期后会转换到死信队列中处理。
    * */
    @Bean
    public Queue mailQueue() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("x-dead-letter-exchange", "dead_letter_exchange");//设置死信交换机
        map.put("x-dead-letter-routing-key", "mail_queue_fail");//设置死信routingKey
        map.put("x-message-ttl", 10000);//消息过期时间
        Queue queue = new Queue("mailQueue",true, false, false, map);
        return queue;
    }
/**
 *创建业务交换机
 * */
    @Bean
    public DirectExchange mailExchange() {
        return new DirectExchange("mailExchange", true, false);
    }
/**
 *绑定业务队列和交换机，指定routingKey
 */
    @Bean
    public Binding mailBinding() {
        return BindingBuilder.bind(mailQueue()).to(mailExchange())
                .with("mailRoutingKey");
    }


    /**
     * 创建死信交换机，交换机名称一定要和在声明业务队列的时候定义的死信交换机的名称一致
     * @return
     */
    @Bean
    public DirectExchange deadExchange() {
        return new DirectExchange("dead_letter_exchange", true, false);
    }
    /**
     * 创建死信队列,业务队列消息过期后会把消息转送到这个队列
     */
    @Bean
    public Queue deadQueue(){
        Queue queue = new Queue("dead_queue", true);
        return queue;
    }

    /**
     * 绑定死信队列和死信交换机
     * @return
     */
    @Bean
    public Binding deadBinding() {
        return BindingBuilder.bind(deadQueue()).to(deadExchange())
                .with("mail_queue_fail");//routingKey和创建业务队列时候创建“x-dead-letter-routing-key”的值对应。
    }
}
