package com.luojay.sell.dataobject;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
@Entity
@Data
public class ProductInfo {
    @Id
    private String productId;//商品ID
    private String productName;//商品名称
    private BigDecimal productPrice;//商品单价
    private Integer productStock;//商品库存
    private String productDescription;//商品描述
    private String productIcon;//商品小图
    private Integer productStatus;//商品状态,0正常1下架
    private Integer categoryType;//商品类目编号
}
