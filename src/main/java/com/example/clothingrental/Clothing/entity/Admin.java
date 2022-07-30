package com.example.clothingrental.Clothing.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


/**
 * <p>
 * 管理员信息
 * </p>
 *
 * @author lu
 * @since 2022-07-07
 */
@Data
@Accessors(chain = true)
public class Admin {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 会员账号
     */
    private String name;

    /**
     * 密码
     */
    private String password;


}
