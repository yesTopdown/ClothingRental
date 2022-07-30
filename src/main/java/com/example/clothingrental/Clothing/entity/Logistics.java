package com.example.clothingrental.Clothing.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 物流表
 * </p>
 *
 * @author lu
 * @since 2022-07-07
 */
@Data
@Accessors(chain = true)
public class Logistics{

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 订单号
     */
    @TableField("orderNo")
    private String orderNo;

    /**
     * 配送单号
     */
    @TableField("sendNo")
    private String sendNo;

    /**
     * 物流状态 1未发货，2已发货，3已送达，4 已返还,
     */
    private Integer sendStatus;

    /**
     * 收货人
     */
    private String receiver;

    /**
     * 送货地址
     */
    private String sendAddress;

    /**
     * 配送日期
     */
    private LocalDateTime sendTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 电话
     */
    private String phone;
}
