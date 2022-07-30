package com.example.clothingrental.Clothing.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.clothingrental.Clothing.entity.AddressBook;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 地址管理 Mapper 接口
 * </p>
 *
 * @author lu
 * @since 2022-07-07
 */
@Mapper
public interface AddressBookMapper extends BaseMapper<AddressBook> {

}
