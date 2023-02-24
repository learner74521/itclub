package com.itclub.demo.redis.controller;

import com.itclub.common.core.domain.AjaxResult;
import com.itclub.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * redis 演示demo
 *
 * @author: onePiece
 * @date: 2023-02-24
 */
@RestController
@RequestMapping("demo")
public class RedisDemoController {

    @Autowired
    private RedisService redisService;

    @GetMapping("/redis")
    public AjaxResult getRedis(){
        redisService.setEx("key","welcome redis",200L);
        return AjaxResult.success(redisService.get("key"));
    }
}
