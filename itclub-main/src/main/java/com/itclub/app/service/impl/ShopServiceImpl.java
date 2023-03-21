package com.itclub.app.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itclub.app.domain.Shop;
import com.itclub.app.mapper.ShopMapper;
import com.itclub.app.service.IShopService;
import com.itclub.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 *
 *  @author Monkey D. Luffy
 */
@Service
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements IShopService {

    @Autowired
    private ShopMapper shopMapper;

    public int add(Shop shop){
        return shopMapper.insert(shop);
    }

    public List<Shop> list(){
        return shopMapper.selectList(null);
    }

}
