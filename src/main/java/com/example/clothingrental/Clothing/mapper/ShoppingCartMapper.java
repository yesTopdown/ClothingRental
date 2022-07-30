package com.example.clothingrental.Clothing.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.clothingrental.Clothing.entity.ShoppingCart;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 购物车 Mapper 接口
 * </p>
 *
 * @author lu
 * @since 2022-07-07
 */
@Mapper
public interface ShoppingCartMapper extends BaseMapper<ShoppingCart> {

}
