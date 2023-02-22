package com.itclub.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * rabbitmq配置  扇形交换机
 * 队列与交换机的绑定,只负责转发消息，不具备存储消息的能力
 * @Author : onePiece
 **/
@Configuration
public class FanoutRabbitConfig {

    /**
     *  创建三个队列 ：fanout.A   fanout.B  fanout.C
     *  将三个队列都绑定在交换机 fanoutExchange 上
     *  因为是扇型交换机, 路由键无需配置,配置也不起作用
     */
    @Bean
    public Queue fanoutQueueA() {
        return new Queue("Fanout.A",true);
    }

    @Bean
    public Queue fanoutQueueB() {
        return new Queue("Fanout.B",true);
    }

    @Bean
    public Queue fanoutQueueC() {
        return new Queue("Fanout.C",true);
    }

    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("FanoutExchange");
    }

    @Bean
    Binding bindingExchangeA() {
        return BindingBuilder.bind(fanoutQueueA()).to(fanoutExchange());
    }

    @Bean
    Binding bindingExchangeB() {
        return BindingBuilder.bind(fanoutQueueB()).to(fanoutExchange());
    }

    @Bean
    Binding bindingExchangeC() {
        return BindingBuilder.bind(fanoutQueueC()).to(fanoutExchange());
    }
}