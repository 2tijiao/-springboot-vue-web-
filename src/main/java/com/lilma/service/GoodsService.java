package com.lilma.service;

import com.lilma.pojo.Goods;

import java.util.*;

public interface GoodsService {
    //定义增加商品的方法
    void add(Goods goods);

    //定义更新商品的方法
    void category(Goods goods);

    //定义删除商品的方法
    void delete(Integer id);

    //定义商品展示的方法
    List<Goods> show();

    //定义搜索商品的方法
    List<Goods> test(String name);

    //定义商品添加至购物车的方法
    void addtotrolly(Integer goodsid, Integer num);
}
