package com.example.clothingrental.Clothing.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.experimental.Accessors;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author lu
 * @since 2022-07-10
 */
@Data
@Accessors(chain = true)
public class Message  {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 消息内容
     */
    private String messbody;

    /**
     * 消息创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 消息题目
     */
    private String title;

    /**
     * 订单号
     */
    private String number;

    /**
     * 下单用户id
     */
    private Long userId;


}
