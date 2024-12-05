package com.lilma.service;

import com.lilma.pojo.Trolly;

import java.util.List;

public interface TrollyService {
    //获取购物车的商品信息
    List<Trolly> list();
    //当购物完成后，实现对选中的商品id进行添加至订单表和从购物车表删除的操作
    void finish(List<Integer> lists);
    //实现对用户的邮箱发送邮件
    void semail();
    //删除指定id的购物车商品
    void delete(Integer id);
    //更新指定id的购物车商品的商品数量
    void update(Integer id, Integer goodsnum);
    //搜索指定商品名的购物车商品
    List<Trolly> search(String goodsname);
}
