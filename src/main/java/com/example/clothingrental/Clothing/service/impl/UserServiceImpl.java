package com.example.clothingrental.Clothing.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.clothingrental.Clothing.Util.CookieUtil;
import com.example.clothingrental.Clothing.Util.JsonUtil;
import com.example.clothingrental.Clothing.Util.UUIDUtil;
import com.example.clothingrental.Clothing.Vo.LoginVo;
import com.example.clothingrental.Clothing.common.AdminContext;
import com.example.clothingrental.Clothing.common.Res;
import com.example.clothingrental.Clothing.entity.User;
import com.example.clothingrental.Clothing.mapper.UserMapper;
import com.example.clothingrental.Clothing.service.IUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 用户信息 服务实现类
 * </p>
 *
 * @author lu
 * @since 2022-07-07
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private RedisTemplate redisTemplate;


    @Override
    public Res<User> login(HttpServletRequest request, HttpServletResponse response, LoginVo loginVo) {
        //密码进行md5加密
        String password=loginVo.getPassword(); //获取密码
        password= DigestUtils.md5DigestAsHex(password.getBytes());
        LambdaQueryWrapper<User> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getName,loginVo.getName());
        User user = userMapper.selectOne(queryWrapper);
        if (null==user){
            return Res.error("登录失败");
        }
        if(!user.getPassword().equals(password)){
            return Res.error("登录失败");
        }
        if (user.getStatus()==0){
            return Res.error("账号已禁用");
        }
//        request.getSession().setAttribute("user",user.getId());
//        AdminContext.setUserId(user.getId());
        //生成cookie
        String ticket = UUIDUtil.uuid();
        request.getSession().setAttribute(ticket,user);
        redisTemplate.opsForValue().set("user:" + ticket,
                JsonUtil.object2JsonStr(user));
        CookieUtil.setCookie(request,response,"userTicket",ticket);
        return Res.success(user);
    }

    @Override
    public User getByUserTicket(String userTicket, HttpServletRequest request, HttpServletResponse response) {
        if(StringUtils.isEmpty(userTicket)){
            return null; //代表用户未登录
        }
        String userJson = (String) redisTemplate.opsForValue().get("user:" + userTicket);
        User user = JsonUtil.jsonStr2Object(userJson, User.class);
        if(null!=user){ //解析成功
            CookieUtil.setCookie(request,response,"userTicket",userTicket);
        }
        return user;
    }
}
