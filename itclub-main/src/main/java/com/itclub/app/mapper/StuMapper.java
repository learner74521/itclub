package com.itclub.app.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itclub.app.domain.Student;
import com.itclub.common.annotation.DataSource;
import com.itclub.common.enums.DataSourceType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 *
 *  @author Monkey D. Luffy
 */
public interface StuMapper extends BaseMapper<Student> {

    @Select("select * from stu")
    public List<Student> searchAllByCreat();

}
