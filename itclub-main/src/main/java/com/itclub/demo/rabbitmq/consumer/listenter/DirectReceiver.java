package com.itclub.demo.rabbitmq.consumer.listenter;

import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Map;


/**
 * 直接交换机-消费端
 * 一个队列中在多个消费者中，每个消息只被消费一次
 * @author: onePiece
 * @date: 2023-02-20
 */
@Component
public class DirectReceiver {

    @RabbitListener(queues = "DirectQueue")
    public void process(Map message) {
        System.out.println("process消费者收到消息  : " + message.toString());
    }

    @RabbitListener(queues = "DirectQueue")
    public void process1(Map message) {
        System.out.println("process1消费者收到消息  : " + message.toString());
    }

}
