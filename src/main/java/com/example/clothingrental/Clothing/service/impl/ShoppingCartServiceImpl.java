package com.example.clothingrental.Clothing.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.clothingrental.Clothing.entity.ClothesOrders;
import com.example.clothingrental.Clothing.entity.ShoppingCart;
import com.example.clothingrental.Clothing.mapper.ShoppingCartMapper;
import com.example.clothingrental.Clothing.service.IAddressBookService;
import com.example.clothingrental.Clothing.service.IShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 购物车 服务实现类
 * </p>
 *
 * @author lu
 * @since 2022-07-07
 */
@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements IShoppingCartService {


}
