package com.luojay.sell.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * 订单详情实体类
 */
@Entity
@Data
@DynamicUpdate
public class OrderDetail {
    /*`detail_id` varchar(32) not null,
    `order_id` varchar(32) not null,
    `product_id` varchar(32) not null,
    `product_name` varchar(64) not null comment '商品名称',
    `product_price` decimal(8,2) not null comment '当前价格,单位分',
    `product_quantity` int not null comment '数量',
    `product_icon` varchar(512) comment '小图',
    `create_time` timestamp not null default current_timestamp comment '创建时间',
    `update_time` timestamp not null default current_timestamp on update current_timestamp comment '修改时间',*/
    @Id
    private String detailId;
    //订单主表ID
    private String orderId;
    //商品ID
    private String productId;
    //商品名称
    private String productName;
    //当前价格,单位分
    private BigDecimal productPrice;
    //数量
    private Integer productQuantity;
    //小图
    private String productIcon;
}
