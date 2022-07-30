package com.example.clothingrental.Clothing.Config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.example.clothingrental.Clothing.common.AdminContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Calendar;

/**
 * 公共字段
 */
@Component
@Slf4j
public class MyMetaObjecthandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("公共字段自动填充");
        if(metaObject.hasGetter("createTime")){
            metaObject.setValue("createTime", LocalDateTime.now());
        }
        if(metaObject.hasGetter("createUser")){
            metaObject.setValue("createUser", AdminContext.getAdminId());
        }
        if(metaObject.hasGetter("updateUser")){

            metaObject.setValue("updateUser", AdminContext.getAdminId());
        }
        if(metaObject.hasGetter("orderTime")){
            //下单时间
            metaObject.setValue("orderTime", LocalDateTime.now());
        }
        if(metaObject.hasGetter("checkoutTime")){
            //付款时间
            metaObject.setValue("checkoutTime", LocalDateTime.now());

        }
        if(metaObject.hasGetter("updateTime")){
            metaObject.setValue("updateTime",LocalDateTime.now());
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        if(metaObject.hasGetter("updateTime")){
            metaObject.setValue("updateTime",LocalDateTime.now());
        }
        if(metaObject.hasGetter("updateUser")){
            metaObject.setValue("updateUser", AdminContext.getAdminId());
        }
    }
}
