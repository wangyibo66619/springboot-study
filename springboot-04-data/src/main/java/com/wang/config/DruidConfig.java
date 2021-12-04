package com.wang.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
public class DruidConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druidDataSource() {
        return new DruidDataSource();
    }


    // 后台监控: web.xml  ServletRegistrationBean
    // 因为SpringBoot 内置了 servlet容器，所以没有web.xml， 替代方法   ServletRegistrationBean
    @Bean
    public ServletRegistrationBean StatViewServlet() {
        ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        // 后台需要有人登录，账号密码配置
        HashMap<String, String> initParameters = new HashMap<>();
        // 增加配置
        initParameters.put("loginUsername","admin"); // 登陆的key是固定的loginUsername   loginPassword
        initParameters.put("loginPassword","123");

        // 允许谁可以访问     如果value为"" 表示谁都可以访问
        initParameters.put("allow","");

        bean.setInitParameters(initParameters);// 设置初始化参数

        return bean;
    }


    //  filter 过滤
    @Bean
    public FilterRegistrationBean webStatFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean<>();
        bean.setFilter(new WebStatFilter());

        HashMap<String, String> initParameters = new HashMap<>();
        // 这些东西不进行统计 ~
        initParameters.put("exclusions","*.js,*.css,/druid/*");

        bean.setInitParameters(initParameters);


        return bean;
    }
}
