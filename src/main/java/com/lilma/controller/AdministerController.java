package com.lilma.controller;


import com.lilma.pojo.Administer;
import com.lilma.pojo.Result;
import com.lilma.pojo.User;
import com.lilma.service.AdministerService;
import com.lilma.utils.jwtUtil;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


//这是一个Spring注解，用于声明一个类是一个控制器，并且返回的数据会自动以JSON或XML等格式响应给客户端。
@RestController
//这个注解用于映射Web请求到控制器的处理方法上。这里指定了所有以/administer开头的请求都会由这个控制器处理。
@RequestMapping("/administer")
public class AdministerController {

    //注入adminService层
    @Autowired
    private AdministerService administerService;

    //管理员登录方法
    @PostMapping("/login")
    public Result login(String name,String pwd){
        //检查登录用户名是否正确
        Administer u=administerService.findByUserName(name);
        //用户不存在时
        if(u==null){
            return Result.error("非开发者登录");
        }
        //用户名存在时，检查密码是否正确
        //创建一个claims的Map，存放管理员的id和用户名。
        //使用jwtUtil.genToken(claims)方法根据claims生成一个JWT令牌。
        //返回一个成功的结果，包含生成的JWT令牌。
        if(pwd.equals(u.getPwd())){
            Map<String,Object> claims=new HashMap<>();
            claims.put("id",u.getId());
            claims.put("name",u.getName());
            String token=jwtUtil.genToken(claims);
            return Result.success(token);
        }
        return Result.error("密码错误");
    }


}
