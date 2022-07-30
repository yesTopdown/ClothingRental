package com.example.clothingrental.Clothing.Vo;

import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 小仙女
 * @create 2022-07-30 9:30
 * 用户登录填入的信息
 */
@Data
@NoArgsConstructor
public class LoginVo {
    @NotNull
    private String name;
    @NotNull
    private String password;
}
