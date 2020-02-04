package com.luojay.sell.service.impl;

import com.luojay.sell.dataobject.OrderDetail;
import com.luojay.sell.dataobject.OrderMaster;
import com.luojay.sell.dataobject.ProductInfo;
import com.luojay.sell.dto.CartDTO;
import com.luojay.sell.dto.OrderDTO;
import com.luojay.sell.enums.OrderStatusEnum;
import com.luojay.sell.enums.PayStatusEnum;
import com.luojay.sell.enums.ResultEnum;
import com.luojay.sell.exception.SellException;
import com.luojay.sell.repository.OrderDetailRepository;
import com.luojay.sell.repository.OrderMasterRepository;
import com.luojay.sell.service.OrderService;
import com.luojay.sell.service.ProductService;
import com.luojay.sell.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        //创建订单逻辑
        String orderId = KeyUtil.generateUnniqueKey();
        //定义订单总价初始化为0
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);

/*        List<CartDTO> cartDTOList = new ArrayList<>();*/

        //1.查询商品(数量，价格)
        for(OrderDetail orderDetail:orderDTO.getOrderDetailList()){
            ProductInfo productInfo = productService.findById(orderDetail.getProductId());
            if (productInfo==null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //2.计算总价:商品单价*商品数量=单个商品总价 订单总价=单个商品总价+上一个商品累计的订单总价(初始化为零)
            orderAmount= productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);
            //3.1订单详情入库(orderMaster和orderDetail)
            BeanUtils.copyProperties(productInfo,orderDetail);
            orderDetail.setOrderId(orderId);
            orderDetail.setDetailId(KeyUtil.generateUnniqueKey());
            //设置订单的商品名字等属性

            orderDetailRepository.save(orderDetail);

/*            CartDTO cartDTO = new CartDTO(orderDetail.getProductId(), orderDetail.getProductQuantity());
            cartDTOList.add(cartDTO);*/
        }
        //3.2订单写入数据库
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO,orderMaster);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount);
        orderMasterRepository.save(orderMaster);
        //4.扣库存(方式二lamdba表达式)
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList()
                .stream().map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productService.decreaseStock(cartDTOList);
        return orderDTO;
    }

    @Override
    public OrderDTO findById(String orderId) {
        return null;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        return null;
    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        return null;
    }
}
