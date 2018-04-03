package com.yy.apollo.mq;

import java.io.IOException;

import org.apache.commons.lang.SerializationUtils;

import com.rabbitmq.client.Channel;

public class Producer {

    private Channel channel;

    public void sendMessage(String queue,String message) {
        try {
            channel.basicPublish("", queue, null, SerializationUtils.serialize(message));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Producer(Channel channel) {
        super();
        this.channel = channel;
    }
}
