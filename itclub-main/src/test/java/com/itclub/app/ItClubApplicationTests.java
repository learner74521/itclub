package com.itclub.app;

import com.itclub.app.domain.Shop;
//import com.itclub.app.mapper.ShopMapper;

import lombok.RequiredArgsConstructor;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@RequiredArgsConstructor
class ItClubApplicationTests {

//    private final ShopMapper shopMapper;

    @Test
//    @DataSource(value = DataSourceType.SLAVE) //测试切换数据源
    void testInsert() {
//        Shop list = shopMapper.selectById("1");
//        System.out.println(list);
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
