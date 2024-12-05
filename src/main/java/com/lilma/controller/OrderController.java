package com.lilma.controller;


import com.lilma.pojo.Order;
import com.lilma.pojo.Result;
import com.lilma.pojo.Rorder;
import com.lilma.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/order")
public class OrderController {
    //注入OrderService对象
    @Autowired
    private OrderService orderService;

    //在用户端显示该用户的订单列表
    @GetMapping("/usershow")
    public Result usershow(){
        List<Order> lists=orderService.usershow();
        return Result.success(lists);
    }

    //在管理员端显示所有用户的订单列表
    @GetMapping("/adminshow")
    public Result adminshow(){
        List<Order>lists=orderService.adminshow();
        return Result.success(lists);
    }

    //在用户端实现收货功能，将商品状态改为已收货
    @GetMapping("/changestate")
    public Result changestate(Integer id){
        orderService.changestate(id);
        return Result.success();
    }

    //将所有的订单进行分类统计，统计每一类的总数量、总销售额、占比等
    @GetMapping("/ordersta")
    public Result ordersta(){
        List<Rorder>list=orderService.ordersta();
        return Result.success(list);
    }

    //管理员实现删除指定id的订单信息
    @GetMapping("/delete")
    public Result deleteorder(Integer id){
        orderService.deleteorder(id);
        return Result.success();
    }

    //管理员修改指定id订单的信息
    @PutMapping("/alter")
    public Result alterorder(@RequestBody Order order){
        orderService.alterorder(order);
        return Result.success();
    }

    //管理员实现搜索指定用户或指定商品的订单信息
    @PostMapping("/adminsearch")
    public Result adminsearch(String username, String goodsname){
        List<Order>list=orderService.search(username,goodsname);
        return Result.success(list);
    }

    //用户搜索指定商品的订单信息
    @GetMapping("/usersearch")
    public Result usersearch(String goodsname){
        List<Order>list=orderService.usersearch(goodsname);
        return Result.success(list);
    }

}
