package com.lysong.friday.security;

import com.lysong.friday.security.authentication.MyAuthenctiationFailureHandler;
import com.lysong.friday.security.authentication.MyAuthenticationSuccessHandler;
import com.lysong.friday.security.authentication.MyLogoutSuccessHandler;
import com.lysong.friday.security.authentication.RestAuthenticationAccessDeniedHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.devtools.restart.FailureHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.context.annotation.ApplicationScope;

import javax.annotation.Resource;

/**
 * @Author: LySong
 * @Date: 2020/3/20 21:21
 */
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    private MyAuthenctiationFailureHandler myAuthenctiationFailureHandler;

    @Resource
    private UserDetailsService userDetailsService;

    @Autowired
    private RestAuthenticationAccessDeniedHandler restAuthenticationAccessDeniedHandler;

    @Autowired
    private MyLogoutSuccessHandler myLogoutSuccessHandler;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().frameOptions().sameOrigin();
        http.authorizeRequests()
                .antMatchers("/xadmin/**",
                        "/treetable-lay/**",
                        "/ztree/**",
                        "/static/**",
                        "/css/**",
                        "/login.html")
                .permitAll()
                .anyRequest()
                .authenticated();
        http.formLogin()
                .loginPage("/login.html")
                .loginProcessingUrl("/login")
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenctiationFailureHandler)
        .and().logout()
                .permitAll()
                .invalidateHttpSession(true)
                .deleteCookies("JESSIONID")
                .logoutSuccessHandler(myLogoutSuccessHandler);
        //异常处理
        http.exceptionHandling().accessDeniedHandler(restAuthenticationAccessDeniedHandler);
    }
}
