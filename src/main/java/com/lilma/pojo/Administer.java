package com.lilma.pojo;

import lombok.Data;


//这是一个Lombok库提供的注解，
// 用于自动为类的字段生成getter和setter方法、toString() 方法、equals() 方法和 hashCode() 方法。
// 使用 @Data 注解可以减少模板化的代码编写，使代码更加简洁。
@Data
public class Administer {
//管理员id
private Integer id;
//管理员密码
private String pwd;
//管理员用户名
private String name;
}
