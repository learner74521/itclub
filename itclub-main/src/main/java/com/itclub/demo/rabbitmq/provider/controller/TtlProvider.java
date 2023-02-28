package com.itclub.demo.rabbitmq.provider.controller;

import com.itclub.common.core.domain.AjaxResult;
import com.itclub.common.core.text.Convert;
import com.itclub.common.utils.uuid.UUID;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * 延迟队列-生产端
 *
 * @author: onePiece
 * @date: 2023-02-21
 */
@RestController
@RequestMapping("demo")
public class TtlProvider {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("ttl")
    public AjaxResult ttl(){
        for (int i = 0; i < 5; i++) {
            String messageId = Convert.toStr(UUID.randomUUID());
            String messageData = "ttl hello rabbit";
            String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            Map<String, Object> map = new HashMap<>(16);
            map.put("messageId", messageId);
            map.put("messageData", messageData);
            map.put("createTime", createTime);
            if (i % 2 == 0) {
                rabbitTemplate.convertAndSend("X", "XA", map);
            }else {
                rabbitTemplate.convertAndSend("X", "XB", map);
            }

        }
        return AjaxResult.success();
    }

}
