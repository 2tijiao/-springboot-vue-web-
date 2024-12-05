package com.lilma.service.impl;

import com.lilma.mapper.GoodsMapper;
import com.lilma.pojo.Goods;
import com.lilma.service.GoodsService;
import com.lilma.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GoodsServiceimpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMpper;

    @Override
    //调用mapper层方法，向数据库的商品表中添加商品信息
    public void add(Goods goods) {
        goodsMpper.add(goods);
    }

    @Override
    //调用mapper层方法，向商品表中指定商品信息进行更新
    public void category(Goods goods) {
        goodsMpper.category(goods);
    }

    @Override
    //调用mapper层方法，在商品表中删除指定id的商品信息
    public void delete(Integer id) {
        goodsMpper.delete(id);
    }

    @Override
    //调用mapper层方法，获取所有商品的信息
    public List<Goods> show() {
        return goodsMpper.show();

    }

    @Override
    //调用mapper层方法，获取与指定商品名匹配的商品信息
    public List<Goods> test(String name) {
        return goodsMpper.test(name);
    }

    @Override
    //通过线程获取登录用户的用户id，再调用mapper层方法，向购物车表添加相关信息
    public void addtotrolly(Integer goodsid, Integer num) {
        Map<String,Object> claim= ThreadLocalUtil.get();
        Integer userid=(Integer)claim.get("id");
        goodsMpper.addtotrolly(userid,goodsid,num);

    }
}
