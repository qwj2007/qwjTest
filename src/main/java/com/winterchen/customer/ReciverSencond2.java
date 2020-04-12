package com.winterchen.customer;

import com.winterchen.constant.MQConstant;
import com.winterchen.service.world.ICityService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
//@RabbitListener(queues = MQConstant.HI_QUEUE_NAME)
public class ReciverSencond2 {
    @Autowired
    private ICityService cityService;

    @RabbitHandler
    public void getHello(String hello)
    {
        //cityService.updates();
        System.out.println("Receiver  getHello: 第二个方法第三个消费者" + hello+"条数据");
    }

}
