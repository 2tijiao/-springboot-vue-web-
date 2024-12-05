package com.lilma.pojo;

import lombok.Data;

import java.math.BigDecimal;


@Data
public class Order {
    // 订单ID
    private Integer id;
    // 商品ID
    private Integer goodsid;
    // 用户ID
    private Integer userid;
    // 用户名
    private String username;
    // 商品数量
    private Integer goodsnum;
    // 商品价格
    private BigDecimal goodsprice;
    // 订单状态（例如：0-未收货，1-已收货）
    private Integer state;
    // 商品名称
    private String goodsname;

}

