package com.lilma.pojo;

import lombok.Data;

import java.math.BigDecimal;


@Data
public class Trolly {
    // 购物车商品ID
    private Integer id;
    // 用户ID
    private Integer userid;
    // 商品ID
    private Integer goodsid;
    // 商品数量
    private Integer goodsnum;
    // 商品名称
    private String goodsname;
    // 商品价格
    private BigDecimal goodsprice;
    // 商品总价
    private BigDecimal total;
}
