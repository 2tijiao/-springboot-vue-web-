package com.lilma.controller;

import com.lilma.pojo.Result;
import com.lilma.pojo.Trolly;
import com.lilma.service.TrollyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/trolly")
public class TrollyController {

    //注入TrollyService对象
    @Autowired
    private TrollyService trollyService;

    //用于获取登陆用户的购物车信息
    @GetMapping("/list")
    public Result list(){
        List<Trolly>list=trollyService.list();
        return Result.success(list);
    }

    //当顾客结算购物车商品时，实现先删除购物车商品然后加入商品订单，然后向顾客发送购物成功的电子邮件
    @GetMapping("/finish")
    @ResponseBody
    public Result finish(@RequestParam List<Integer>lists){
       trollyService.finish(lists);
       trollyService.semail();
        return Result.success();
    }

    //用户删除指定id的购物车商品
    @GetMapping("/delete")
    public Result delete(Integer id){
        trollyService.delete(id);
        return Result.success();
    }

    //用户更新指定购物车商品的商品数量
    @PostMapping("/update")
    public Result update(Integer id,Integer goodsnum){
        trollyService.update(id,goodsnum);
        return Result.success();
    }

    //用户在购物车中搜索指定名字的商品
    @GetMapping("/search")
    public Result search(String goodsname){
        List<Trolly>lists=trollyService.search(goodsname);
        return Result.success(lists);
    }

}
