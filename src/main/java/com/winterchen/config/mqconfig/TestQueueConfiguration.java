package com.winterchen.config.mqconfig;

import com.winterchen.constant.MQConstant;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 作者：qiwj
 * 时间：2020/4/12
 * 测试rabbitmq消息队列，包括延时队列
 */
@Configuration
public class TestQueueConfiguration {


    /**以下是死信消息队列的定义方法**/
    /**
     *创建业务交换机
     * */
    @Bean
    public DirectExchange testExchange() {
        return new DirectExchange(MQConstant.TEST_EXCHANGE, true, false);
    }

    /**
     * 正常的业务队列,生产者网这个消息队列发送消息，时间过期后会转换到死信队列中处理。
     *
     * @return
     */
    @Bean
    public Queue businessQueue() {
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-dead-letter-exchange", MQConstant.TEST_DEAD_EXCHANGE);////设置死信交换机
        arguments.put("x-dead-letter-routing-key", MQConstant.TEST_DEAD_QUEUE_ROUTING_KEY);////设置死信routingKey
        //arguments.put("x-message-ttl", 30000);//消息过期时间
        Queue queue = new Queue(MQConstant.TEST_QUEUE_NAME, true, false, false, arguments);
        System.out.println("arguments :" + queue.getArguments());
        return queue;
    }

    /**
     *
     *绑定业务队列和交换机，指定routingKey
     */
    @Bean
    public Binding businessQueueBinding() {
        return BindingBuilder.bind(businessQueue()).to(testExchange()).with(MQConstant.TEST_QUEUE_NAME);
    }


    /**
     * 一下是业务消息队列消息过期后，转发到死信队列
     */

    /**
     * 创建死信队列,业务队列消息过期后会把消息转送到这个队列
     */
    @Bean
    public Queue testDeadQueue() {
        Queue queue = new Queue(MQConstant.TEST_DEAD_QUEUE_NAME, true);
        return queue;
    }
    /**
     * 创建死信交换机，交换机名称一定要和在声明业务队列的时候定义的死信交换机的名称一致
     * @return
     */
    @Bean
    public DirectExchange deadExchange() {
        return new DirectExchange(MQConstant.TEST_DEAD_EXCHANGE, true, false);
    }
    /**
     * 绑定死信队列和死信交换机
     * @return
     */
    @Bean
    public Binding deadBinding() {
        return BindingBuilder.bind(testDeadQueue()).to(deadExchange())
                .with(MQConstant.TEST_DEAD_QUEUE_ROUTING_KEY);//routingKey和创建业务队列时候创建“x-dead-letter-routing-key”的值对应。
    }

/***************死信消息队列的定义方式完成****************************/


    /**以下是不用死信消息队列的定义方法**/
    /*******Hello消息队列******/
    @Bean
    public Queue helloQueue() {
        Queue queue = new Queue(MQConstant.HELLO_QUEUE_NAME, true);
        return queue;
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(helloQueue()).to(defaultExchange()).with(MQConstant.BING_KELLO_QUEUE_KEY);
    }

    @Bean
    public DirectExchange defaultExchange() {
        return new DirectExchange(MQConstant.DEFAULT_EXCHANGE, true, false);
    }


}
