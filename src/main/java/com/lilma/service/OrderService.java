package com.lilma.service;

import com.lilma.pojo.Order;
import com.lilma.pojo.Rorder;

import java.util.List;

public interface OrderService {
    //显示用户的所有订单
    List<Order> usershow();
    //显示管理员的所有订单
    List<Order> adminshow();
    // 改变订单状态
    void changestate(Integer id);
    //显示订单状态统计信息
    List<Rorder> ordersta();
    //删除指定ID的订单
    void deleteorder(Integer id);
    // 修改订单信息
    void alterorder(Order order);
    // 在管理员端根据用户名和商品名称搜索订单
    List<Order> search(String username, String goodsname);
    // 在用户端根据商品名称搜索订单
    List<Order> usersearch(String goodsname);
}
