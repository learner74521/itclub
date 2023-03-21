package com.itclub.app.controller;


import com.itclub.app.domain.Shop;
import com.itclub.app.mapper.ShopMapper;
import com.itclub.app.service.IShopService;
import com.itclub.common.core.domain.AjaxResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


/**
 *  控制台
 *
 *  @author Monkey D. Luffy
 */
@RestController
@Slf4j
@RequestMapping("/app")
@RequiredArgsConstructor
public class ShopController {

    private final IShopService shopService;

    @PostMapping("/shop/add")
    public AjaxResult add(@RequestBody Shop shop){
        return AjaxResult.success(shopService.add(shop));
    }

    @GetMapping("/shop/list")
    public AjaxResult list(){
        return AjaxResult.success(shopService.list());
    }

}
