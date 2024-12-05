package com.lilma.service;

import com.lilma.pojo.Administer;
import com.lilma.pojo.User;

//Service层接口
public interface AdministerService {
    //包含一个方法，用于查找指定用户名的管理员是否存在，并返回该类型的数据
    Administer findByUserName(String name);
}
