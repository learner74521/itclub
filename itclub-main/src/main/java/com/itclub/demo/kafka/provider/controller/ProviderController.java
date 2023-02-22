//package com.itclub.demo.kafka.provider.listenter;
//
//
//import com.itclub.common.core.domain.AjaxResult;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.KafkaTemplate;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * 功能描述
// *
// * @author: 天鸣
// * @date: 2023-02-15
// */
//@RestController
//@RequestMapping("/demo")
//public class ProviderController {
//
//    @Autowired
//    private KafkaTemplate<String, Object> stringKafkaTemplate;
//
//
//    @GetMapping("Provider")
//    public AjaxResult provider(@RequestParam("id") String id){
//
//        for (int i = 0; i < 5; i++) {
//            stringKafkaTemplate.executeInTransaction(operations -> operations.send("topic1", id));
//        }
//        return AjaxResult.success();
//    }
//
//    @GetMapping("Provider1")
//    public AjaxResult provider1(@RequestParam("id") String id){
//        stringKafkaTemplate.executeInTransaction(operations -> operations.send("topic2", id));
//        return AjaxResult.success();
//    }
//
//
//}
