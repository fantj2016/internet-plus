package com.tyut.web.security.config;

import com.tyut.web.security.authention.MyAuthenticationFailHandler;
import com.tyut.web.security.authention.MyAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * 资源服务器
 * Created by Fant.J.
 * 2018/5/2 20:46
 */
@Configuration
@EnableResourceServer
public class MyResourceServerConfig extends ResourceServerConfigurerAdapter {
//
//    @Autowired
//    private SpringSocialConfigurer springSocialConfigurer;
    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;
    @Autowired
    private MyAuthenticationFailHandler myAuthenticationFailHandler;
    @Override
    public void configure(HttpSecurity http) throws Exception {
        //表单登录 方式
        http.formLogin()
                .loginPage("/authentication/require")
                //登录需要经过的url请求
                .loginProcessingUrl("/authentication/form")
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailHandler);

        http
//                .apply(springSocialConfigurer)
//                .and()
                .authorizeRequests()
                .antMatchers(
                        "/user/*"
//                        "/authentication/require",
//                        "/oauth/token",
//                        "/authentication/form",
//                        "/notice/*",
//                        "/notice/list/*/*",
//                        "/sch/*",
//                        "/sch/like/*",
//                        "/guest/*",
//                        "/guest/get0/*",
//                        "/guest/get1/*",
//                        "/register/checkEmail",
//                        "/register/checkPhone",
//                        "/register/user",
//                        "/swagger*.*/",
//                        "/swagger-ui.html#/**",
//                        "/swagger-ui.html/**",
//                        "/swagger-resources/configuration/security/",
//                        "/swagger-resources/configuration/ui",
//                        "/swagger-resources/**",
//                        "/swagger-ui.html#!/**",
//                        "/v2/api-docs"
                ).authenticated()
                .anyRequest()
                .permitAll()


//                .permitAll()
//                .anyRequest()
//                .authenticated()
                .and()
                //关闭跨站请求防护
                .csrf().disable();
    }
}
