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
 * 扇形交换机-生产端
 * 同时推送多个队列
 *
 * @author: onePiece
 * @date: 2023-02-20
 */
@RestController
@RequestMapping("demo")
public class FanoutProvider {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("fanout")
    public AjaxResult fanout() {
        for (int i = 0; i < 5; i++) {
            String messageId = Convert.toStr(UUID.randomUUID());
            String messageData = "FanoutMessage"+i+", hello!";
            String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            Map<String, Object> map = new HashMap<>(16);
            map.put("messageId", messageId);
            map.put("messageData", messageData);
            map.put("createTime", createTime);
            rabbitTemplate.convertAndSend("FanoutExchange", null, map);
        }
        return AjaxResult.success();
    }
}
