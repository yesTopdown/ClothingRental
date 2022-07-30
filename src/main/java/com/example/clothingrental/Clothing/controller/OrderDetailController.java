package com.example.clothingrental.Clothing.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.clothingrental.Clothing.common.AdminContext;
import com.example.clothingrental.Clothing.common.Res;
import com.example.clothingrental.Clothing.entity.ClothesDetail;
import com.example.clothingrental.Clothing.entity.OrderDetail;
import com.example.clothingrental.Clothing.entity.OrderDetail;
import com.example.clothingrental.Clothing.service.IOrderDetailService;
import com.example.clothingrental.Clothing.service.IOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 订单明细表 前端控制器
 * </p>
 *
 * @author lu
 * @since 2022-07-07
 */
@RestController
@RequestMapping("/Clothing/order-detail")
@CrossOrigin
public class OrderDetailController{
    @Autowired
    IOrderDetailService orderDetailService;
    //用户查询订单
    @PostMapping("/queryDe")
    public Res<List<OrderDetail>> query(){
        Long adminId = AdminContext.getUserId();
        LambdaQueryWrapper<OrderDetail> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(OrderDetail::getUserId,adminId);
        queryWrapper.orderByDesc(OrderDetail::getCreateTime);
        List<OrderDetail> list = orderDetailService.list(queryWrapper);
        return Res.success(list);
    }
}
