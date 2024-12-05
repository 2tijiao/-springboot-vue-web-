package com.lilma.mapper;

import com.lilma.pojo.Order;
import com.lilma.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    //使用注解SQL语句，在用户表中查找指定用户名的用户信息
    @Select("select * from web_db.users where name=#{name}")
    User find(String name);

    //使用注解SQL语句，向用户表插入新的用户信息
    @Insert("insert into web_db.users(name, pwd,email,create_time) values(#{username},#{password},#{email},now())")
    void add(@Param("username") String name, @Param("password") String pwd,@Param("email")String email);

    //使用注解sql语句，在用户表中删除指定id的用户信息
    @Delete("delete from web_db.users where id=#{id}")
    void delete(Integer id);

    //使用注解sql语句，查询所有用户信息
    @Select("select * from web_db.users")
    List<User> allusers();

    //使用注解sql查询指定id用户的订单信息，并按照订单状态排序
    @Select("select* from web_db.order where userid=#{id} order by state")
    List<Order> usershow(Integer id);

    //使用MyBatis框架中的一个XML映射文件，定义数据库操作的SQL语句，具体作用在用户表中进行模糊查询
    List<User> search(String name);
}
