package com.example.clothingrental.Clothing.common;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 *通用返回结果类，服务端响应的数据最终都会封装成此对象
 */
@Data
public class Res<T> {
    private Integer code; //编码：1成功，0和其它数字为失败

    private String msg; //错误信息

    private T data; //数据

    private Map map = new HashMap(); //动态数据

    public static <T> Res<T> success(T object) {
        Res<T> res = new Res<T>();
        res.data = object;
        res.code = 1;
        return res;
    }

    public static <T> Res<T> error(String msg) {
        Res res = new Res();
        res.msg = msg;
        res.code = 0;
        return res;
    }
}
