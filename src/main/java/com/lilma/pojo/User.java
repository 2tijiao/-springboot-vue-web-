package com.lilma.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.Email;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
public class User {
    //用户id
    private Integer id;
    //用户名
    private String name;
    //这是一个Jackson库提供的注解，用于在序列化对象为JSON时忽略这个字段。
    // 在这个例子中，它被应用于pwd字段，意味着当User对象被转换为JSON时，pwd字段不会被包含在内。
    @JsonIgnore
    //用户密码
    private String pwd;
    //这通常是一个校验注解，用于确保对应的字段符合电子邮件的格式。
    // 如果字段值不符合电子邮件的格式，校验将失败。
    @Email
    //用户邮箱
    private String email;
    //这是Jackson库提供的注解，用于指定日期时间字段的序列化和反序列化格式。
    // 在这个例子中，它被应用于createTime字段，指定日期时间应该按照年-月-日 时:分:秒的格式来显示。
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    //用户创建时间
    private LocalDateTime createTime;

}
