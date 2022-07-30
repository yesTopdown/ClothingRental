package com.example.clothingrental.Clothing.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.clothingrental.Clothing.common.AdminContext;
import com.example.clothingrental.Clothing.common.Res;
import com.example.clothingrental.Clothing.entity.User;
import com.example.clothingrental.Clothing.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 用户信息 前端控制器
 * </p>
 *
 * @author lu
 * @since 2022-07-07
 */
@RestController
@RequestMapping("/Clothing/User")
@CrossOrigin
public class UserController {
    @Autowired
    IUserService userService;
    /**
     * 用户登录
     */
    @PostMapping("/login")
    public Res<User> login(HttpServletRequest request, @RequestBody User user){
        //密码进行md5加密
        String password=user.getPassword(); //获取密码
        password= DigestUtils.md5DigestAsHex(password.getBytes());
        LambdaQueryWrapper<User> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getName,user.getName());
        User user1 = userService.getOne(queryWrapper);
        if (user1==null){
            return Res.error("登录失败");
        }
        if(!user1.getPassword().equals(password)){
            return Res.error("登录失败");
        }
        if (user1.getStatus()==0){
            return Res.error("账号已禁用");
        }
        request.getSession().setAttribute("user",user1.getId());
        AdminContext.setUserId(user1.getId());
        return Res.success(user1);
    }
    /**
     * 用户退出
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public Res<String> logout(HttpServletRequest request){
        //清理Session中保存的当前登录员工id
        request.getSession().removeAttribute("user");
        return  Res.success("退出成功");
    }
    /**
     * 用户注册
     */
    @PostMapping("/register")
    public Res<String> save(@RequestBody User user){
        //密码加密
        String password= user.getPassword();
        password=DigestUtils.md5DigestAsHex(password.getBytes());
        LambdaQueryWrapper<User> wrapper=new LambdaQueryWrapper<>();
        wrapper.eq(User::getName,user.getName());
        User one = userService.getOne(wrapper);
        if (one!=null&&one.getName().equals(user.getName())){
            return Res.error("用户名被注册");
        }
        user.setPassword(password);
        userService.save(user);
        return Res.success("注册成功");
    }
}
