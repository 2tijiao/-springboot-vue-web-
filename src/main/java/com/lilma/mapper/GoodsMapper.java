package com.lilma.mapper;

import com.lilma.pojo.Goods;
import org.apache.ibatis.annotations.*;

import java.util.*;

@Mapper
public interface GoodsMapper {

    //传入一个Goods对象时，MyBatis通过插入注释，将该对象的price、type和name属性插入到web_db数据库的goods表中。
    @Insert("insert into web_db.goods(price, type, name) values (#{price},#{type},#{name})")
    void add(Goods goods);

    //传入Goods对象，MyBatis通过更新注释，更新表中指定id的商品信息
    @Update("update web_db.goods set price=#{price},type=#{type},name=#{name} where id=#{id}")
    void category(Goods goods);

    //传入商品id，通过mybaits注释在商品表中删除指定商品信息
    @Delete("delete from web_db.goods where id=#{id}")
    void delete(Integer id);

    //通过mybaits注释在商品表中查询所有商品信息
    @Select("select * from web_db.goods")
    List<Goods> show();

    //在mybaits的xml文件中进行动态SQL编写
    List<Goods> test(String name);

    //通过mybaits注解在购物车表中插入指定商品信息
    @Insert("insert into web_db.trolly (userid,goodsnum,goodsid,goodsname,goodsprice,total) select #{userid},#{num},id,name,price,sum(#{num}*price) from web_db.goods where id=#{goodsid}")
    void addtotrolly(@Param("userid") Integer userid, @Param("goodsid") Integer goodsid, @Param("num") Integer num);
}
