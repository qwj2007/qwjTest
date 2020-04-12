package com.winterchen.service.rabbitmq;

public interface IMessageQueueService {
    /**
     * 发送消息到队列
     * @param queue 队列名称
     * @param message 消息内容
     */
     void send( String queueName, String message);

    /**
     * 发送消息到队列
     * @param queue 队列名称
     * @param message 消息内容
     */
     void send(String exchange,String queueName,String message);

    /**
     * 延迟发送消息到队列
     * @param deadQueueName 死信队列名称
     * @param businessQueueName 业务队列名称
     * @param businessQueueExchange 业务队列交换机名称
     * @param msg 发送的消息
     * @param times 消息过期时间
     */
     void send(String deadQueueName,String businessQueueName,String businessQueueExchange, String msg, long times);

    /**
     * 延迟发送消息到队列
     * @param businessQueueName 业务队列名称
     * @param message 发送的消息
     * @param times 消息过期时间
     */
     void send( String businessQueueName, String message, long times);




}
