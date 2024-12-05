package com.lilma.service.impl;

import com.lilma.mapper.AdministerMapper;
import com.lilma.pojo.Administer;
import com.lilma.pojo.User;
import com.lilma.service.AdministerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//声明一个类是一个服务组件。被@Service注解的类会被Spring容器自动扫描并注册为一个Bean，
// 这意味着Spring可以管理这个类的实例，
// 并且可以在需要时自动注入这个服务到其他组件中。
@Service
public class AdministerServiceimpl implements AdministerService {
    //自动注入Spring容器中的Bean。
    @Autowired
    private AdministerMapper administerMapper;

    //调用了administerMapper的findByName方法，传递了参数name。
    // 这个方法的目的是查找并返回一个与给定用户名匹配的Administer对象。
    // 如果找到匹配的管理员，它将被返回；如果没有找到，通常会返回null。
    @Override
    public Administer findByUserName(String name) {
        return administerMapper.findByName(name);
    }
}
