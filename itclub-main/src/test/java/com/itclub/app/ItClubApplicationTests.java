package com.itclub.app;

import com.itclub.app.domain.Student;
import com.itclub.app.mapper.StuMapper;
import com.itclub.common.annotation.DataSource;
import com.itclub.common.enums.DataSourceType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


//@RunWith(SpringRunner.class)springboot2.2版本以前
@SpringBootTest
class ItClubApplicationTests {

    @Autowired
    private StuMapper stuMapper;

    @Test
//    @DataSource(value = DataSourceType.SLAVE) //测试切换数据源
    void testInsert() {
        List<Student> userList = stuMapper.searchAllByCreat();
        System.out.println(userList);
    }

}
