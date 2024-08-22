package com.mall.mall_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    /*
    Spring Boot 自动配置默认的 PasswordEncoder 确实使用了 @ConditionalOnMissingBean 注解。
    具体来说，Spring Boot 自动配置类是 SecurityAutoConfiguration，并且实际负责创建默认 PasswordEncoder 的部分是由 PasswordConfiguration 类处理的。
    在 Spring 应用程序中使用 @Configuration 和 @Bean 注解来定义一个 Bean 时，Spring 会将这个 Bean 注册到应用程序的上下文中。
    如果你定义的 Bean 与 Spring 默认提供的 Bean 相同类型或相同名称，那么你的自定义 Bean 会替换掉原有的默认 Bean。
     */

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //关闭csrf
                .csrf().disable()
                //不通过Session获取SecurityContext
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                // 对于登录接口 允许匿名访问
                .antMatchers("/user/login").anonymous()
                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest().authenticated();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
