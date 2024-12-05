package com.lilma.mapper;

import com.lilma.pojo.Order;
import com.lilma.pojo.Rorder;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface OrderMapper {
    // 根据用户ID查询该用户的所有订单，并按订单状态排序
    @Select("select* from web_db.`order` where userid=#{id} order by state")
    List<Order> usershow(Integer id);

    // 查询所有订单，并按订单状态排序
    @Select("select * from web_db.`order` order by state")
    List<Order> adminshow();

    // 将指定ID的订单状态更新为1
    @Update("update web_db.`order` set state=1 where id=#{id}")
    void changestate(Integer id);

    // 统计每个商品名称的总数量、总金额以及占总销售额的比例，只计算状态为1的订单
    @Select("select goodsname,sum(goodsnum) as goodsnum,sum(goodsprice * goodsnum) as goodssum,(sum(goodsprice * goodsnum) / (select sum(goodsprice * goodsnum) from web_db.order))as ratio from web_db.order where state=1 group by goodsname")
    List<Rorder> ordersta();

    // 根据ID删除指定的订单
    @Delete("delete from web_db.`order` where id=#{id}")
    void deleteorder(Integer id);

    //更新订单的商品数量和状态
    @Update("update web_db.`order` set goodsnum=#{goodsnum},state=#{state} where id=#{id}")
    void alterorder(Order order);

    // 根据用户名和商品名称搜索订单
    List<Order> search(@Param("username")String username, @Param("goodsname")String goodsname);

    // 根据用户ID和商品名称模糊搜索订单
    @Select("select * from web_db.`order` where userid=#{id} and goodsname LIKE CONCAT('%', #{goodsname}, '%')")
    List<Order> usersearch(@Param("id")Integer id, @Param("goodsname")String goodsname);
}
