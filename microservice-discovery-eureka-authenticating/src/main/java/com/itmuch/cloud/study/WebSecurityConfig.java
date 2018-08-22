package com.itmuch.cloud.study;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Spring Cloud Finchley开始，必须加上这个类，部分关闭掉Spring Security
 * 的CSRF保护功能。否则，客户端将无法正常注册！
 * ref: http://cloud.spring.io/spring-cloud-netflix/single/spring-cloud-netflix.html#_securing_the_eureka_server
 */
@EnableWebSecurity
class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("/eureka/**");
        super.configure(http);
    }
}