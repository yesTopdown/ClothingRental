package com.example.clothingrental.Clothing.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.clothingrental.Clothing.entity.ClothesOrders;
import java.util.List;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author lu
 * @since 2022-07-07
 */
public interface IClothesOrdersService extends IService<ClothesOrders> {
    public void submitOder(ClothesOrders clothesOrders,List<Long> shopId);
}
