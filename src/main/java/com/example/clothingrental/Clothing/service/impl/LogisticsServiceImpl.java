package com.example.clothingrental.Clothing.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.clothingrental.Clothing.entity.Logistics;
import com.example.clothingrental.Clothing.mapper.LogisticsMapper;
import com.example.clothingrental.Clothing.mapper.UserMapper;
import com.example.clothingrental.Clothing.service.ILogisticsService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 物流表 服务实现类
 * </p>
 *
 * @author lu
 * @since 2022-07-07
 */
@Service
public class LogisticsServiceImpl extends ServiceImpl<LogisticsMapper, Logistics> implements ILogisticsService {

}
