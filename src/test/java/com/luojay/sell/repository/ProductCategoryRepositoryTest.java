package com.luojay.sell.repository;

import com.luojay.sell.dataobject.ProductCategory;


import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class ProductCategoryRepositoryTest {
    @Autowired
    private ProductCategoryRepository repository;
    @Test
    public void findOneTest(){
        ProductCategory productCategory = repository.findById(1).orElse(null);
        System.out.println(productCategory);
    }
    @Test
    //@Transactional 如果想单纯测试通不通过而不添加数据，可以加这个事务注解，只对测试有效完全回滚
    public void saveTest(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("kid最爱");
        productCategory.setCategoryType(8);
        ProductCategory result = repository.save(productCategory);
        Assert.assertNotEquals(null,result);
    }
    @Test
    public void updateTest(){
        ProductCategory productCategory = repository.findById(4).orElse(null);
        productCategory.setCategoryName("老年最爱");
        productCategory.setCategoryType(6);
        System.out.println(repository.save(productCategory));
    }
    @Test
    public void findByCategoryTypeInTest(){
        List<Integer> list = Arrays.asList(2, 3, 4, 5, 6);
        List<ProductCategory> result = repository.findByCategoryTypeIn(list);
        Assert.assertNotEquals(0,result.size());
    }
}