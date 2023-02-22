package com.itclub.demo.rabbitmq.consumer.listenter;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 主题交换机-消费端
 * 匹配 Routing key
 * @author: onePiece
 * @date: 2023-02-20
 */
@Component
public class TopicReceiver {


    @RabbitListener(queues = "topic.A")
    public void processA(Map message) {
        System.out.println("processA消费者收到消息  : " + message.toString());
    }

    @RabbitListener(queues = "Topic.B")
    public void processB(Map message) {
        System.out.println("processB消费者收到消息  : " + message.toString());
    }

}