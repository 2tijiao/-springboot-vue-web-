package com.lilma.mapper;

import com.lilma.pojo.Administer;
import com.lilma.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


//这是一个MyBatis的注解，用于声明一个接口是一个MyBatis的Mapper接口。
// 被@Mapper注解的接口会被MyBatis框架识别，并生成相应的代理实现类，从而允许接口方法直接操作数据库。
@Mapper
public interface AdministerMapper {

    //这是一个MyBatis的注解，用于指定一个SQL查询语句，并将其与接口中的方法关联起来。
    // @Select注解的值是一个SQL查询，这个查询从web_db数据库的administer表中选择所有字段（*），
    // 条件是name字段等于方法参数中的name值。
    //#{name}是MyBatis的参数占位符，
    // 它告诉MyBatis在执行SQL时，将方法参数中的name值替换到这个位置。
    // MyBatis会自动处理参数的传递和预编译，以防止SQL注入攻击。
    @Select("select * from web_db.administer where name=#{name}")
    //这个方法的目的是查找administer表中与给定name匹配的管理员记录，
    // 并返回这个记录的详细信息。
    // 如果找到匹配的记录，它将返回一个包含这些信息的Administer对象；
    // 如果没有找到，通常会返回null。
    Administer findByName(String name);
}
