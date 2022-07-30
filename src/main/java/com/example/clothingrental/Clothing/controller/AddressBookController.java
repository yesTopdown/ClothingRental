package com.example.clothingrental.Clothing.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.example.clothingrental.Clothing.common.AdminContext;
import com.example.clothingrental.Clothing.common.Res;
import com.example.clothingrental.Clothing.entity.AddressBook;
import com.example.clothingrental.Clothing.service.IAddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 地址管理 前端控制器
 * </p>
 *
 * @author lu
 * @since 2022-07-07
 */
@RestController
@RequestMapping("/Clothing/address-book")
@CrossOrigin
public class AddressBookController{

    @Autowired
    IAddressBookService iAddressBookService;
    /**
     * 新增地址
     */
    @PostMapping
    public Res<AddressBook> save(@RequestBody AddressBook addressBook){
        addressBook.setUserId(AdminContext.getUserId());
        if(addressBook.getId()!=null){
            LambdaUpdateWrapper<AddressBook> updateWrapper=new LambdaUpdateWrapper<>();
            updateWrapper.eq(AddressBook::getId,addressBook.getId());
            iAddressBookService.update(addressBook,updateWrapper);
        }else {
            iAddressBookService.save(addressBook);
        }
        return Res.success(addressBook);
    }
    /**
     * 根据id查询地址
     */
    @GetMapping("/{id}")
    public Res<Object> get(@PathVariable Long id){
        AddressBook addressBook=iAddressBookService.getById(id);
        if(addressBook!=null){
            return Res.success(addressBook);
        }else {
            return Res.error("没有地址");
        }
    }

    /**
     * 查询指定会员的全部地址
     */
    @GetMapping("/list")
    public Res<List<AddressBook>> list(){
        //条件构造器
        LambdaQueryWrapper<AddressBook> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(AddressBook::getUserId,AdminContext.getUserId());
        queryWrapper.orderByDesc(AddressBook::getIsDeleted);
        return Res.success(iAddressBookService.list(queryWrapper));
    }

    /**
     * 设置默认地址
     */
    @PutMapping("/default")
    public Res<AddressBook> setDefault(@RequestBody AddressBook addressBook){
        LambdaUpdateWrapper<AddressBook> wrapper=new LambdaUpdateWrapper<>();
        wrapper.eq(AddressBook::getUserId,AdminContext.getUserId()).set(AddressBook::getIsDeleted,0);
        //先把所有用户的默认地址改为0
        iAddressBookService.update(wrapper);
        //在单独修改默认地址
        addressBook.setDefault(true);
        iAddressBookService.updateById(addressBook);
        return Res.success(addressBook);
    }
}
