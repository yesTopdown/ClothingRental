package com.example.clothingrental.Clothing.Vo;

import com.example.clothingrental.Clothing.entity.ClothesOrders;
import lombok.Data;
import java.util.List;

/**
 *总订单
 */
@Data
public class TotalOrders extends ClothesOrders{
    List<Long> shopId;
}
