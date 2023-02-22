package com.itclub.app.controller;


import com.google.gson.Gson;
import com.itclub.redis.service.RedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



/**
 *  控制台
 *
 *  @author Monkey D. Luffy
 */
@RestController
@Slf4j
@RequestMapping("/app")
@RequiredArgsConstructor
public class StuController {


    @Autowired
    private RedisService redisService;

    @Autowired
    private ElasticsearchRestTemplate esService;

    @GetMapping("/hello")
    public String hello(){
        redisService.setEx("key","1",200L);
        return "welcome";
    }

}
