package com.example.clothingrental.Clothing.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.clothingrental.Clothing.Vo.LoginVo;
import com.example.clothingrental.Clothing.common.Res;
import com.example.clothingrental.Clothing.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 用户信息 服务类
 * </p>
 *
 * @author lu
 * @since 2022-07-07
 */
public interface IUserService extends IService<User> {
    /**
     * 登录
     */
    Res<User> login(HttpServletRequest request, HttpServletResponse response, LoginVo loginVo);

    /**
     * 根据cookie获取用户
     * @param userTicket
     * @param request
     * @param response
     * @return
     */
    User getByUserTicket(String userTicket,HttpServletRequest request,HttpServletResponse response);
}
