package com.example.clothingrental.Clothing.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author lu
 * @since 2022-07-07
 */
@Data
@Accessors(chain = true)
public class ClothesOrders {
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO,value = "id")
    private Long id;
    /**
     * 订单号
     */
    private String number;

    /**
     * 订单状态 1待付款，2待派送，3已派送，4已完成，5已取消
     */
    private Integer status;

    /**
     * 下单用户
     */
    private Long userId;

    /**
     * 地址id
     */
    private Long addressBookId;

    /**
     * 下单时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime orderTime;

    /**
     * 结账时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime checkoutTime;

    /**
     * 支付方式 1微信,2支付宝
     */
    private Integer payMethod;

    /**
     * 实收租金额
     */
    private BigDecimal amount;

    /**
     * 备注
     */
    private String remark;

    private String phone;

    private String address;

    private String userName;

    private String consignee;

    /**
     * 管理员操作状态 0:未确认，1:确认
     */
    private Integer managerStatus;

}
