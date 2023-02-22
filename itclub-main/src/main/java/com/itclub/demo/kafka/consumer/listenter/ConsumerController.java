//package com.itclub.demo.kafka.consumer.listenter;
//
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.kafka.support.Acknowledgment;
//import org.springframework.stereotype.Component;
//
///**
// * 功能描述
// *
// * @author: 天鸣
// * @date: 2023-02-15
// */
//@Component
//public class ConsumerController {
//
//    @KafkaListener(topics = {"topic1"})
//    private void consumer(ConsumerRecord<?,?> consumerRecord,Acknowledgment acknowledgment){
//        System.out.println(consumerRecord.topic()+"_"+consumerRecord.partition()+"_value:"+consumerRecord.value());
//        acknowledgment.acknowledge();
//    }
//
//}
