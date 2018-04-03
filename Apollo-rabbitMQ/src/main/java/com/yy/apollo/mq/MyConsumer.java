package com.yy.apollo.mq;

import java.io.IOException;

import org.apache.commons.lang3.SerializationUtils;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;

public class MyConsumer implements Runnable , Consumer{

    @Override
    public void run() {
        try {
            channel.basicConsume(queue, true, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private Channel channel;
    public MyConsumer(Channel channel, String queue) {
        super();
        this.channel = channel;
        this.queue = queue;
    }

    private String queue;
    @Override
    public void handleConsumeOk(String consumerTag) {
        System.out.println("Consumer: "+ consumerTag);
    }
    @Override
    public void handleCancelOk(String consumerTag) {
        
    }
    @Override
    public void handleCancel(String consumerTag) throws IOException {
        
    }
    @Override
    public void handleDelivery(String arg0, Envelope arg1, BasicProperties arg2, byte[] arg3) throws IOException {
        System.out.println(Thread.currentThread()+":"+"Consumer消费"+SerializationUtils.deserialize(arg3));
    }
    @Override
    public void handleShutdownSignal(String consumerTag, ShutdownSignalException sig) {
        
    }
    @Override
    public void handleRecoverOk(String consumerTag) {
        
    }

}
