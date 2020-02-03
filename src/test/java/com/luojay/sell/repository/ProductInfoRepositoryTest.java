package com.luojay.sell.repository;

import com.luojay.sell.dataobject.ProductInfo;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ProductInfoRepositoryTest {
    @Autowired
    private ProductInfoRepository repository;
    @Test
    void findByProductStatus() {
        List<ProductInfo> result = repository.findByProductStatus(0);
        Assert.assertNotEquals(0,result.size());
    }
    @Test
    void save(){
        ProductInfo productInfo = new ProductInfo();
        //ID
        productInfo.setProductId("123456");
        //Name
        productInfo.setProductName("皮蛋粥");
        //Price
        productInfo.setProductPrice(new BigDecimal(3.2));
        //Stock
        productInfo.setProductStock(20);
        //Status
        productInfo.setProductStatus(0);
        //Icon
        productInfo.setProductIcon("http://xxxxx.jpg");
        //Type
        productInfo.setCategoryType(2);
        //Description
        productInfo.setProductDescription("很好的粥");
        ProductInfo result = repository.save(productInfo);
        Assert.assertNotNull(result);
    }
}