package com.luojay.sell.service;

import com.luojay.sell.dataobject.ProductCategory;

import java.util.List;

public interface CategoryService {
    //查询单一类目
    ProductCategory findById(Integer id);
    //查询所有类目
    List<ProductCategory> findAll();
    //查询在列表中的类目
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
    //更新保存方法
    ProductCategory save(ProductCategory productCategory);

}
