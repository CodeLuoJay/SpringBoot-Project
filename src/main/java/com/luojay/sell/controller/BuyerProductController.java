package com.luojay.sell.controller;

import com.luojay.sell.dataobject.ProductCategory;
import com.luojay.sell.dataobject.ProductInfo;
import com.luojay.sell.service.CategoryService;
import com.luojay.sell.service.ProductService;
import com.luojay.sell.service.impl.CategoryServiceImpl;
import com.luojay.sell.service.impl.ProductServiceImpl;
import com.luojay.sell.utils.ResultVOUtil;
import com.luojay.sell.vo.ProductInfoVO;
import com.luojay.sell.vo.ProductVO;
import com.luojay.sell.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {
    @Autowired
    private ProductServiceImpl productService;
    @Autowired
    private CategoryServiceImpl categoryService;

    @GetMapping("/list")
    public ResultVO list(){
        //1.查询所有上架的商品列表
        List<ProductInfo> productInfoList = productService.findUpAll();

        /*2.获取在架商品列表的所有类目编号封装成列表List 方式一 Java8 lambda表达式 productInfoList转化为流然后获取类别，收集器收集成成categoryTypeList*/
        List<Integer> categoryTypeList = productInfoList.stream().map(e -> e.getCategoryType()).collect(Collectors.toList());

        /*  //方式二for循环方式遍历获取
            List<Integer> categoryTypeList = new ArrayList<>();
            for(ProductInfo productInfo:productInfoList){
            categoryTypeList.add(productInfo.getCategoryType());
        }
        * */

        //3.查询类目列表(一次性查询)
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);

        //4.数据拼装
        //4.1先遍历类目(从外往里装)
        ResultVO resultVO = new ResultVO();

        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory:productCategoryList){
            ProductVO productVO = new ProductVO();
            productVO.setCategoryName(productCategory.getCategoryName());
            productVO.setCategoryType(productCategory.getCategoryType());
            List<ProductInfoVO> productInfoVOList=new ArrayList<>();
            for(ProductInfo productInfo:productInfoList){
                if(productCategory.getCategoryType().equals(productInfo.getCategoryType())){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    //利用BeanUtils的copyProperties方法简化productInfo和productInfoVO同名属性值赋值，减少setter方法
                    BeanUtils.copyProperties(productInfo,productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOList.add(productVO);
        }
        return ResultVOUtil.success(productVOList);
    }

}
