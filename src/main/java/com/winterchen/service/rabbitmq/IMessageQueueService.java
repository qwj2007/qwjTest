package com.winterchen.service.rabbitmq;

public interface IMessageQueueService {
    /**
     * excahnge:交换机名称
     * queueName:队列名称
     * message:发送消息
    * */
    void send(String queueName, String message);

    /**
     * 延迟发送消息到队列
     * @param queue 队列名称
     * @param message 消息内容
     * @param times 延迟时间 单位毫秒
     */
     void send(String queueName, String message, long times);

    /**
     * 延迟发送消息到队列
     *  @param dealQueueName 死消息队列名称
     * @param queue 队列名称
     * @param message 消息内容
     * @param times 延迟时间 单位毫秒
     */
    void send(String dealQueueName, String queueName, String message, long times);

    /**
     * 延迟发送消息到队列
     *  @param exchange 交换机
     *  @param dealQueueName 死消息队列名称
     * @param queue 队列名称
     * @param message 消息内容
     * @param times 延迟时间 单位毫秒
     */
    void send(String exchange, String dealQueueName, String queueName, String message, long times);
}
