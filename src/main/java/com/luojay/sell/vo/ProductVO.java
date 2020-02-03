package com.luojay.sell.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
@Data
public class ProductVO {

    @JsonProperty("name")
    //@JsonProperty是json序列化时按照格式输出，同时又能保证VO对象的名字的能够见名知其意
    private String categoryName;
    @JsonProperty("type")
    private Integer categoryType;
    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;
}
