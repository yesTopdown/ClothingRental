package com.example.clothingrental.Clothing.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.clothingrental.Clothing.entity.Admin;
import com.example.clothingrental.Clothing.mapper.AdminMapper;
import com.example.clothingrental.Clothing.service.IAdminService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 管理员信息 服务实现类
 * </p>
 *
 * @author lu
 * @since 2022-07-07
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

}
