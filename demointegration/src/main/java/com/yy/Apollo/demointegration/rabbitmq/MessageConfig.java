package com.yy.Apollo.demointegration.rabbitmq;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfig {

    // 交换机
    private static String EXCHANGE = "DirectExchange";

    public static final String QUEUENAME1 = "queue1";
    public static final String QUEUENAME2 = "queue2";

    public static String getEXCHANGE() {
        return EXCHANGE;
    }

    public static void setEXCHANGE(String eXCHANGE) {
        EXCHANGE = eXCHANGE;
    }

    /**
     * 设置对了名为queue1的队列
     * 
     * @return
     */
    @Bean("queue1")
    public Queue queue1() {
        return new Queue(QUEUENAME1, false);// 队列不持久
    }

    /**
     * 设置对了名为queue2的队列
     * 
     * @return
     */
    @Bean("queue2")
    public Queue queue2() {
        return new Queue("queue2", false);// 队列不持久
    }

    /**
     * 配置交换机,这里是使用精确匹配方式
     * 
     * @return
     */
    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(EXCHANGE, false, false);
    }

    /**
     * 绑定交换机与队列，下列配置的意思是，如果到达交换机DirectExchange的消息中routeKey=qu.1，则把这条消息放入队列queue1
     * 
     * @param queue
     * @param exchange
     * @return
     */
    @Bean
    public Binding binding1(@Qualifier(QUEUENAME1) Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("qu.1"); // 如果消息的路由Key为qu.1，则交换机将消息发送到队列1
    }

    /**
     * 绑定交换机与队列，下列配置的意思是，如果到达交换机DirectExchange的消息中routeKey=qu.2，则把这条消息放入队列queue2
     * 
     * @param queue
     * @param exchange
     * @return
     */
    @Bean
    public Binding binding2(@Qualifier(QUEUENAME2) Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("qu.2"); // 如果消息的路由Key为qu.2，则交换机将消息发送到队列2
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
            MessageListenerAdapter listenerAdapter) {
        // 生成一个Listener的容器
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        // 使用springboot自动生成的connection工厂
        container.setConnectionFactory(connectionFactory);
        // 这个container负责监听queue1与quue2消息
        container.setQueueNames(QUEUENAME1, QUEUENAME2);
        // 其实我们的listener只是一个pojo类，需要listenerAdapter来让我们在pojo中定义的方法来执行。如果使用@RabbitListener可以不用设定。
        container.setMessageListener(listenerAdapter);
        return container;
    }

    /**
     * 这是代码配置listenerAdapter，可以使用RabbitListener代替本步骤
     * 
     * @param messageRcv
     * @return
     */
    @Bean
    MessageListenerAdapter listenerAdapter(MessageRcv messageRcv) {
        MessageListenerAdapter adapter = new MessageListenerAdapter(messageRcv);
        Map<String, String> queueOrTagToMethodName = new HashMap<>();
        queueOrTagToMethodName.put(QUEUENAME1, "onMessageQueue1");
        queueOrTagToMethodName.put(QUEUENAME2, "onMessageQueue2");
        adapter.setQueueOrTagToMethodName(queueOrTagToMethodName);
        return adapter;
    }

}
