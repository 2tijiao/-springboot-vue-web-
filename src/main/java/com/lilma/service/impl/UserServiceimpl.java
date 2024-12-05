package com.lilma.service.impl;

import com.lilma.mapper.UserMapper;
import com.lilma.pojo.Order;
import com.lilma.pojo.User;
import com.lilma.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceimpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    //查找指定用户名方法
    @Override
    public User findByUserName(String name) {
        //直接返回mapper层在数据库中的查询结果
        return userMapper.find(name);
    }

    //注册方法
    @Override
    //直接在mapper层中增加注册的用户信息
    public void register(String name, String pwd,String email) {
        userMapper.add(name,pwd,email);
    }


    //注销方法
    @Override
    //直接在mapper层中删除指定id的用户信息
    public void delete(Integer id) {
        userMapper.delete(id);
    }

    //显示所有用户方法
    @Override
    //直接返回mapper层中所有用户的查询结果
    public List<User> allusers() {
        return userMapper.allusers();
    }

    //显示指定id的用户的订单方法
    @Override
    //直接返回的mapper层中对指定id的用户的订单查询结果
    public List<Order> usershow(Integer id) {
        return userMapper.usershow(id);
    }

    //搜索指定用户名的用户（与第一个不同，这个进行模糊匹配）
    @Override
    //直接返回mapper层对指定用户名的用户的查询结果
    public List<User> search(String name) {
        return userMapper.search(name);
    }

}
