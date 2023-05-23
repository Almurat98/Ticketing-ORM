package com.cydeo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {
   private final UserDetailsService securityService;

    public SecurityConfig(UserDetailsService securityService) {
        this.securityService = securityService;
    }

    @Bean
            public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {


                return http
                        .authorizeRequests()
                        .antMatchers("/user/**").hasRole("ADMIN")
                        .antMatchers("/project/**","/manager/**").hasRole("MANAGER")
                        .antMatchers("/task/employee/**").hasRole("EMPLOYEE")
                        .antMatchers("/task/**").hasAuthority("ROLE_EMPLOYEE")
                    //    .antMatchers("/task/**").hasAnyRole("EMPLOYEE","ADMIN")
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
                        .and()
                        .logout()
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login")
                        .and()
                        .rememberMe()
                        .tokenValiditySeconds(120)
                        .key("cydeo")
                        .userDetailsService(securityService)
                        .and().build();

            }















}
