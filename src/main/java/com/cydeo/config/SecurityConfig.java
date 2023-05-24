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
   private final AuthSuccessHandler authSuccessHandler;

    public SecurityConfig(UserDetailsService securityService,AuthSuccessHandler authSuccessHandler) {
        this.securityService = securityService;
        this.authSuccessHandler=authSuccessHandler;
    }

    @Bean
            public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {


                return http
                        .authorizeRequests()
                        .antMatchers("/user/**").hasAuthority("Admin")
                        .antMatchers("/project/**","/manager/**").hasAuthority("Manager")
                        .antMatchers("/task/employee/**").hasAuthority("Employee")
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
                        .successHandler(authSuccessHandler)
                        //.defaultSuccessUrl("/welcome")
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
