package com.itclub.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rabbitmq配置  直连交换机
 * 队列与交换机的绑定，必须指定消息的routing key
 * @Author : onePiece
 **/
@Configuration
public class DirectRabbitConfig {

    @Bean
    public Queue directQueue() {
        /** durable:是否持久化,默认是false,持久化队列：会被存储在磁盘上，当消息代理重启时仍然存在，暂存队列：当前连接有效
         exclusive:默认也是false，只能被当前创建的连接使用，而且当连接关闭后队列即被删除。此参考优先级高于durable
         autoDelete:是否自动删除，当没有生产者或者消费者使用此队列，该队列会自动删除。*/
        //一般设置一下队列的持久化就好,其余两个就是默认false
        return new Queue("DirectQueue", true,false,false);
    }

    @Bean
    DirectExchange directExchange() {
        return new DirectExchange("DirectExchange", true, false);
    }

    @Bean
    Binding bindingDirect() {
        return BindingBuilder.bind(directQueue()).to(directExchange()).with("DirectRouting");
    }

}