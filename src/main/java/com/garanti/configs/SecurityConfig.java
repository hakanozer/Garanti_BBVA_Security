package com.garanti.configs;

import com.garanti.services.CustomerDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    final CustomerDetailService service;
    final PasswordEncoder passwordEncoder;

    // SQL - roles
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(service).passwordEncoder(passwordEncoder);
    }

    // Role - Mapping
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .httpBasic()
                .and()
                .authorizeHttpRequests()
                .antMatchers("/product/**").hasRole("product")
                .antMatchers("/note/**").hasRole("note")
                .and()
                .csrf().disable().formLogin().disable();
        http.headers().frameOptions().disable();

        // Security Disable
        //http.csrf().disable().formLogin().disable();
        //http.headers().frameOptions().disable();
    }

}


/*
erkan@mail.com -> ROLE_product
ali@mail.com -> ROLE_note
zehra@mail.com -> ROLE_product, ROLE_note
 */
