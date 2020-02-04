package com.luojay.sell.repository;

import com.luojay.sell.dataobject.OrderMaster;
import com.luojay.sell.enums.OrderStatusEnum;
import com.luojay.sell.enums.PayStatusEnum;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class OrderMasterRepositoryTest {
    private final String OPENID = "110123";
    @Autowired
    private OrderMasterRepository repository;
    @Test
    public void saveTest(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("1234567");
        orderMaster.setBuyerName("Codeluojay");
        orderMaster.setBuyerPhone("13813813812");
        orderMaster.setBuyerOpenid("abc123");
        orderMaster.setBuyerAddress("广州");
        orderMaster.setOrderAmount(new BigDecimal(10.00));
        orderMaster.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        OrderMaster result = repository.save(orderMaster);
        Assert.assertNotNull(result);
    }
    @Test
    void findByBuyerOpenid() {
        PageRequest pageRequest = PageRequest.of(0, 3);
        Page<OrderMaster> result = repository.findByBuyerOpenid(OPENID, pageRequest);
        System.out.println(result.getTotalElements());

    }
}