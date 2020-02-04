package com.luojay.sell.repository;

import com.luojay.sell.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMasterRepository extends JpaRepository<OrderMaster,String> {
    //根据某个人的微信id查询出订单
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);
}
