package com.itclub.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;


/**
 * 延迟队列
 * @Author : onePiece
 */
@Configuration
public class TtlQueueConfig {

    /**
     * 普通交换机名称
     */
    public static final String X_EXCHANGE = "X";

    /**
     * 死信交换机名称
     */
    public static final String Y_DEAD_LETTER_EXCHANGE = "Y";
    /**
     * 普通队列名称
     */
    public static final String QUEUE_A = "QA";
    public static final String QUEUE_B = "QB";

    /**
     * 死信队列名称
     */
    public static final String DEAD_LETTER_QUEUE = "QD";

    public static final String DEAD_LETTER_QUEUE_KEY = "YD";

    /**
     * 声明 XExchange
     */
    @Bean
    public DirectExchange xExchange(){
        return new DirectExchange(X_EXCHANGE);
    }

    /**
     * 声明 yExchange
     */
    @Bean
    public DirectExchange yExchange(){
        return new DirectExchange(Y_DEAD_LETTER_EXCHANGE);
    }

    /**
     * 声明队列QA
     */
    @Bean
    public Queue queueA(){
        Map<String, Object> arguments = new HashMap<>(3);
        // 设置死信交换机
        arguments.put("x-dead-letter-exchange", Y_DEAD_LETTER_EXCHANGE);
        // 设置死信路由键
        arguments.put("x-dead-letter-routing-key", DEAD_LETTER_QUEUE_KEY);
        // 设置过期时间
        arguments.put("x-message-ttl", 2000);
        return new Queue(QUEUE_A, true, true, false, arguments);
    }

    /**
     * 声明队列QB
     */
    @Bean
    public Queue queueB(){
        Map<String, Object> arguments = new HashMap<>(3);
        // 设置死信交换机
        arguments.put("x-dead-letter-exchange", Y_DEAD_LETTER_EXCHANGE);
        // 设置死信路由键
        arguments.put("x-dead-letter-routing-key", DEAD_LETTER_QUEUE_KEY);
        // 设置过期时间
        arguments.put("x-message-ttl", 4000);
        return new Queue(QUEUE_B, true, true, false, arguments);
    }

    /**
     * 死信队列QD
     */
    @Bean
    public Queue queueD(){
        return QueueBuilder.durable(DEAD_LETTER_QUEUE).build();
    }


    /**
     * 绑定交换机
     */
    @Bean
    public Binding queueABindingX(){
        return BindingBuilder.bind(queueA()).to(xExchange()).with("XA");
    }

    @Bean
    public Binding queueBBindingX(){
        return BindingBuilder.bind(queueB()).to(xExchange()).with("XB");
    }

    @Bean
    public Binding queueDBindingY(){
        return BindingBuilder.bind(queueD()).to(yExchange()).with(DEAD_LETTER_QUEUE_KEY);
    }
}

