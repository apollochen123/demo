package com.yy.Apollo.demointegration.rabbitmq;

import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MessageSend {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMsg(String message, String queue) {
        String uuid = UUID.randomUUID().toString();
        CorrelationData correlationId = new CorrelationData(uuid);
        if (queue.equals("1")) {
            rabbitTemplate.convertAndSend(MessageConfig.getEXCHANGE(), "qu.1", message, correlationId);
        } else {
            rabbitTemplate.convertAndSend(MessageConfig.getEXCHANGE(), "qu.2", message, correlationId);
        }

    }
}
