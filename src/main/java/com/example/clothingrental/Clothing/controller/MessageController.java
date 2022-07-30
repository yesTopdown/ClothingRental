package com.example.clothingrental.Clothing.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.clothingrental.Clothing.common.AdminContext;
import com.example.clothingrental.Clothing.common.Res;
import com.example.clothingrental.Clothing.entity.Message;
import com.example.clothingrental.Clothing.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lu
 * @since 2022-07-10
 */
@RestController
@RequestMapping("/Clot/message")
@CrossOrigin
public class MessageController{
    @Autowired
    IMessageService iMessageService;
     //查看用户消息
    @PostMapping("/queryMessage")
    public Res<List<Message>> query(){
        LambdaQueryWrapper<Message> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(Message::getUserId, AdminContext.getUserId());
        queryWrapper.orderByDesc(Message::getCreateTime);
        List<Message> list = iMessageService.list(queryWrapper);
        return Res.success(list);
    }
    //删除消息
    @GetMapping("/deletesM/{id}")
    public Res<String> delete(@PathVariable Long id){
        iMessageService.removeById(id);
        return Res.success("删除成功");
    }
}
