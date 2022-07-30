package com.example.clothingrental.Clothing.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.clothingrental.Clothing.Vo.TotalOrders;
import com.example.clothingrental.Clothing.common.AdminContext;
import com.example.clothingrental.Clothing.common.Res;
import com.example.clothingrental.Clothing.entity.ClothesOrders;
import com.example.clothingrental.Clothing.service.IClothesOrdersService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单表 前端控制器
 * </p>
 *
 * @author lu
 * @since 2022-07-07
 */
@RestController
@RequestMapping("/Clothing/clothes-orders")
@CrossOrigin
public class ClothesOrdersController {
    @Autowired
    IClothesOrdersService clothesOrdersService;

    /**
     * 下单
     * @param
     * @param
     * @return
     */
    @PostMapping("/submit")
    public Res<String> submitOrder(@RequestBody TotalOrders totalOrders){
        if(totalOrders.getShopId()==null){
            return Res.error("下单失败");
        }
        ClothesOrders clothesOrders= new ClothesOrders();
        BeanUtils.copyProperties(totalOrders,clothesOrders,"shopId");
        clothesOrdersService.submitOder(clothesOrders,totalOrders.getShopId());
        return Res.success("下单成功");
    }

    /**
     * 用户查询订单
     * @return
     */
    @PostMapping("/queryTopOrder")
    public Res<List<ClothesOrders>> query(){
        Long adminId = AdminContext.getUserId();
        LambdaQueryWrapper<ClothesOrders> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(ClothesOrders::getUserId,adminId);
        queryWrapper.orderByDesc(ClothesOrders::getOrderTime);
        List<ClothesOrders> list = clothesOrdersService.list(queryWrapper);
        return Res.success(list);
    }

}
