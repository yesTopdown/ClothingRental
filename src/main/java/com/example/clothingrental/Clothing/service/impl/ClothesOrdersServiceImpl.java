package com.example.clothingrental.Clothing.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.clothingrental.Clothing.common.AdminContext;
import com.example.clothingrental.Clothing.entity.*;
import com.example.clothingrental.Clothing.mapper.ClothesOrdersMapper;
import com.example.clothingrental.Clothing.service.IAddressBookService;
import com.example.clothingrental.Clothing.service.IClothesOrdersService;
import com.example.clothingrental.Clothing.service.IOrderDetailService;
import com.example.clothingrental.Clothing.service.IShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author lu
 * @since 2022-07-07
 */
@Service
public class ClothesOrdersServiceImpl extends ServiceImpl<ClothesOrdersMapper, ClothesOrders> implements IClothesOrdersService {
    @Autowired
    public IAddressBookService addressBookService;

    @Autowired
    IOrderDetailService orderDetailService;

    @Autowired
    IShoppingCartService shoppingCartService;

    @Override
    @Transactional
    public void submitOder(ClothesOrders clothesOrders,List<Long> shopId) {
        //获取当前用户的id
        Long uid = AdminContext.getUserId();
        //根据用户id查询购物车中选取的商品
        LambdaQueryWrapper<ShoppingCart> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ShoppingCart::getUserId, uid);
        wrapper.in(ShoppingCart::getId,shopId);
        List<ShoppingCart> shoppingCarts = shoppingCartService.list(wrapper); //支付的商品
        //查询地址数据
        Long addressBookId = clothesOrders.getAddressBookId();
        AddressBook addressBook = addressBookService.getById(addressBookId);
        //商品订单物流
        long orderId = IdWorker.getId();//获取订单号
        clothesOrders.setStatus(2);
        clothesOrders.setNumber(String.valueOf(orderId));
        clothesOrders.setUserId(uid);
        clothesOrders.setConsignee(addressBook.getConsignee()); //收货人
        clothesOrders.setPhone(addressBook.getPhone());
        clothesOrders.setAddress(addressBook.getProvinceName() == null ? "" : addressBook.getProvinceName()
                + (addressBook.getCityName() == null ? "" : addressBook.getCityName())
                + (addressBook.getDistrictName() == null ? "" : addressBook.getDistrictName())
                + (addressBook.getDetail() == null ? "" : addressBook.getDetail()));
        AtomicLong amount = new AtomicLong(0);//使用原子类表示总金额
        //计算总金额和订单明细
        List<OrderDetail> orderDetails = shoppingCarts.stream().map((item) -> {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderId(clothesOrders.getId()); //物流id
            orderDetail.setNumber(item.getNumber());
            orderDetail.setName(item.getName());
            orderDetail.setImage(item.getImage());
            orderDetail.setAmount(item.getAmount());
            orderDetail.setRentalDay(item.getRentalDay());
            orderDetail.setUserId(uid);
            amount.addAndGet(item.getAmount().multiply(new BigDecimal(item.getNumber())).intValue());
            return orderDetail;
        }).collect(Collectors.toList());
        clothesOrders.setAmount(new BigDecimal(amount.get())); //总金额
        //向订单表插入数据，一条数据
        this.saveOrUpdate(clothesOrders);
        orderDetails = orderDetails.stream().peek((item) -> item.setOrderId(clothesOrders.getId())).collect(Collectors.toList());
        //向订单明细表插入数据，多条数据
        orderDetailService.saveBatch(orderDetails);
        //清空购物车
        shoppingCartService.remove(wrapper);
    }

}
