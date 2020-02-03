package com.luojay.sell.service.impl;

import com.luojay.sell.dataobject.ProductInfo;
import com.luojay.sell.enums.ProductStatusEnum;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ProductServiceImplTest {
    @Autowired
    private ProductServiceImpl productService;
    @Test
    void findById() {
        ProductInfo productInfo = productService.findById("123456");
        Assert.assertEquals("123456",productInfo.getProductId());
    }

    @Test
    void findUpAll() {
        List<ProductInfo> upResultList = productService.findUpAll();
        Assert.assertNotEquals(0,upResultList.size());
    }

    @Test
    void findAll() throws Exception{
        PageRequest pageRequest =  PageRequest.of(0, 2);
        Page<ProductInfo> productInfoPage = productService.findAll(pageRequest);
        //System.out.println(productInfoPage.getTotalElements());
        Assert.assertNotEquals(0,productInfoPage.getTotalElements());
    }

    @Test
    void save() {
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123457");
        productInfo.setProductName("艇仔粥");
        productInfo.setProductPrice(new BigDecimal(6));
        productInfo.setProductStock(20);
        productInfo.setProductStatus(ProductStatusEnum.Down.getCode());
        productInfo.setProductIcon("http://xxxxx.jpg");
        productInfo.setCategoryType(2);
        productInfo.setProductDescription("很好吃的艇仔粥");
        ProductInfo result = productService.save(productInfo);
        Assert.assertNotNull(result);
    }
}