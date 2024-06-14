package com.marketplace.demo.Security.Jwt;

import lombok.AllArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@AllArgsConstructor
public class JwtConfig  extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final JwtService jwtService;
    private final UserDetailsService userService;

    @Override
    public void configure(HttpSecurity http) {
        JwtAuthFilter customFilter = new JwtAuthFilter(jwtService,userService);
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
    }



}
