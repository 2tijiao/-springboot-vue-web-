package com.lilma.mapper;

import com.lilma.pojo.Trolly;
import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface TrollyMapper {


    // 获取用户购物车中的所有商品
    @Select("select *from web_db.trolly where userid=#{id}")
    List<Trolly> list(Integer id);

    // 根据用户ID获取用户的电子邮件地址
    @Select("select email from web_db.users where id=#{id}")
    String getemail(Integer id);

    // 根据购物车ID删除购物车中的单个商品
    @Delete("delete from web_db.trolly where id=#{list}")
    void finish(Integer list);

    // 将购物车中的商品添加到订单中
    @Insert("insert into web_db.order(goodsid,userid,username,goodsname,goodsprice, goodsnum,state)values (#{goodsid},#{userid},#{username},#{goodsname},#{goodsprice},#{goodsnum},0)")
    void addtoorder(@Param("goodsid")Integer goodsid, @Param("userid")Integer userid, @Param("username")String username,@Param("goodsname")String goodsname,@Param("goodsprice")BigDecimal goodsprice, @Param("goodsnum")Integer goodsnum);

    // 根据购物车ID查询商品名称
    @Select("select goodsname from web_db.trolly where id=#{list}")
    String findGoodsname(Integer list);

    // 根据购物车ID查询商品价格
    @Select("select goodsprice from web_db.trolly where id=#{list}")
    BigDecimal findGoodsprice(Integer list);

    // 根据购物车ID查询商品数量
    @Select("select goodsnum from web_db.trolly where id=#{list}")
    Integer findGoodsnum(Integer list);

    // 根据购物车ID查询商品ID
    @Select("select goodsid from web_db.trolly where id=#{list}")
    Integer findGoodsid(Integer list);

    // 根据ID删除购物车中的单个商品
    @Delete("delete from web_db.trolly where id=#{id}")
    void delete(Integer id);

    // 更新购物车中商品的数量和总价
    @Update("update web_db.trolly set goodsnum=#{goodsnum},total=goodsnum*goodsprice where id=#{id}")
    void update(@Param("id")Integer id, @Param("goodsnum")Integer goodsnum);

    // 根据商品名称模糊搜索购物车中的商品
    @Select("SELECT * FROM web_db.trolly WHERE goodsname LIKE CONCAT('%', #{goodsname}, '%')")
    List<Trolly> search(String goodsname);
}
