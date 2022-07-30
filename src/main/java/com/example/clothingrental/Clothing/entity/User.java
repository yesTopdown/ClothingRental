package com.example.clothingrental.Clothing.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户信息
 * </p>
 *
 * @author lu
 * @since 2022-07-07
 */
@Data
@Accessors(chain = true)
public class User {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 会员姓名
     */
    private String name;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 会员续费时长
     */
    private String duestime;

    /**
     * 密码
     */
    private String password;

    /**
     * 是否自动续费 0:不自动，1:自动
     */
    private Boolean dues;

    /**
     * 状态 0:禁用，1:正常
     */
    private Integer status;

}
