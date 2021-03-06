package com.ek.eapp.config.security;

import com.ek.eapp.config.handler.EkAuthenticationFailureHandler;
import com.ek.eapp.config.handler.EkAuthenticationSuccessHandler;
import com.ek.eapp.config.handler.EkPasswordEncoder;
import com.ek.eapp.config.jwt.EkJwtAuthorizationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @ClassName: EkMutiSecurityConfig
 * @Description: TODO
 * @Author: qin_hqing
 * @Date: 2019-08-06
 * @Version: V2.0
 **/

@EnableWebSecurity
public class EkMultiSecurityConfig {

    @Autowired
    private EkUserDetailsService userDetailsService;
    @Autowired
    private EkAuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    private EkAuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private EkPasswordEncoder passwordEncoder;
    
    @Configuration
    @Order(1)
    public class ApiSecurityConfig extends WebSecurityConfigurerAdapter {

        @Autowired
        private EkJwtAuthorizationTokenFilter jwtAuthorizationTokenFilter;

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            // 添加自定义认证
            auth
                    .userDetailsService(userDetailsService)
                    .passwordEncoder(passwordEncoder)
            ;
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable().antMatcher("/api/**") //<= Security only available for /api/**
                    .authorizeRequests()
                    .antMatchers("/api/register").permitAll()
//                    .antMatchers("/api/login").permitAll()                // 将api/login定义为钉钉的免登接口，需要带着本服务token
                    .antMatchers("/api/public").permitAll()
                    .antMatchers("/api/lost").permitAll()
                    .antMatchers("/api/get-token").permitAll() // 开放获取本服务token接口
                    .antMatchers("/api/callback").permitAll()  // 开放钉钉回调地址
                    .anyRequest().authenticated()
                .and()
                    .formLogin()
//                    .oauth2Login()
                    .failureHandler(authenticationFailureHandler)
                .and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .addFilterBefore(jwtAuthorizationTokenFilter, UsernamePasswordAuthenticationFilter.class)
            ;
        }

        @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
        @Override
        public AuthenticationManager authenticationManager() throws Exception {
            return super.authenticationManager();
        }
    }

    @Configuration
    public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            // 添加自定义认证
            auth
                    .userDetailsService(userDetailsService)
                    .passwordEncoder(passwordEncoder)
            ;
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            //        http.authorizeRequests().antMatchers("/**").permitAll();
            http.csrf().disable()
//                .and()
                    .httpBasic().authenticationEntryPoint(authenticationFailureHandler)
                .and()
                    .authorizeRequests()
                    .antMatchers("/", "/index", "/toLogin", "/fail", "/druid/**").permitAll()
                    .antMatchers("/resources/**").permitAll()
                    .anyRequest().authenticated()
                    //            .access("@rbacauthorityservice.hasPermission(request,authentication)") // RBAC 动态 url 认证
                .and()
                    .formLogin().loginPage("/index") //指定自己的登录页面
                    .loginProcessingUrl("/toLogin")
                    .usernameParameter("logName")
                    .passwordParameter("password")
                    .defaultSuccessUrl("/success", false)
                    .failureHandler(authenticationFailureHandler)
//                .successHandler(authenticationSuccessHandler)
                .and()
                    .logout()
            ;
        }
    }
}
