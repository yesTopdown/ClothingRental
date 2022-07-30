package com.example.clothingrental.Clothing.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.clothingrental.Clothing.entity.OrderDetail;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 订单明细表 Mapper 接口
 * </p>
 *
 * @author lu
 * @since 2022-07-07
 */
@Mapper
public interface OrderDetailMapper extends BaseMapper<OrderDetail> {

}
