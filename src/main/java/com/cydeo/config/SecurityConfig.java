package com.cydeo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {


            @Bean
            public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {

                return http
                        .authorizeRequests()
                        .antMatchers("/user/**").hasRole("ADMIN")
                        .antMatchers("/project/**","/manager/**").hasRole("MANAGER")
                        .antMatchers("/task/employee/**").hasRole("EMPLOYEE")
                        .antMatchers("/task/**").hasAuthority("ROLE_EMPLOYEE")
                        .antMatchers(
                                "/",
                                "/login",
                                "/fragments/**",
                                "/assets/**",
                                "/images"
                        ).permitAll()
                        .anyRequest().authenticated()
                        .and()
                        .formLogin()
                        .loginPage("/login")
                        .defaultSuccessUrl("/welcome")
                        .failureUrl("/login?error=true")
                        .permitAll()
                        .and().build();

            }















}
