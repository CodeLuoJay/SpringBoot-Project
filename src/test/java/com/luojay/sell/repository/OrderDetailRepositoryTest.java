package com.luojay.sell.repository;

import com.luojay.sell.dataobject.OrderDetail;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class OrderDetailRepositoryTest {
    @Autowired
    private OrderDetailRepository repository;
    @Test
    public void saveTest(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrderId("123456");
        orderDetail.setDetailId("12345678");
        orderDetail.setProductId("123457");
        orderDetail.setProductName("艇仔粥");
        orderDetail.setProductPrice(new BigDecimal(3));
        orderDetail.setProductQuantity(3);
        orderDetail.setProductIcon("http://xxxx.jpg");
        OrderDetail result  = repository.save(orderDetail);
        Assert.assertNotNull(result);
    }
    @Test
    void findByOrderId() {
        List<OrderDetail> orderDetailList = repository.findByOrderId("123456");
        Assert.assertNotEquals(0,orderDetailList.size());
    }
}