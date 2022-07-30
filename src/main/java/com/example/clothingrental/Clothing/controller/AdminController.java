package com.example.clothingrental.Clothing.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.example.clothingrental.Clothing.common.AdminContext;
import com.example.clothingrental.Clothing.common.Res;
import com.example.clothingrental.Clothing.entity.*;
import com.example.clothingrental.Clothing.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 管理员信息 前端控制器
 * </p>
 *
 * @author lu
 * @since 2022-07-07
 */
@RestController
@RequestMapping("/Clothing/admin")
@CrossOrigin
public class AdminController{

    @Autowired
    IAdminService iAdminService;
    @Autowired
    IUserService userService;
    @Autowired
    IClothesOrdersService clothesOrdersService;
    @Autowired
    IClothesDetailService clothesDetailService;
    @Autowired
    IMessageService iMessageService;
    @PostMapping("/login")
    public Res<String> login(@RequestBody Admin admin){
        String password = admin.getPassword();
        password= DigestUtils.md5DigestAsHex(password.getBytes());
        LambdaQueryWrapper<Admin> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(Admin::getName,admin.getName());
        Admin admin1 = iAdminService.getOne(queryWrapper);
        if(admin1==null){
            admin.setPassword(password);
            iAdminService.save(admin);
            return Res.success("注册成功:"+admin.getName());
        }else if (admin1.getPassword().equals(password)){
            AdminContext.setAdminId(admin1.getId());
            return Res.success("登陆成功:"+admin1.getName());
        }else {
            return Res.error("用户名或密码错误");
        }
    }
    /**
     * 管理员禁用会员和解除会员
     * @param sid 状态码
     * @param id 用户id
     * @return
     */
    @GetMapping("/status/{sid}")
    public Res<String> status(@PathVariable Integer sid, Long id){
        LambdaUpdateWrapper<User> updateWrapper=new LambdaUpdateWrapper<>();
        updateWrapper.eq(User::getId,id).set(User::getStatus,sid);
        userService.update(updateWrapper);
        return Res.success("修改成功");
    }

    /**
     * 管理员确认订单发送
     */
    @GetMapping("/orderstatus")
    public Res<String> status(@RequestParam("ordersId") Long orderId,
                              @RequestParam("status") Integer status){
        //修改订单详情
        LambdaUpdateWrapper<ClothesOrders> lambdaUpdateWrapper=new LambdaUpdateWrapper<>();
        lambdaUpdateWrapper.set(ClothesOrders::getStatus,status);
        lambdaUpdateWrapper.set(ClothesOrders::getManagerStatus,1);
        lambdaUpdateWrapper.eq(ClothesOrders::getId,orderId);
        clothesOrdersService.update(lambdaUpdateWrapper);
        //查询订单信息
        ClothesOrders order = clothesOrdersService.getById(orderId);
        //添加消息
        Message message=new Message();
        message.setUserId(order.getUserId());
        message.setNumber(order.getNumber());
        message.setTitle("订单消息");
        message.setMessbody("订单:"+order.getNumber()+(status==3?"已发货":"已取消"));
        iMessageService.saveOrUpdate(message);
        return Res.success("操作成功");
    }
    /**
     * 查看所有会员
     */
    @PostMapping("/users")
    public Res<List<User>> queryU(){
        List<User> list = userService.list();
        return Res.success(list);
    }
    /**
     * 查看所有服装信息
     */
    @GetMapping("/clothes")
    public Res<List<ClothesDetail>> queryCloth(@RequestParam String search){
        LambdaQueryWrapper<ClothesDetail> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.like(search!=null,ClothesDetail::getName,search);
        queryWrapper.orderByDesc(ClothesDetail::getUpdateTime);
        List<ClothesDetail> list = clothesDetailService.list(queryWrapper);
        return Res.success(list);
    }
    /**
     * 查看所有未发货的订单信息
     */
    @PostMapping("/order")
    public Res<List<ClothesOrders>> queryOrder(){
        LambdaQueryWrapper<ClothesOrders> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(ClothesOrders::getManagerStatus,0);
        List<ClothesOrders> list = clothesOrdersService.list(queryWrapper);
        return Res.success(list);
    }
}
