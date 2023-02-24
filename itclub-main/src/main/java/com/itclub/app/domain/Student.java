package com.itclub.app.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author onePiece
 */
@Data
@TableName("stu")
public class Student {

    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private String sex;
}
