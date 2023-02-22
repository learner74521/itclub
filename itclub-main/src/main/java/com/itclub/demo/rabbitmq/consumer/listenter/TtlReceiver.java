package com.itclub.demo.rabbitmq.consumer.listenter;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 延迟消费
 *
 * @author: onePiece
 * @date: 2023-02-21
 */
@Component
public class TtlReceiver {

//    @RabbitListener(queues = "QA")
    public void xa(Message message) throws Exception {
//        String nowDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//        byte[] body = message.getBody();
//        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(body));
//        System.out.println("QA队列消费: 当前时间" + nowDate + ois.readObject().toString());
    }

    @RabbitListener(queues = "QD")
    public void xb(Message message) throws Exception {
        String nowDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        byte[] body = message.getBody();
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(body));
        System.out.println("死信队列消费: 当前时间" + nowDate + ois.readObject().toString());
    }

}
