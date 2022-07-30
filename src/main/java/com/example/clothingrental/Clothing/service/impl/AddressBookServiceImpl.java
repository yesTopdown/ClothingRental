package com.example.clothingrental.Clothing.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.clothingrental.Clothing.entity.AddressBook;
import com.example.clothingrental.Clothing.mapper.AddressBookMapper;
import com.example.clothingrental.Clothing.service.IAddressBookService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 地址管理 服务实现类
 * </p>
 *
 * @author lu
 * @since 2022-07-07
 */
@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements IAddressBookService {

}
