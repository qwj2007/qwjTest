package com.winterchen.constant;

/**
 * @author victor
 * @desc Rabbit消息队列相关常量
 */
public final class MQConstant {
    private MQConstant() {
    }

    //exchange name
    public static final String DEFAULT_EXCHANGE = "KSHOP";

    //DLX QUEUE
    public static final String DEFAULT_DEAD_LETTER_QUEUE_NAME = "kshop.dead.letter.queue";

    //DLX repeat QUEUE 死信转发队列
    public static final String DEFAULT_REPEAT_TRADE_QUEUE_NAME = "kshop.repeat.trade.queue";

    //Hello 测试消息队列名称
    public static final String HELLO_QUEUE_NAME = "HELLOQUEUES";

    //HELLO消息队列的bindingkey
    public static final String BING_KELLO_QUEUE_KEY = "BING_KELLO_QUEUE_KEY";

    public static final String HI_QUEUE_NAME = "HIQUEUE";


    //TESTQUEUE消息队列名称
    public static final String TEST_QUEUE_NAME = "QUEUE_TEST";
    //TESTQUEUE消息队列交换机
    public static final String TEST_EXCHANGE = "TEST_EXCHANGE";

    public static final String TEST_DEAD_EXCHANGE = "TEST.DEAD.EXCHANGE";
    //死信队列
    public static final String TEST_DEAD_QUEUE_NAME = "TEST.DEAD.QUEUE";
    //
    public static final String TEST_DEAD_QUEUE_ROUTING_KEY = "TEST.DEAD.QUEUE.ROUTING.KEY";


}
