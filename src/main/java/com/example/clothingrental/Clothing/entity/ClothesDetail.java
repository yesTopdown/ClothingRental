package com.example.clothingrental.Clothing.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 服装信息管理
 * </p>
 *
 * @author lu
 * @since 2022-07-07
 */
@Data
@Accessors(chain = true)
public class ClothesDetail  {

    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 服装名称
     */
    private String name;

    /**
     * 服装价格
     */
    private BigDecimal price;

    /**
     * 服装图片
     */
    private String image;

    /**
     * 描述信息
     */
    private String description;

    /**
     * 服装颜色
     */
    private String color;

    /**
     * 服装季节
     */
    private String seasons;

    /**
     * 服装大小 {S M L}
     */
    private String size;

    /**
     * 租金 /天
     */
    private String rent;

    /**
     * 0 停售 1 起售
     */
    private Integer status;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private Long createUser;

    /**
     * 修改人
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;

    /**
     * 是否删除
     */
    private Integer isDeleted;


}
