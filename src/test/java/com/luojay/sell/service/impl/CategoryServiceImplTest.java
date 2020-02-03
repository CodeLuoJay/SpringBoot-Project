package com.luojay.sell.service.impl;

import com.luojay.sell.dataobject.ProductCategory;
import com.luojay.sell.repository.ProductCategoryRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CategoryServiceImplTest {
    @Autowired
    private ProductCategoryRepository repository;
    @Test
    void findById() {
        ProductCategory result = repository.findById(2).orElse(null);
        Assert.assertNotEquals(null,result);
    }

    @Test
    void findAll() {
        List<ProductCategory> categoryList = repository.findAll();
        Assert.assertNotEquals(0,categoryList.size());
    }

    @Test
    void findByCategoryTypeIn() {
        List<Integer> categoryGroup = Arrays.asList(2, 4, 6, 8, 10);
        List<ProductCategory> categoryTypeIn = repository.findByCategoryTypeIn(categoryGroup);
        Assert.assertNotEquals(0,categoryTypeIn.size());
    }

    @Test
    @Transactional
    void save() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("女神最爱");
        productCategory.setCategoryType(9);
        ProductCategory category = repository.save(productCategory);
        Assert.assertNotEquals(null,category);
    }
}