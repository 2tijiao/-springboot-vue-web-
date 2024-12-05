package com.lilma.service.impl;


import com.lilma.mapper.OrderMapper;
import com.lilma.pojo.Order;
import com.lilma.pojo.Rorder;
import com.lilma.service.OrderService;
import com.lilma.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OrderServiceimpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    //获取当前用户的订单列表
    @Override
    public List<Order> usershow() {
        //通过线程获取当前用户id
        Map<String,Object> claim= ThreadLocalUtil.get();
        Integer id=(Integer)claim.get("id");
        return orderMapper.usershow(id);
    }

    //获取所有订单列表，供管理员查看
    @Override
    public List<Order> adminshow() {
        return orderMapper.adminshow();
    }

    //更改指定订单的状态
    @Override
    public void changestate(Integer id) {
        orderMapper.changestate(id);
    }

    // 获取订单状态统计信息
    @Override
    public List<Rorder> ordersta() {
        return orderMapper.ordersta();
    }

    //删除指定ID的订单
    @Override
    public void deleteorder(Integer id) {
        orderMapper.deleteorder(id);
    }

    // 修改订单信息
    @Override
    public void alterorder(Order order) {
        orderMapper.alterorder(order);
    }

    //管理员根据用户名和商品名称搜索订单
    @Override
    public List<Order> search(String username, String goodsname) {
        return orderMapper.search(username, goodsname);
    }

    //当前用户根据商品名称搜索订单
    @Override
    public List<Order> usersearch(String goodsname) {
        Map<String,Object> claim= ThreadLocalUtil.get();
        Integer id=(Integer)claim.get("id");
        return orderMapper.usersearch(id, goodsname);
    }
}
