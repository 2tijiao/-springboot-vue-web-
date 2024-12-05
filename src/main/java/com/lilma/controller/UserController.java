package com.lilma.controller;

import com.lilma.pojo.Order;
import com.lilma.pojo.Result;
import com.lilma.pojo.User;
import com.lilma.service.UserService;
import com.lilma.service.impl.UserServiceimpl;
import com.lilma.utils.ThreadLocalUtil;
import com.lilma.utils.jwtUtil;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    //用户注册
    @PostMapping("/register")
    public Result register(String name,String pwd,String email){
        //调用userService的findByUserName方法，传入用户名name作为参数，查询数据库中是否已存在该用户名
        User u= userService.findByUserName(name);
        //如果查询结果u为null，表示数据库中没有找到该用户名，即该用户名可用。
        if(u==null){
            //如果用户名不存在，调用userService的register方法，
            // 传入用户名、密码和电子邮件地址，执行注册操作。
            // 这个方法会在数据库中创建一个新的用户记录。
            userService.register(name,pwd,email);
            return Result.success();
        }
        //如果用户名已存在（即u不为null），则返回一个错误的Result对象，提示“该用户名已存在”。
        else{
            return Result.error("该用户名已存在");
        }
    }

    //用户登录
    @PostMapping("/login")
    public Result login(String name,String pwd){
        //这行代码调用userService的findByUserName方法，传入用户名name作为参数，查询数据库中是否存在该用户名。
        User u=userService.findByUserName(name);
        //如果查询结果u为null，表示数据库中没有找到该用户名，即该用户未注册。
        //如果用户未注册，则返回一个错误的Result对象，提示“请先完成注册”。
        if(u==null){
            return Result.error("请先完成注册");
        }
        //如果用户已注册（即u不为null），则继续检查传入的密码pwd是否与数据库中存储的密码相匹配。
        if(pwd.equals(u.getPwd())){
            //创建一个claims的Map，用于存放生成JWT令牌时需要的声明。
            //将用户的ID作为声明放入claims中。
            //将用户的姓名作为声明放入claims中。
            //使用jwtUtil的genToken方法，根据claims生成一个JWT（JSON Web Token）令牌。
            //如果密码正确，返回一个成功的Result对象，并将生成的JWT令牌作为数据返回。
            Map<String,Object> claims=new HashMap<>();
            claims.put("id",u.getId());
            claims.put("name",u.getName());
            String token=jwtUtil.genToken(claims);
            return Result.success(token);
        }
        //密码错误则返回错误信息
        return Result.error("密码错误");
    }


    //查看用户信息
    @GetMapping("/info")
    public Result userInfo(/*@RequestHeader(name = "Authorization") String token*/){
        /*Map<String,Object>claims=new HashMap<>();
        claims=jwtUtil.parseToken(token);
        String name=(String)claims.get("name");*/
        //获取线程中的token解析对象
        Map<String,Object>map= ThreadLocalUtil.get();
        //获取登陆的用户名
        String name=(String)map.get("name");
        User u=userService.findByUserName(name);
        return Result.success(u);
    }


    //用户注销
    @PostMapping("/delete")
    public Result delete(){
        //这行代码调用了工具类ThreadLocalUtil的get方法，
        // 获取当前线程的局部变量map。
        // ThreadLocal是一种线程局部存储，可以用来存储每个线程的私有数据，
        // 这里的map存储了当前用户会话的信息。
        Map<String,Object>map=ThreadLocalUtil.get();
        Integer id=(Integer)map.get("id");
        //调用userService的delete方法，传入获取到的id，执行删除操作。
        userService.delete(id);
        return Result.success();
    }

    //在管理员端展示所有的用户信息
    @GetMapping("/allusers")
    public Result allusers(){
        //这行代码调用userService的allusers方法，获取所有用户的列表。并返回给前端
            List<User> u = userService.allusers();
            return Result.success(u);
    }

    //显示指定id的用户的购买信息
    @GetMapping("/usershow")
    public Result usershow(Integer id){
        //这行代码调用userService的usershow方法，传入用户ID作为参数，以获取该用户的所有订单信息。
        //并返回给前端
        List<Order> lists=userService.usershow(id);
        return Result.success(lists);
    }

    //搜索指定名字的用户信息
    @GetMapping("/search")
    public Result search(String name){
        //这行代码调用userService的search方法，传入用户姓名作为参数，以获取该用户的用户信息。
        //并返回给前端
        List<User> u=userService.search(name);
        return Result.success(u);
    }

}
