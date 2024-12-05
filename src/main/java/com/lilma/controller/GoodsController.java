package com.lilma.controller;

import com.lilma.pojo.Goods;
import com.lilma.pojo.Result;
import com.lilma.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@Slf4j
@RestController
@RequestMapping("/goods")
public class GoodsController {
    //注入GoodsService对象
    @Autowired
    private GoodsService goodsService;

    //用于获取所有的商品信息，调用service层的方法
    @GetMapping("/show")
    public Result show(){
        List<Goods>lists=goodsService.show();
        return Result.success(lists);
    }

    //用于增加商品
    @PostMapping("/add")
    //接收一个Goods类型的对象，自动实现转换，调用service层的方法添加商品
    public Result add(@RequestBody Goods goods){
        goodsService.add(goods);
        return Result.success();
    }


    //接收一个Goods类型数据，调用service层方法，实现对Goods对应的id进行数据更新
    @PutMapping("/category")
    public Result category(@RequestBody Goods goods){
        goodsService.category(goods);
        return Result.success();
    }

    //接收Goods的id，调用service层方法，实现删除指定商品
    @GetMapping("/delete")
    public Result delete(Integer id){
        goodsService.delete(id);
        return Result.success();
    }

    //获取要搜索的商品名称（可不完整），调用service层方法实现显示搜索商品
    @PostMapping("/test")
    public Result test(String name){
        List<Goods> lists=goodsService.test(name);
        return Result.success(lists);
    }

    //接收商品id和商品数量，调用service层方法将指定id和指定数量的商品加入购物车
    @PostMapping("/addtotrolly")
    public Result addtotrolly(Integer goodsid,Integer num){
        goodsService.addtotrolly(goodsid,num);
        return Result.success();

    }













}
