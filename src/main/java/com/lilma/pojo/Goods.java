package com.lilma.pojo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Goods {
    //商品id
    private Integer id;
    //商品价格
    private BigDecimal price;
    //商品类型
    private String type;
    //商品名称
    private String name;

}
