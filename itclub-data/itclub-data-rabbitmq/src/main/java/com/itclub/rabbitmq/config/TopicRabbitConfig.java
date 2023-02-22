package com.itclub.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rabbitmq配置  主题交换机
 * 类比扇形交换机，但队列在绑定 Routing key 可以使用通配符
 * @Author : onePiece
 **/
@Configuration
public class TopicRabbitConfig {

    @Bean
    public Queue topicQueueA() {
        return new Queue("topic.A");
    }

    @Bean
    public Queue topicQueueB() {
        return new Queue("Topic.B");
    }

    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange("TopicExchange");
    }

    /**
     * 将topic.A和TopicExchange绑定,而且绑定的键值为Topic.Routing
     * @return
     */
    @Bean
    Binding bindingExchangeMessage() {
        return BindingBuilder.bind(topicQueueA()).to(topicExchange()).with("topic.Routing");
    }

    /**
     * 将Topic.B和TopicExchange绑定,而且绑定的键值为用上通配路由键规则topic.#
     * @return
     */
    @Bean
    Binding bindingExchangeMessage2() {
        return BindingBuilder.bind(topicQueueB()).to(topicExchange()).with("Topic.#");
    }

}