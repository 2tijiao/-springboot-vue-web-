package com.lilma.service;

import com.lilma.pojo.Order;
import com.lilma.pojo.User;

import java.util.List;

public interface UserService {
    //查找指定用户名的用户
    User findByUserName(String username);

    //用户注册
    void register(String name, String pwd,String email);

    //用户注销
    void delete(Integer id);

    //显示所有用户信息
    List<User> allusers();

    //查找指定用户id的订单信息
    List<Order> usershow(Integer id);

    //模糊查找指定用户名的用户信息
    List<User> search(String name);
}
