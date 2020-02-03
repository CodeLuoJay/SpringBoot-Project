package com.luojay.sell.service;

import com.luojay.sell.dataobject.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {
    //查询单个商品
    ProductInfo findById(String id);
    //查询在上架的商品的列表
    List<ProductInfo> findUpAll();
    //查询所有商品列表带分页
    Page<ProductInfo> findAll(Pageable pageable);
    //更新添加商品
    ProductInfo save(ProductInfo productInfo);
    //加库存

    //减库存


}
