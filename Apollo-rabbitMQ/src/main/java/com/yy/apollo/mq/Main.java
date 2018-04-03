package com.yy.apollo.mq;

import com.rabbitmq.client.Channel;

public class Main {
    public static void main(String[] args) {
        Channel send = ChannelFactory.getChannel("Apollo");
        Channel client = ChannelFactory.getChannel("Apollo"); 
        Channel client2 = ChannelFactory.getChannel("Apollo"); 
        Producer producer = new Producer(send);
        MyConsumer consumer = new MyConsumer(client,"Apollo");
        MyConsumer consumer2 = new MyConsumer(client2,"Apollo");
        
        Thread thread1 = new Thread(consumer,"11:");
        Thread thread2 = new Thread(consumer2,"22:");
        thread1.start();
        thread2.start();
        
        for(int i = 0;i<10000;i++) {
            producer.sendMessage("Apollo", "消息"+i);
            System.out.println("发送消息"+i);
        }
    }
}
