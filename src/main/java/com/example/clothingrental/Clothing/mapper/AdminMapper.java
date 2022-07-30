package com.example.clothingrental.Clothing.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.clothingrental.Clothing.entity.Admin;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 管理员信息 Mapper 接口
 * </p>
 *
 * @author lu
 * @since 2022-07-07
 */
@Mapper
public interface AdminMapper extends BaseMapper<Admin> {

}
