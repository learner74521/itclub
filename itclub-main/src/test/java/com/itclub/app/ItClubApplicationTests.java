package com.itclub.app;

import com.itclub.app.domain.Student;
import com.itclub.app.mapper.StuMapper;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//@RunWith(SpringRunner.class)springboot2.2版本以前
@SpringBootTest
class ItClubApplicationTests {

    @Autowired
    private StuMapper stuMapper;

    @Test
//    @DataSource(value = DataSourceType.SLAVE) //测试切换数据源
    void testInsert() {
        Student userList = stuMapper.selectById("1");
        System.out.println(userList);
    }

    @Test
    public void testCreateDbTable(){
        //  1 以下使用默认方式 使用默认方式目录名称是不能改的！
        //  需要使用avtiviti提供的工具类 ProcessEngines ,使用方法getDefaultProcessEngine
        //  getDefaultProcessEngine会默认从resources下读取名字为actviti.cfg.xml的文件
        //  创建processEngine时，就会创建mysql的表
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
    }



}
