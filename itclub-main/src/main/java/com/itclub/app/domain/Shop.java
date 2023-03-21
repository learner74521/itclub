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
@TableName("shop")
public class Shop {

    @TableId(type = IdType.ASSIGN_ID)
    private Long shopId;
    private String shopName;
    private String shopPrice;
    private String shopType;
}
