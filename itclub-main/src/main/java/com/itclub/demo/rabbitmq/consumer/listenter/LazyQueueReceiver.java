package com.itclub.demo.rabbitmq.consumer.listenter;

import com.itclub.rabbitmq.config.LazyQueueConfig;
import com.rabbitmq.client.Channel;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * 惰性队列-消费端
 *
 * @author: onePiece
 * @date: 2023-02-21
 */
@SuppressWarnings("all")
@Component
public class LazyQueueReceiver {
    /**
     *
     * @param channel 信道
     * @param message 消息
     * @throws Exception
     */
    @RabbitListener(queues = LazyQueueConfig.LAZY_TOPIC_QUEUE)
    public void onMessage(Channel channel, Message message) throws Exception {
        System.out.println("--------------------------------------");
        System.out.println("消费端Payload: " + message.getPayload()+"-ID:"+message.getHeaders().getId()+"-messageId:"+message.getHeaders());
        Long deliveryTag = (Long)message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
        //手工ACK,获取deliveryTag
        channel.basicAck(deliveryTag, false);
    }
}

