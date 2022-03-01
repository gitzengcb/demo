package com.example.server.config.security;

import com.example.server.pojo.CcUsernameInfos;
import com.example.server.service.ICcUsernameInfosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private ICcUsernameInfosService ccUsernameInfosService;
    @Autowired(required = false)
    private RestAuthorizationEntryPoint restAuthorizationEntryPoint;
    @Autowired(required = false)
    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Override//接口登陆token验证
    public void configure(HttpSecurity http) throws Exception{
        http.csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
//                所有请求都要认证
                .anyRequest()
                .authenticated()
                .and()
                .headers()
                .cacheControl();
        //添加jwt，登陆授权过滤器
        http.addFilterBefore(jwtAuthencationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        //添加自定义未授权未登陆结果返回
        http.exceptionHandling()
                .accessDeniedHandler(restfulAccessDeniedHandler)
                .authenticationEntryPoint(restAuthorizationEntryPoint);
    }
    //以下接口是不需要登陆认证的
    @Override
    public void configure(WebSecurity web) throws Exception{
        web.ignoring().antMatchers(
                "/**"
//                "/logout",
//                "/css/**",
//                "/index.html",
//                "/doc.html",
//                "/httpget",
//                "/httppost",
//                "/userid",
//                "/admin/info"
        );
    }
    @Override
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            CcUsernameInfos ccUsernameInfos = ccUsernameInfosService.getAdminByUserName(username);
            if (null != ccUsernameInfos) {
                return ccUsernameInfos;
            }
            return null;
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
            }

    @Bean
    public JwtAuthencationTokenFilter jwtAuthencationTokenFilter(){
        return new JwtAuthencationTokenFilter();

    }
}


