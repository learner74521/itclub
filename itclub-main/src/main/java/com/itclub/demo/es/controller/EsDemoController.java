package com.itclub.demo.es.controller;

import com.itclub.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能描述
 *
 * @author: onePiece
 * @date: 2023-02-24
 */
@RestController
@RequestMapping("demo")
public class EsDemoController {

//    @Autowired
//    private ElasticsearchRestTemplate esService;

    @GetMapping("es")
    public AjaxResult getEs(){
         return AjaxResult.success();
    }
}
