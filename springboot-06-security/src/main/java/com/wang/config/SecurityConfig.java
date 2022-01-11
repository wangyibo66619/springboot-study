package com.wang.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

// AOP
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    // 授权
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 首页对所有人都可以访问，功能页只有对应权限的人才能访问
        // 请求授权的规则
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasRole("vip1")
                .antMatchers("/level2/**").hasRole("vip2")
                .antMatchers("/level3/**").hasRole("vip3");


        // 没有权限默认会回到登录页面，需要开启登录的页面
        http.formLogin().loginPage("toLogin").loginProcessingUrl("login");

        http.csrf().disable(); // 关闭csrf功能
        // 注销. 开启了注销功能  跳转到首页
        http.logout().logoutSuccessUrl("/");

        // 开启记住我功能  cookie, 默认保存两周，自定义接收前端参数
        http.rememberMe().rememberMeParameter("remember");
    }

    // 认证
    // 密码编码：PasswordEncoder
    // 在Spring Security 5.0+ 新增了很多加密方法
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // 这些数据应该从数据库中读(现在是从内存中读的)
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("wang").password(new BCryptPasswordEncoder().encode("123")).roles("vip1","vip2")
                .and()
                .withUser("root").password(new BCryptPasswordEncoder().encode("123")).roles("vip1","vip2","vip3")
                .and()
                .withUser("guest").password(new BCryptPasswordEncoder().encode("123")).roles("vip1");
    }
}
