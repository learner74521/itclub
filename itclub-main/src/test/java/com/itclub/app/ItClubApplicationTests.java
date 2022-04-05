package com.itclub.app;

import com.itclub.app.domain.Student;
import com.itclub.app.mapper.StuMapper;
import com.itclub.common.annotation.DataSource;
import com.itclub.common.enums.DataSourceType;
import com.itclub.framework.datasource.DynamicDataSourceContextHolder;
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
    @DataSource(value = DataSourceType.SLAVE)
    void testInsert() {
//        DynamicDataSourceContextHolder.setDataSourceType(DataSourceType.SLAVE.name());
        List<Student> userList = stuMapper.searchAllByCreat();
        System.out.println(userList);
//        DynamicDataSourceContextHolder.clearDataSourceType();

    }

}
