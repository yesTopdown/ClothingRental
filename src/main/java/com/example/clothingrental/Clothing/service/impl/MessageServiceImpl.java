package com.example.clothingrental.Clothing.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.clothingrental.Clothing.entity.Message;
import com.example.clothingrental.Clothing.mapper.MessageMapper;
import com.example.clothingrental.Clothing.service.IMessageService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lu
 * @since 2022-07-10
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements IMessageService {

}
