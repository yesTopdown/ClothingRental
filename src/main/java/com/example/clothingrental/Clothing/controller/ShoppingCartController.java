package com.example.clothingrental.Clothing.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import com.example.clothingrental.Clothing.common.AdminContext;
import com.example.clothingrental.Clothing.common.Res;
import com.example.clothingrental.Clothing.entity.ShoppingCart;
import com.example.clothingrental.Clothing.service.IShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


/**
 * <p>
 * 购物车 前端控制器
 * </p>
 *
 * @author lu
 * @since 2022-07-07
 */
@RestController
@RequestMapping("/Clothing/shopping-cart")
@CrossOrigin
public class ShoppingCartController{
    @Autowired
    private IShoppingCartService iShoppingCartService;

    /**
     * 添加购物车
     */
    @PostMapping("/add")
    public Res<ShoppingCart> add(@RequestBody ShoppingCart shoppingCart){
        //设置用户id
        Long id= AdminContext.getUserId();
        if(id==null){
            return Res.error("未登录");
        }
        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId,id).eq(ShoppingCart::getClothesId,shoppingCart.getClothesId());
        ShoppingCart cartOne = iShoppingCartService.getOne(queryWrapper);
        if(cartOne!=null) {
            //如果已经存在，就在原来数量基础上加一
            Integer number = cartOne.getNumber();
            BigDecimal amount = cartOne.getAmount().multiply(new BigDecimal(number + 1)); //计算价钱
            cartOne.setNumber(number+1);
            cartOne.setAmount(amount);
            iShoppingCartService.updateById(cartOne);
        }else {
            //如果不存在，则添加到购物车，数量默认就是1
            shoppingCart.setUserId(id);
            shoppingCart.setNumber(1);
            BigDecimal amount = shoppingCart.getAmount();
            Integer rentalDay = shoppingCart.getRentalDay();
            shoppingCart.setAmount(amount.multiply(new BigDecimal(rentalDay)));
            shoppingCart.setCreateTime(LocalDateTime.now());
            iShoppingCartService.save(shoppingCart);
            cartOne=shoppingCart;
        }
        return  Res.success(cartOne);
    }
    /**
     * 查看购物车 通过用户查看购物车
     * @return
     */
    @GetMapping("/list")
    public Res<List<ShoppingCart>> list(){
        LambdaQueryWrapper<ShoppingCart> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId,AdminContext.getUserId());
        queryWrapper.orderByAsc(ShoppingCart::getCreateTime);
        List<ShoppingCart> cartList = iShoppingCartService.list(queryWrapper);
        return Res.success(cartList);
    }
    /**
     * 删除商品 商品id
     */
    @GetMapping("/deleteCart")
    public Res<String> delete(Long id){
        LambdaQueryWrapper<ShoppingCart> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId,AdminContext.getUserId()).eq(ShoppingCart::getId,id);
        iShoppingCartService.remove(queryWrapper);
        return Res.success("删除成功");
    }
    /**
     * 查看购物车 在数组中 ids 购物车商品id
     */
    @PostMapping("/queryPay")
    public Res<List<ShoppingCart>> listPay(@RequestBody List<Long> ids) {
        Long uid=AdminContext.getUserId();
        LambdaQueryWrapper<ShoppingCart> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId,uid);
        queryWrapper.in(ShoppingCart::getId,ids);
        List<ShoppingCart> list = iShoppingCartService.list(queryWrapper);
        return Res.success(list);
    }
}
