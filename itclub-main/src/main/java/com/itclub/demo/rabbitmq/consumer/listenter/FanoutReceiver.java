package com.itclub.demo.rabbitmq.consumer.listenter;

import com.itclub.common.core.text.Convert;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 扇形交换机-消费端
 *
 * @author: onePiece
 * @date: 2023-02-20
 */
@Component
public class FanoutReceiver {

    /**
     * 指定队列名称
     */
    @RabbitListener(queues = "Fanout.A")
    public void fanoutA(Map message) {
        System.out.println("fanout.A消费者收到消息  : " + message.toString());
    }

    @RabbitListener(queues = "Fanout.B")
    public void fanoutB(Map message) {
        System.out.println("fanout.B消费者收到消息  : " + message.toString());
    }

    @RabbitListener(queues = "Fanout.C")
    public void fanoutC(Map message) {
        System.out.println("fanout.C消费者收到消息  : " + message.toString());
    }

    /**
     * 不指定队列名称
     * 队列名称,系统会随机产生
     * @param message
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(),
            exchange = @Exchange(value = "FanoutExchange", type = ExchangeTypes.FANOUT)))
    public void fanoutD(Message message) {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        System.out.println("fanout.D消费者收到消息  : " + deliveryTag);
        System.out.println(message);
    }
}

