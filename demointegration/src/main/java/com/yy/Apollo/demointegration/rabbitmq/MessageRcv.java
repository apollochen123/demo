package com.yy.Apollo.demointegration.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageRcv {

    public void onMessageQueue1(String message) {
        System.out.println("Queue1收到消息 : " + new String(message));
    }
    
    public void onMessageQueue2(String message) {
        System.out.println("Queue2收到消息 : " + new String(message));
    }
    
    @RabbitListener(queues = MessageConfig.QUEUENAME1)
    public void processMessage(String content) {
        System.out.println("@RabbitListener:"+content);
    }
    
    @RabbitListener(queues = MessageConfig.QUEUENAME2)
    public void processMessage2(String content) {
        System.out.println("@RabbitListener2:"+content);
    }

}
