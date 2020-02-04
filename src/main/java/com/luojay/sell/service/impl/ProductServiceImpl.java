package com.luojay.sell.service.impl;

import com.luojay.sell.dataobject.ProductInfo;
import com.luojay.sell.dto.CartDTO;
import com.luojay.sell.enums.ProductStatusEnum;
import com.luojay.sell.enums.ResultEnum;
import com.luojay.sell.exception.SellException;
import com.luojay.sell.repository.ProductInfoRepository;
import com.luojay.sell.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductInfoRepository repository;
    @Override
    public ProductInfo findById(String id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save(productInfo);
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        //此处存在多线程安全问题，后面会用分布式锁优化
        //减库存操作前先查询库存，不够就回滚事务
        for (CartDTO cartDTO:cartDTOList) {
            ProductInfo productInfo = repository.findById(cartDTO.getProductId()).orElse(null);
            if(productInfo==null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            int result = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if(result<0){
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(result);
            repository.save(productInfo);
        }
    }
    @Override
    public void increaseStock(List<CartDTO> cartDTOList) {

    }
}
