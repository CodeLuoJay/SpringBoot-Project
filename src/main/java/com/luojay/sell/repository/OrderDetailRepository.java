package com.luojay.sell.repository;

import com.luojay.sell.dataobject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {
    //根据订单主表拿到订单id再根据订单id查询订单详情记录（一对多关系），所以返回列表形式的订单详情
    List<OrderDetail> findByOrderId(String orderId);

}
