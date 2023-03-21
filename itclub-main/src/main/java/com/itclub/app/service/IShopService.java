package com.itclub.app.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itclub.app.domain.Shop;

import java.util.List;

/**
 *
 *  @author Monkey D. Luffy
 */
public interface IShopService extends IService<Shop> {

      int add(Shop shop);

      List<Shop> list();
}
