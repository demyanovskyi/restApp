package com.demyanovsky.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.sql.DataSource;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationEntryPoint authEntryPoint;
    @Autowired
    DataSource dataSource;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                //  .antMatchers(HttpMethod.GET, "/product/**").hasAnyAuthority("USER","ADMIN")
                .antMatchers(HttpMethod.POST, "/product/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.PUT, "/product/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/product/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/user/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST, "/user/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .authenticationEntryPoint(authEntryPoint);
    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        final DaoAuthenticationProvider bean = new CustomDaoAuthenticationProvider();
        bean.setUserDetailsService(customUserDetailsService);
        bean.setPasswordEncoder(encoder());
        return bean;
    }
}
