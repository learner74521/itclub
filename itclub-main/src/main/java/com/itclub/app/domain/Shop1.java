package com.itclub.app.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author onePiece
 */
@Data
@TableName("shop1")
public class Shop1 {

    @TableId(type = IdType.ASSIGN_ID)
    private Long shopId;
    private String shopName;
    private String shopPrice;
    private String shopType;
}
