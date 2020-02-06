package com.luojay.sell.dto;

import com.luojay.sell.dataobject.OrderDetail;
import com.luojay.sell.enums.OrderStatusEnum;
import com.luojay.sell.enums.PayStatusEnum;
import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 跟订单主表的实体类字段一致
 * 封装成DTO是为了更好的处理
 * 一个主订单对应多个详情订单
 */
@Data
public class OrderDTO {
    @Id
    private String orderId;
    // 买家名字
    private String buyerName;
    //买家电话
    private String buyerPhone;
    //买家地址
    private String buyerAddress;
    //买家微信openid
    private String buyerOpenid;
    //订单总金额
    private BigDecimal orderAmount ;
    //订单状态, 默认0为新下单
    private Integer orderStatus ;
    //支付状态, 默认0为未支付
    private Integer payStatus ;
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;
    //为了更好查询一对多的详情订单
    private List<OrderDetail> orderDetailList;
}
