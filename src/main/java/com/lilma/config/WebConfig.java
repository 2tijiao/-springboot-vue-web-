package com.lilma.config;

import com.lilma.interceptors.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//在系统过滤器注册令牌的过滤器
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //设置用户登录、注册，管理员登录接口不拦截
        registry.addInterceptor(loginInterceptor).excludePathPatterns("/user/login","/user/register","/administer/login");
    }
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 允许所有域名进行跨域访问，实际使用时应根据需要进行配置
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE","OPTIONS")
                .allowedHeaders("*");
    }
}
