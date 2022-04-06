package com.itclub.app.controller;


import com.itclub.app.domain.Student;
import com.itclub.app.mapper.StuMapper;
import com.itclub.app.service.StuService;
import com.itclub.common.annotation.DataSource;
import com.itclub.common.enums.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 *  控制台
 *
 *  @author Monkey D. Luffy
 */
@RestController
@RequestMapping("/app")
public class StuController {

    @Autowired
    private StuService stuService;

    @GetMapping("/hello")
    public String hello(){
        return "welcome";
    }


}
